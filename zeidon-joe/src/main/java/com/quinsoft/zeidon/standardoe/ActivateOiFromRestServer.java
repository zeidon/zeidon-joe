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

import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.Activator;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonRestException;
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
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
            view = this.task.activateEmptyObjectInstance( options.getViewOd() );
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
        ViewOd viewOd = view.getViewOd();
        Application application = viewOd.getApplication();
        String stringResponse = null;
        BufferedBinaryStreamReader reader = null;
        HttpClient client = null;

        try
        {
            String url = String.format( "%s/activate?application=%s&viewOdName=%s",
                                        serverUrl, application.getName(), view.getViewOd().getName() );
            client = new DefaultHttpClient();
            HttpPost post = new HttpPost( url );
            View qual = activateOptions.getQualificationObject();
            String qualStr = new SerializeOi().asJson().toStringWriter().write( qual ).getString();
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


            List<View> views = new DeserializeOi( getTask() )
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
     * @see com.quinsoft.zeidon.standardoe.Activator#activate(com.quinsoft.zeidon.objectdefinition.ViewEntity)
     */
    @Override
    public int activate( ViewEntity subobjectRootEntity )
    {
        throw new ZeidonException( "Lazy load activate is not supported for REST (yet)." );
    }

    private TaskImpl getTask()
    {
        return task;
    }
}
