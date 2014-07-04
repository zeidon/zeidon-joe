/**
 *
 */
package com.quinsoft.zeidon.utils;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.WriteOiOptions;
import com.quinsoft.zeidon.objectdefinition.ViewOd;
import com.quinsoft.zeidon.standardoe.WriteOisToJsonStream;

/**
 * Implements the activate and commit logic for REST servers.  This is used by the
 * default Zeidon REST gateway but can also be used by third-party servers.
 *
 * @author dg
 *
 */
public class RestServerImplementation
{
    /**
     * Lists the currently available data formats that can be used to transfer data
     * between the client and the server.
     *
     * @author dg
     *
     */
    public enum DataFormat
    {
        JSON;
    }

    private final ObjectEngine objectEngine;
    private final ViewOd restResponse;

    public RestServerImplementation(ObjectEngine objectEngine)
    {
        this.objectEngine = objectEngine;
        Task systemTask = objectEngine.getSystemTask();
        restResponse = systemTask.getApplication().getViewOd( systemTask, "kzrestresponse" );
    }

    public String activate( Task   task,
                            String viewOdName,
                            String postContent,
                            DataFormat format )  // Currently we don't do anything with this because only JSON is supported.
    {
        View rc = task.activateEmptyObjectInstance( restResponse );
        EntityInstance rcEI = rc.cursor( "RestResponse" ).createEntity();
        WriteOiOptions options = new WriteOiOptions();
        options.setIncremental();

        try
        {
            rcEI.getAttribute( "ReturnCode" ).setValue( 0 ); // Assume everything is OK.

            InputStream stream = IOUtils.toInputStream(postContent, "UTF-8");
            View qual = task.activateOiFromJsonStream( stream, null );
            qual.logObjectInstance();
            
            ActivateOptions activateOptions = new ActivateOptions( task );
            
            View view = task.activateObjectInstance( viewOdName, qual, activateOptions );

            StringWriter strWriter = new StringWriter();

            List<View> list = Arrays.asList( rc, view );
            WriteOisToJsonStream writer = new WriteOisToJsonStream( list, strWriter, options );
            writer.writeToStream();

            return strWriter.toString();
        }
        catch ( Exception e )
        {
            task.log().error( "Error in Activate", e, (Object[]) null );
            
            // Set the error codes.
            rcEI.getAttribute( "ReturnCode" ).setValue( 500 );
            rcEI.getAttribute( "ErrorMessage" ).setValue( e.getMessage() );

            // Write the rc OI to a string.
            StringWriter strWriter = new StringWriter();
            WriteOisToJsonStream writer = new WriteOisToJsonStream( rc, strWriter, options );
            writer.writeToStream();

            return strWriter.toString();
        }
    }
    
    /**
     * Activate an OI.
     *
     * @param applicationName
     * @param viewOdName
     * @param postContent The qualification object as JSON.  Assumed to be retrieved from
     *                    POST data.
     *
     * @return The activated OI as JSON string.
     */
    public String activate( String applicationName,
                            String viewOdName,
                            String postContent,
                            DataFormat format )  // Currently we don't do anything with this because only JSON is supported.
    {
        Task task = objectEngine.createTask( applicationName, false );

        try
        {
            return activate( task, viewOdName, postContent, format );
        }
        finally
        {
            task.dropTask();
        }
    }
}
