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
package com.quinsoft.zeidon.utils;

import java.io.Writer;

import com.quinsoft.zeidon.ActivateOptions;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.SerializeOi;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.LodDef;

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
    private final LodDef restResponse;

    public RestServerImplementation(ObjectEngine objectEngine)
    {
        this.objectEngine = objectEngine;
        Task systemTask = objectEngine.getSystemTask();
        restResponse = systemTask.getApplication().getLodDef( systemTask, "kzrestresponse" );
    }

    public String activate( Task   task,
                            String lodDefName,
                            String postContent,
                            DataFormat format )  // Currently we don't do anything with this because only JSON is supported.
    {
        View rc = task.activateEmptyObjectInstance( restResponse );
        EntityInstance rcEI = rc.cursor( "RestResponse" ).createEntity();

        try
        {
            rcEI.getAttribute( "ReturnCode" ).setValue( 0 ); // Assume everything is OK.
            View qual = task.deserializeOi().setLodDef( lodDefName )
                                    .asJson()
                                    .fromString( postContent )
                                    .activateFirst();

            qual.logObjectInstance();

            ActivateOptions activateOptions = new ActivateOptions( task );
            View view = task.activateObjectInstance( lodDefName, qual, activateOptions );
            Writer writer = new SerializeOi().asJson().addView( rc, view ).withIncremental().toStringWriter();
            return writer.toString();
        }
        catch ( Exception e )
        {
            task.log().error( "Error in Activate", e, (Object[]) null );

            // Set the error codes.
            rcEI.getAttribute( "ReturnCode" ).setValue( 500 );
            rcEI.getAttribute( "ErrorMessage" ).setValue( e.getMessage() );

            // Write the rc OI to a string.
            Writer writer = rc.serializeOi().asJson().withIncremental().toStringWriter();
            return writer.toString();
        }
    }

    /**
     * Activate an OI.
     *
     * @param applicationName
     * @param lodDefName
     * @param postContent The qualification object as JSON.  Assumed to be retrieved from
     *                    POST data.
     *
     * @return The activated OI as JSON string.
     */
    public String activate( String applicationName,
                            String lodDefName,
                            String postContent,
                            DataFormat format )  // Currently we don't do anything with this because only JSON is supported.
    {
        Task task = objectEngine.createTask( applicationName, false );

        try
        {
            return activate( task, lodDefName, postContent, format );
        }
        finally
        {
            task.dropTask();
        }
    }
}
