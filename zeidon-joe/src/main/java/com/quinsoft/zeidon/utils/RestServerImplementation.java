/**
 * 
 */
package com.quinsoft.zeidon.utils;

import java.io.InputStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;

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

    public RestServerImplementation(ObjectEngine objectEngine)
    {
        this.objectEngine = objectEngine;
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
            InputStream stream = IOUtils.toInputStream(postContent, "UTF-8");
            View qual = task.activateOiFromJsonStream( stream, null );
            qual.logObjectInstance();
            View view = task.activateObjectInstance( viewOdName, qual, new ActivateOptions( task ) );

            StringWriter writer = new StringWriter();
            view.writeOiAsJson( writer );
            return writer.toString();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
        finally
        {
            task.dropTask();
        }
    }

}
