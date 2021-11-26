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
package com.quinsoft.zeidon.http;

import com.google.common.collect.ImmutableMap;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.SubobjectValidationException;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.objectdefinition.KeyValidator.KeyValidationError;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ZeidonRestServerEngine
{
    private final static Map<StreamFormat, String> STREAM_FORMAT_TO_CONTENT_TYPE = ImmutableMap
            .of( StreamFormat.JSON, MediaType.APPLICATION_JSON,
                 StreamFormat.XML, MediaType.APPLICATION_XML );

    private final ObjectEngine oe;

    private StreamFormat defaultStreamFormat = StreamFormat.JSON;

    /**
        Defines whether OIs returned to the client have incrementals or not.
     */
    private Boolean defaultResponseWithIncrementals = true;
    private Boolean defaultResponseWithHiddenAttributes = false;

    private final List<RestValidator> activateValidators = new ArrayList<>();
    private final List<RestValidator> activateResultValidators = new ArrayList<>();
    private final List<RestValidator> commitValidators = new ArrayList<>();
    private final List<RestValidator> commitResultValidators = new ArrayList<>();

    private final Map<LodDef, LodPermissions> allowLods = new HashMap<>();
    private final Set<LodDef> blockLods = new HashSet<>();

    @FunctionalInterface
    public interface RestValidator
    {
        void validate( RestEngineHandler handler );
    }

    @FunctionalInterface
    public interface RestEngineCallback
    {
        Response process( RestEngineHandler restEngineTask );
    }

    public class LodPermissions
    {
        public final boolean commit;
        public final boolean activate;

        public LodPermissions()
        {
            this.commit = true;
            this.activate = true;
        }

        public LodPermissions( boolean activate, boolean commit )
        {
            this.activate = activate;
            this.commit = commit;
        }
    }

    public ZeidonRestServerEngine( ObjectEngine oe )
    {
        this.oe = oe;
    }

    public ZeidonRestServerEngine addLodPermissions( String applicationName, String lodName, LodPermissions lodPermissions )
    {
        LodDef lodDef = oe.getApplication( applicationName ).getLodDef( oe.getSystemTask(), lodName );
        allowLods.put( lodDef, lodPermissions );
        return this;
    }

    public ZeidonRestServerEngine addLodPermissions( String applicationName, String lodName )
    {
        LodDef lodDef = oe.getApplication( applicationName ).getLodDef( oe.getSystemTask(), lodName );
        allowLods.put( lodDef, new LodPermissions() );
        return this;
    }

    public ZeidonRestServerEngine blockLod( String applicationName, String lodName )
    {
        LodDef lodDef = oe.getApplication( applicationName ).getLodDef( oe.getSystemTask(), lodName );
        blockLods.add( lodDef );
        return this;
    }

    public ZeidonRestServerEngine addActivateValidation( RestValidator validator )
    {
        activateValidators.add( validator );
        return this;
    }

    public ZeidonRestServerEngine addActivateResultValidation( RestValidator validator )
    {
        activateResultValidators.add( validator );
        return this;
    }

    public ZeidonRestServerEngine addCommitValidation( RestValidator validator )
    {
        commitValidators.add( validator );
        return this;
    }

    public ZeidonRestServerEngine addCommitResultValidation( RestValidator validator )
    {
        commitResultValidators.add( validator );
        return this;
    }

    public ZeidonRestServerEngine setDefaultResponseIncrementals( boolean defaultResponse )
    {
        defaultResponseWithIncrementals = defaultResponse;
        return this;
    }

    public ZeidonRestServerEngine setDefaultResponseWithHiddenAttributes( boolean defaultResponse )
    {
        defaultResponseWithHiddenAttributes = defaultResponse;
        return this;
    }

    public String serializeView( View view )
    {
        String serialized = view.serializeOi()
                .setFormat( defaultStreamFormat )
                .withIncremental( defaultResponseWithIncrementals )
                .setWriteHiddenAttributes( defaultResponseWithHiddenAttributes )
                .toString();

        return serialized;
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
        try
        {
            String applicationName = request.getParameter("applicationName");
            if (StringUtils.isBlank(applicationName))
                applicationName = (String) request.getAttribute("applicationName");

            if (StringUtils.isBlank(applicationName))
                throw new HttpErrorMessage("applicationName is required as param or attribute");

            task = oe.createTask(applicationName);
            return callback.process(new RestEngineHandler(task, request));
        }
        catch ( Exception e )
        {
            return responseFromException( task, e );
        }
        finally {
            if (task != null)
                task.dropTask();
        }
    }

    public StreamFormat getDefaultStreamFormat()
    {
        return defaultStreamFormat;
    }

    public ZeidonRestServerEngine setDefaultStreamFormat( StreamFormat defaultStreamFormat )
    {
        this.defaultStreamFormat = defaultStreamFormat;
        return this;
    }

    /**
     * Performs a standard activate with default processing.  The activate parameters
     * are assumed to be set as query parameters or request attributes.  The following
     * are required:
     * <ul>
     *     <li>applicationName - Name of the application.</li>
     *     <li>lodName - Name of the LOD.</li>
     *     <li>qual - The qualification OI in simple JSON form.</li>
     * </ul>
     * -- OR--
     * <ul>
     *     <li>qualOI - The qualification OI in Zeidon OI form.</li>
     * </ul>
     * If values are specified as path params in the HTTP request they must be copied
     * to the request object as attributes:
     *
     *<pre>
     * &#64;GET
     * &#64;Path("/{applicationName}/{lodName}")
     * &#64;Produces({"application/xml", "application/json"})
     * public Response activate( &#64;Context HttpServletRequest request,
     *                           &#64;PathParam("applicationName") String applicationName,
     *                           &#64;PathParam("lodName")         String lodName,
     *                           &#64;QueryParam("qual")           String jsonQual )
     * {
     *     request.setAttribute("applicationName", applicationName );
     *     request.setAttribute( "lodName", lodName );
     *     return restEngine.activate( request );
     * }
     * </pre>
     * @param request - HttpServletRequest
     * @return Response with the activated OI or an error.
     */
    public Response activate( HttpServletRequest request )
    {
        return withTask(request, (handler) -> {
            return handler.activate();
        } );
    }

    public Response activateByKey( HttpServletRequest request, String key )
    {
        return withTask(request, (handler) -> {
            return handler.activateByKey( key );
        } );
    }

    public Response commit( HttpServletRequest request, String body )
    {
        return withTask(request, (handler) -> {
            return handler.commit( body );
        } );
    }

    public Response deleteByKey( HttpServletRequest request, String key )
    {
        return withTask(request, (handler) -> {
            return handler.deleteByKey( key );
        } );
    }

    void validateActivate( RestEngineHandler handler )
    {
        LodDef lodDef = handler.lodDef;
        if ( blockLods.contains( lodDef ) )
            throw new HttpErrorMessage( "Illegal LOD name %s", lodDef.getName() )
                    .withStatusCode( Status.FORBIDDEN );

        if ( allowLods.size() > 0 )
        {
            LodPermissions permissions = allowLods.get( lodDef );
            if ( permissions != null && permissions.activate == false )
                throw new HttpErrorMessage( "Illegal LOD name %s", lodDef.getName() )
                        .withStatusCode( Status.FORBIDDEN );
        }

        activateValidators.stream().forEach( validator -> validator.validate( handler ) );
    }

    void validateActivateResult(  RestEngineHandler handler )
    {
        activateResultValidators.stream().forEach( validator -> validator.validate( handler ) );
    }

    void validateCommit( RestEngineHandler handler )
    {
        LodDef lodDef = handler.lodDef;
        if ( blockLods.contains( lodDef ) )
            throw new HttpErrorMessage( "Illegal LOD name %s", lodDef.getName() )
                    .withStatusCode( Status.FORBIDDEN );

        if ( allowLods.size() > 0 )
        {
            LodPermissions permissions = allowLods.get( lodDef );
            if ( permissions != null && permissions.commit == false )
                throw new HttpErrorMessage( "Illegal LOD name %s", lodDef.getName() )
                        .withStatusCode( Status.FORBIDDEN );
        }

        commitValidators.stream().forEach( validator -> validator.validate( handler ) );
    }

    void validateCommitResult( RestEngineHandler handler )
    {
        commitResultValidators.stream().forEach( validator -> validator.validate( handler ) );
    }

    Response responseFromException( Task task, Exception e )
    {
        task.log().error( e );
        String errorMessage = e.getMessage();
        int statusCode = 500;
        String errorCode = "error";
        if ( e instanceof HttpErrorMessage )
        {
            HttpErrorMessage error = (HttpErrorMessage) e;
            statusCode = error.getStatusCode();
            errorCode = error.getErrorCode();
        }
        else if ( e instanceof SubobjectValidationException )
        {
            return subobjectValidationError( task, (SubobjectValidationException) e );
        }

        View errors = task.getSystemTask().activateEmptyObjectInstance( "kzrestresponse" );
        EntityCursor response = errors.cursor( "RestResponse" );
        response.createEntity()
                .getAttribute( "ErrorCode" ).setValue( errorCode )
                .getAttribute( "ErrorMessage" ).setValue( errorMessage );

        String str = errors
                .serializeOi()
                .setFormat( defaultStreamFormat )
                .withMappings( HttpErrorMessage.JSON_ERROR_MAPPING )
                .compressed().toString();

        return Response.status( statusCode )
                .entity( str )
                .header( "X-Zeidon-Error", e.getClass().getCanonicalName() )
                .build();
    }

    private Response subobjectValidationError( Task task, SubobjectValidationException e )
    {
        View errors = task.getSystemTask().activateEmptyObjectInstance( "kzrestresponse" );
        EntityCursor response = errors.cursor( "RestResponse" );
        e.getChildExceptions().forEach( ( validationException ) -> {
            response.createEntity()
                    .getAttribute( "ErrorCode" ).setValue( "validation" )
                    .getAttribute( "ErrorMessage" ).setValue( validationException.getMessage() );

        } );

        String str = errors
                .serializeOi()
                .setFormat( defaultStreamFormat )
                .withMappings( HttpErrorMessage.JSON_ERROR_MAPPING )
                .compressed().toString();

        return Response.status( 500 ).entity( str ).build();
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
        // By default don't allow Custom Queries.
        qual.setAllowCustomQuery( false );

        if ( StringUtils.isNotBlank( jsonQual ) )
        {
            if ( StringUtils.isNotBlank( qualOi ) )
                throw new HttpErrorMessage( "JSON and qualOi both specified for qualification" );

            qual.loadFromJsonString( jsonQual );
        }

        if ( StringUtils.isNotBlank( qualOi ) )
            qual.loadFromSerializedString( qualOi );

        return qual;
    }

    public class RestEngineHandler
    {
        private final Task               task;
        private final HttpServletRequest request;
        private final StreamFormat       format;
        private final LodDef             lodDef;
        private QualificationBuilder     qual;
        private View                     activatedView;
        private View                     viewFromBody;
        private boolean                  responseWithIncrementals = defaultResponseWithIncrementals;
        private boolean                  responseWithHiddenAttributes = defaultResponseWithHiddenAttributes;

        private RestEngineHandler( Task task, HttpServletRequest request )
        {
            this.task = task;
            this.request = request;
            this.format = interpretContentType( request.getHeader( "Content-Type" ) );
            String lodName = getRequiredParam( "lodName" );
            lodDef = task.getApplication().getLodDef( task, lodName );
        }

        public RestEngineHandler setResponseWithIncrementals( boolean incrementals )
        {
            responseWithIncrementals = incrementals;
            return this;
        }

        public RestEngineHandler setResponseWithHiddenAttributes( boolean hidden )
        {
            responseWithHiddenAttributes = hidden;
            return this;
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

        public LodDef getLodDef()
        {
            return lodDef;
        }

        public HttpServletRequest getRequest()
        {
            return request;
        }

        public QualificationBuilder getQualBuilder()
        {
            return qual;
        }

        public View getActivatedView()
        {
            return activatedView;
        }

        public View getViewFromBody()
        {
            return viewFromBody;
        }

        private View loadViewFromBody( String body )
        {
            if ( viewFromBody != null )
                return viewFromBody;

            try
            {
                viewFromBody = task.deserializeOi()
                        .setFormat( format )
                        .setLodDef( lodDef )
                        .fromString( body )
                        .withKeyValidation()
                        .activateFirst();

                return viewFromBody;
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
        public Response activateByKey( String key )
        {
            qual = new QualificationBuilder( task );
            qual.addRootQualForKey( key );

            validateActivate( this );
            activatedView = qual.activate();
            validateActivateResult( this );

            return viewToResponse( activatedView );
        }

        /**
         * Activates an OI using the qualification specified in either the
         * qual or qualOi query params.

         * @return Response object with serialized OI and status code = 200
         */
        public Response activate()
        {
            String jsonQual = getParam( "qual" );
            String qualOi = getParam( "qualOi" );
            qual = instantiateQual( task, lodDef, jsonQual, qualOi );

            validateActivate( this );
            activatedView = qual.activate();
            validateActivateResult( this );

            return viewToResponse( activatedView );
        }

        public Response viewToResponse( View view, Status status )
        {
            String serialized = view.serializeOi()
                    .withIncremental( responseWithIncrementals )
                    .setWriteHiddenAttributes( responseWithHiddenAttributes )
                    .setFormat( format )
                    .toString();
            return Response.ok( serialized ).status( status ).type( STREAM_FORMAT_TO_CONTENT_TYPE.get( format ) ).build();
        }

        public Response viewToResponse( View view )
        {
            return viewToResponse( view, Status.OK );
        }

        /**
         * Commits the OI specified in the body.
         * @param body the OI deserialized as JSON/XML.  Must have incrementals.
         * @return Response object with the resulting OI and status code = 201
         */
        public Response commit( String body )
        {
            View view = loadViewFromBody( body );
            validateCommit( this );
            view.commit();
            validateCommitResult( this );

            return viewToResponse( view, Status.CREATED );
        }

        public Response deleteByKey( String key )
        {
            qual = new QualificationBuilder( task );
            qual.addRootQualForKey( key );
            viewFromBody = qual.activate();
            viewFromBody.cursor( lodDef.getRoot() ).deleteEntity();

            validateCommit( this );
            viewFromBody.commit();
            validateCommitResult( this );

            return Response.noContent().build();
        }
    }
}
