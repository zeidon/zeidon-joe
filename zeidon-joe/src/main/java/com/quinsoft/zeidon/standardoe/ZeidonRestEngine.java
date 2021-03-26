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

import com.google.common.collect.ImmutableMap;
import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.HttpErrorMessage;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonRestException;
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.Map;

public class ZeidonRestEngine
{
    private final static Map<StreamFormat, String> STREAM_FORMAT_TO_CONTENT_TYPE = ImmutableMap
            .of( StreamFormat.JSON, MediaType.APPLICATION_JSON,
                 StreamFormat.XML, MediaType.APPLICATION_XML );

    private final ObjectEngine oe;

    private StreamFormat defaultStreamFormat = StreamFormat.JSON;

    public ZeidonRestEngine( ObjectEngine oe )
    {
        this.oe = oe;
    }

    /**
     * A request wrapper that creates a task and supplies a REST handler for performing
     * common REST tasks.
     *
     * Note: It is assumed that the application name has been specified as either a
     *       parameter or attribute in the request object.
     *
     * <pre>
     * {@code
     *
     *   request.setAttribute("applicationName", applicationName );
     *
     *   return restEngine.withTask(request, (handler) -> {
     *       return handler.commit( body );
     *   } );
     * }
     * </pre>
     *
     * @param request servlet request created by Java container.
     * @param callback Lambda code called by withTask after creating task.
     * @return Response object with serialized View or error.
     */
    public Response withTask( HttpServletRequest request, RestEngineCallback callback )
    {
        Task task = null;
        try {
            String applicationName = request.getParameter("applicationName");
            if (StringUtils.isBlank(applicationName))
                applicationName = (String) request.getAttribute("applicationName");

            if (StringUtils.isBlank(applicationName))
                throw new HttpErrorMessage("applicationName is required as param or attribute");

            task = oe.createTask(applicationName);
            Application app = task.getApplication();
            validateApplication(task, app);

            return callback.process(new RestEngineHandler(task, request));
        } catch (Exception e) {
            return responseFromException(task, e);
        } finally {
            if (task != null)
                task.dropTask();
            ;
        }
    }

    public StreamFormat getDefaultStreamFormat() {
        return defaultStreamFormat;
    }

    public ZeidonRestEngine setDefaultStreamFormat(StreamFormat defaultStreamFormat) {
        this.defaultStreamFormat = defaultStreamFormat;
        return this;
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

    Response responseFromException( Task task, Exception e )
    {
        task.log().error(e);
        if ( e instanceof HttpErrorMessage )
        {
            HttpErrorMessage error = (HttpErrorMessage) e;
            return Response.status( error.getStatusCode() ).entity( error.getMessage() ).build();
        }

        return Response.status( 500 ).build();
    }

    private StreamFormat interpretContentType( String contentType )
    {
        if ( StringUtils.isBlank( contentType ) )
            return defaultStreamFormat;

        switch ( contentType )
        {
            case MediaType.APPLICATION_XML:
                return StreamFormat.XML;

            case MediaType.APPLICATION_JSON:
                return StreamFormat.JSON;

            default:
                throw new HttpErrorMessage( "Unsupported content-type: %s", contentType );
        }
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

    @FunctionalInterface
    public interface RestEngineCallback
    {
        Response process( RestEngineHandler restEngineTask );
    }

    public class RestEngineHandler
    {
        private final Task               task;
        private final HttpServletRequest request;
        private final StreamFormat       format;

        private RestEngineHandler( Task task, HttpServletRequest request )
        {
            this.task = task;
            this.request = request;
            this.format = interpretContentType( request.getHeader( "Content-Type" ) );
        }

        private String getParam( String name )
        {
            String param = request.getParameter( name );
            if ( StringUtils.isBlank( param ) )
                param = (String) request.getAttribute( name );

            return param;
        }

        private String getRequiredParam( String name )
        {
            String param = getParam( name );
            if ( StringUtils.isBlank( param ) )
                throw new HttpErrorMessage( name + " is required as param or attribute" );

            return param;
        }

        public Task getTask()
        {
            return task;
        }

        /**
         * Deserialize the HTTP body.  The body is assumed to have full incrementals.
         * Use getViewFromBody( lodName, body ) to deserialize non incremental bodies.
         */
        public View getViewFromBody( String body )
        {
            try
            {
                return task
                        .deserializeOi()
                        .setFormat( format )
                        .fromString(body)
                        .withKeyValidation()
                        .activateFirst();
            }
            catch ( KeyValidationError e )
            {
                task.log().error( e );
                throw new HttpErrorMessage( "Invalid request" ).withStatusCode( 403 );
            }
        }

        /**
         * Deserialize the HTTP body from a string without incrementals.
         */
        public View getViewFromBody( String lodName, String body )
        {
            try
            {
                return task
                        .deserializeOi()
                        .setFormat( format )
                        .setLodDef( lodName )
                        .fromString(body)
                        .withKeyValidation()
                        .activateFirst();
            }
            catch ( KeyValidationError e )
            {
                task.log().error( e );
                throw new HttpErrorMessage( "Invalid request" ).withStatusCode( 403 );
            }
        }

        /**
         * Activates an OI with root key equal to key param.

         * @return Response object with serialized OI and status code = 200
         */
        public Response activate( String key )
        {
            String jsonQual = "{\root\": {\"key\": \"" + key + "\"}}";
            request.setAttribute( "qual", jsonQual );
            return activate();
        }

        /**
         * Activates an OI using the qualification specified in either the
         * qual or qualOi query params.

         * @return Response object with serialized OI and status code = 200
         */
        public Response activate()
        {
            String lodName = getRequiredParam( "lodName" );
            LodDef lodDef = task.getApplication().getLodDef( task, lodName );

            String jsonQual = getParam( "qual" );
            String qualOi = getParam( "qualOi" );
            QualificationBuilder qual = instantiateQual( task, lodDef, jsonQual, qualOi );
            validateActivate( qual );

            View view = qual.activate();
            validateActivateResult(view);
            return viewToResponse(view);
        }

        public Response viewToResponse( View view )
        {
            String serialized = view.serializeOi().setFormat( format ).toString();
            return Response
                    .ok( serialized )
                    .type( STREAM_FORMAT_TO_CONTENT_TYPE.get( format ) )
                    .build();
        }

        /**
         * Commits the OI specified in the body.
         * @param body the OI deserialized as JSON/XML.  Must have incrementals.
         * @return Response object with the resulting OI and status code = 201
         */
        public Response commit( String body )
        {
            View view = getViewFromBody( body );
            validateCommit( view );
            view.commit();
            validateCommitResult( view );

            String serialized = view.serializeOi().setFormat( format ).toString();
            return Response
                    .ok( serialized )
                    .status(Status.CREATED)
                    .type( STREAM_FORMAT_TO_CONTENT_TYPE.get( format ) )
                    .build();
        }
    }
}
