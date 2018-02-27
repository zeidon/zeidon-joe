/**
    This file is part of the Zeidon Java Object Engine (Zeidon JOE).

    Zeidon JOE is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Zeidon JOE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Zeidon JOE.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.net.URI;
import java.net.URLEncoder;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;

/**
 * Activate an OI from a REST server.
 *
 * @author dgc
 *
 */
class ActivateOiFromRestServer implements Activator
{
    final private String serverUrl;

    private TaskImpl  task;
    private ViewImpl  view;
    private ActivateOptions activateOptions;

    //
    // These are shared by all connections
    //

    private static RequestConfig requestConfig;
    private static CloseableHttpClient httpClient;

    public ActivateOiFromRestServer( String serverUrl )
    {
        this.serverUrl = serverUrl;
    }

    private synchronized void initHttp( Task task )
    {
        if ( requestConfig != null )
            return;

        // TODO: This assumes all applications use the same config.  We should someday update this to allow
        // different applications to have different configs.
        String group = task.getApplication().getName() + ".Http";
        int timeout;
        try
        {
            timeout = Integer.parseInt( task.readZeidonConfig( group, "HttpTimeout", "2000" ) );
        }
        catch ( Exception e )
        {
            throw new ZeidonException( "Error reading HTTP config: " + e.getMessage() )
                        .appendMessage( "Group = %s, key = 'HttpTimeout'", group );
        }

        int connectionPoolSize;
        try
        {
            connectionPoolSize = Integer.parseInt( task.readZeidonConfig( group, "ConnectionPoolSize", "5" ) );
        }
        catch ( Exception e )
        {
            throw new ZeidonException( "Error reading HTTP config: " + e.getMessage() )
                        .appendMessage( "Group = %s, key = 'ConnectionPoolSize'", group );
        }

        requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal( connectionPoolSize );
        cm.setDefaultMaxPerRoute( connectionPoolSize );
        httpClient = HttpClients.custom().setConnectionManager( cm ).build();
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#init(com.quinsoft.zeidon.standardoe.TaskImpl, com.quinsoft.zeidon.standardoe.ViewImpl, com.quinsoft.zeidon.ActivateOptions)
     */
    @Override
    public View init( Task task, View initialView, ActivateOptions options )
    {
        assert options != null;

        this.task = (TaskImpl) task;
        if ( initialView == null )
            view = this.task.activateEmptyObjectInstance( options.getLodDef() );
        else
            view = ((InternalView) initialView).getViewImpl();

        activateOptions = options;
        initHttp( task );

        return view;
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#activate()
     */
    @Override
    public View activate()
    {
        LodDef lodDef = view.getLodDef();
        Application application = lodDef.getApplication();

        View qual = activateOptions.getQualificationObject();
        String qualStr = qual.serializeOi().asJson().compressed().withIncremental().toStringWriter().toString();
        String url = "";
        CloseableHttpResponse response = null;

        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.

        try
        {
            url = String.format( "%s/%s/%s?qualOi=%s", serverUrl, application.getName(),
                                  view.getLodDef().getName(), URLEncoder.encode( qualStr, "UTF-8" ) );

            URI urlObject = new URI( url );
            HttpGet httpGet = new HttpGet( urlObject );
            httpGet.setConfig( requestConfig );

            response = httpClient.execute(httpGet);
            int status = response.getStatusLine().getStatusCode();
            task.log().debug( "HTTP activate status = %d", status );
            if ( status != 200 )
            {
                qual.logObjectInstance();
                throw new ZeidonException( "Status error when activating from HTTP server.  Status = %d", status )
                                .appendMessage( "URL = %s", url );
            }

            HttpEntity entity = response.getEntity();
            String json = IOUtils.toString( entity.getContent(), "UTF-8" );
            return getTask().deserializeOi()
                    .asJson()
                    .fromString( json )
                    .activateFirst();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
        finally
        {
            if ( response != null )
            {
                EntityUtils.consumeQuietly(response.getEntity());
                IOUtils.closeQuietly( response );
            }
        }
    }

    /* (non-Javadoc)
     * @see com.quinsoft.zeidon.standardoe.Activator#activate(com.quinsoft.zeidon.objectdefinition.EntityDef)
     */
    @Override
    public int activate( EntityDef subobjectRootEntity )
    {
        throw new ZeidonException( "Lazy load activate is not supported for REST (yet)." );
    }

    private TaskImpl getTask()
    {
        return task;
    }

    @Override
    public void dropOutstandingLocks()
    {
        throw new ZeidonException( "Not implemented" );
    }
}
