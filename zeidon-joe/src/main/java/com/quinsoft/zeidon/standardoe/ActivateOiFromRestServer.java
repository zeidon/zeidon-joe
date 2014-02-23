/**
 *
 */
package com.quinsoft.zeidon.standardoe;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
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
import com.quinsoft.zeidon.objectdefinition.ViewEntity;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.utils.BufferedBinaryStreamReader;
import com.quinsoft.zeidon.utils.JoeUtils;

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

        try
        {
            String url = String.format( "%s/activate?application=%s&viewOdName=%s",
                                        serverUrl, application.getName(), view.getViewOd().getName() );
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost( url );
            View qual = activateOptions.getQualificationObject();
            String qualStr = JoeUtils.serializeViewAsJson( qual );
            StringEntity entity = new StringEntity( qualStr );
            post.setEntity( entity );
            post.setHeader( "Content-Type", "application/json" );

            HttpResponse response = client.execute( post );
            InputStream stream = response.getEntity().getContent();

            // If we're in debug mode, print out the results.
            if ( task.log().isDebugEnabled() )
            {
                StringWriter writer = new StringWriter();
                IOUtils.copy(stream, writer, "UTF-8");
                stringResponse = writer.toString();
                task.log().debug( "REST response: %s", stringResponse );
                stream = IOUtils.toInputStream(stringResponse, "UTF-8");
            }

            // Since we expect multiple OIs in a single stream, create a stream reader to share
            // between activates.

            ActivateOisFromJsonStream activator = new ActivateOisFromJsonStream(getTask(), stream, null );
            View restRc = activator.read().get( 0 );
            restRc.logObjectInstance();
            return restRc;

//            activator = new ActivateOiFromStream(getTask(), application, stream, null );
//            activator.setStreamReader( reader );
//            activator.setEmptyView( view );
//            ViewImpl newView = activator.read();
//
//            newView.logObjectInstance();
//            return newView;
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
