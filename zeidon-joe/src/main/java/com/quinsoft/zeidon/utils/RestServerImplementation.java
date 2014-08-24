package com.quinsoft.zeidon.utils;

import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.objectdefinition.ViewOd;

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
        SerializeOi options = new SerializeOi();
        options.withIncremental();

        try
        {
            rcEI.getAttribute( "ReturnCode" ).setValue( 0 ); // Assume everything is OK.
            View qual = new DeserializeOi( task )
                                    .asJson()
                                    .fromString( postContent )
                                    .activateFirst();

            qual.logObjectInstance();

            ActivateOptions activateOptions = new ActivateOptions( task );
            View view = task.activateObjectInstance( viewOdName, qual, activateOptions );
            SerializeOi writer = new SerializeOi().addView( rc, view ).withIncremental().toStringWriter().write();
            return writer.getString();
        }
        catch ( Exception e )
        {
            task.log().error( "Error in Activate", e, (Object[]) null );

            // Set the error codes.
            rcEI.getAttribute( "ReturnCode" ).setValue( 500 );
            rcEI.getAttribute( "ErrorMessage" ).setValue( e.getMessage() );

            // Write the rc OI to a string.
            SerializeOi writer = new SerializeOi().addView( rc ).withIncremental().toStringWriter().write();
            return writer.getString();
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
