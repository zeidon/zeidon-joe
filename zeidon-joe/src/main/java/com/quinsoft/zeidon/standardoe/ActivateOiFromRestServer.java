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

    Copyright 2009-2014 QuinSoft
 */
package com.quinsoft.zeidon.standardoe;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonRestException;
import com.quinsoft.zeidon.objectdefinition.EntityDef;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.BufferedBinaryStreamReader;

/**
 * Activate an OI from a REST server.
 *
 * @author dgc
 *
 */
public class ActivateOiFromRestServer implements Activator
{
    final private String serverUrl;

    private TaskImpl  task;
    private ViewImpl  view;
    private ActivateOptions activateOptions;

    public ActivateOiFromRestServer( String serverUrl )
    {
        this.serverUrl = serverUrl;
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
        String stringResponse = null;
        BufferedBinaryStreamReader reader = null;
        HttpClient client = null;

        try
        {
            String url = String.format( "%s/activate?application=%s&lodDefName=%s",
                                        serverUrl, application.getName(), view.getLodDef().getName() );
            client = new DefaultHttpClient();
            HttpPost post = new HttpPost( url );
            View qual = activateOptions.getQualificationObject();
            String qualStr = qual.serializeOi().asJson().withIncremental().toStringWriter().toString();
            StringEntity entity = new StringEntity( qualStr );
            post.setEntity( entity );
            post.setHeader( "Content-Type", "application/json" );

            HttpResponse response = client.execute( post );
            InputStream stream = response.getEntity().getContent();
            StatusLine status = response.getStatusLine();
            task.log().info( "Status from http activate = %s", status );
            int statusCode = status.getStatusCode();

            // If we're in debug mode, print out the results.
            if ( task.log().isDebugEnabled() || statusCode != 200 )
            {
                StringWriter writer = new StringWriter();
                IOUtils.copy(stream, writer, "UTF-8");
                stringResponse = writer.toString();
                task.log().debug( "REST response: %s", stringResponse );
                stream = IOUtils.toInputStream(stringResponse, "UTF-8");
            }

            if ( statusCode != 200 )
            {
                throw new ZeidonException( "http activate failed with status %s", status )
                            .appendMessage( "web URL = %s", url );
            }


            List<View> views = getTask().deserializeOi()
                                        .asJson()
                                        .fromInputStream( stream )
                                        .activate();
            View restRc = views.get( 0 );
            restRc.logObjectInstance();
            Integer rc = restRc.cursor( "RestResponse" ).getAttribute( "ReturnCode" ).getInteger();
            if ( rc != 0 )
            {
                String errorMsg = restRc.cursor( "RestResponse" ).getAttribute( "ErrorMessage" ).getString();
                throw new ZeidonRestException( "Error activating OI from REST server %d", rc )
                                                .appendMessage( "%s", errorMsg );
            }

            return views.get( 1 );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e )
                                 .appendMessage( "Server URL = %s", serverUrl );
        }
        finally
        {
            IOUtils.closeQuietly( reader );
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
}
