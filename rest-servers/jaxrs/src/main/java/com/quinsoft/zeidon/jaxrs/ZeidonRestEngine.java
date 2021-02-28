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
package com.quinsoft.zeidon.jaxrs;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonRestException;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Map;

/**
 *
 *
 */
public class ZeidonRestEngine
{
    private final static Map<StreamFormat, String> STREAM_FORMAT_TO_CONTENT_TYPE = Map.of(
            StreamFormat.JSON, MediaType.APPLICATION_JSON,
            StreamFormat.XML, MediaType.APPLICATION_XML );

    private final ObjectEngine oe;
    private StreamFormat defaultResponseType = StreamFormat.JSON;

    public ZeidonRestEngine( ObjectEngine oe )
    {
        this.oe = oe;
    }

    /**
     *
     * @param applicationName
     * @param lodName
     * @param jsonQual - Qualification as simple JSON.
     * @param qualOi   - Qualification as ActivateQual LOD.
     * @return
     */
    public Response activate( String applicationName,
                              String lodName,
                              String jsonQual,
                              String qualOi,
                              StreamFormat format )
    {
        Task task = null;
        format = determineFormat( format );
        try {
            task = oe.createTask( applicationName );
            task.log().debug( "Task created ======================" );
            Application app = task.getApplication();
            validateApplication( task, app );

            LodDef lodDef = app.getLodDef( task, lodName );
            QualificationBuilder qual = instantiateQual( task, lodDef, jsonQual, qualOi );
            validateActivate( qual );

            View view = qual.activate();
            validateActivateResult( view );
            String serialized = view.serializeOi().setFormat( format ).toString();
            return Response
                    .ok( serialized )
                    .type( STREAM_FORMAT_TO_CONTENT_TYPE.get( format ) )
                    .build();
        }
        catch ( Exception e )
        {
            if ( task != null )
                task.log().error( e );

            return responseFromException(e);
        }
        finally
        {
            if ( task != null )
                task.dropTask();;
        }
    }

    void validateApplication( Task task, Application application )
    {
        return; // Nothing to do by default.
    }

    void validateActivate( QualificationBuilder qualBuilder )
    {
        return; // Nothing to do by default.
    }

    void validateCommit( View view )
    {
        return; // Nothing to do by default.
    }

    void validateActivateResult( View view )
    {
        return; // Nothing to do by default.
    }

    void validateCommitResult( View view )
    {
        return; // Nothing to do by default.
    }

    Response responseFromException( Exception e )
    {
        return Response.status( 500 ).build();
    }

    private StreamFormat determineFormat( StreamFormat clientFormat )
    {
        if ( clientFormat == null )
            return defaultResponseType;

        return clientFormat;
    }

    private QualificationBuilder instantiateQual( Task task, LodDef lodDef, String jsonQual, String qualOi )
    {
        QualificationBuilder qual = new QualificationBuilder( task );
        qual.setLodDef( lodDef );

        if ( StringUtils.isNotBlank( jsonQual ) )
        {
            if ( StringUtils.isNotBlank( qualOi ) )
                throw new ZeidonRestException( "JSON and qualOi both specified for qualification" );

            qual.loadFromJsonString( jsonQual );
        }

        if ( StringUtils.isNotBlank( qualOi ) )
            qual.loadFromSerializedString( qualOi );

        return qual;
    }

    public Response commit( String applicationName, String body, StreamFormat format )
    {
        Task task = null;
        format = determineFormat( format );
        try {
            task = oe.createTask( applicationName );
            task.log().debug( "Task created ======================" );
            Application app = task.getApplication();
            validateApplication( task, app );

            View view = task.deserializeOi().setFormat( format ).fromString( body ).activateFirst();
            validateCommit( view );
            view.commit();
            validateCommitResult( view );
            String serialized = view.serializeOi().setFormat( format ).toString();
            return Response
                    .ok( serialized )
                    .type( STREAM_FORMAT_TO_CONTENT_TYPE.get( format ) )
                    .build();
        }
        catch ( Exception e )
        {
            if ( task != null )
                task.log().error( e );

            return responseFromException(e);
        }
        finally
        {
            if ( task != null )
                task.dropTask();;
        }
    }
}
