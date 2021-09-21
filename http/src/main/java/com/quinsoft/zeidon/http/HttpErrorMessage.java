/**
 * This file is part of the Zeidon Java Object Engine (Zeidon JOE).
 *
 * Zeidon JOE is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Zeidon JOE is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Zeidon JOE. If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2015 QuinSoft
 */
package com.quinsoft.zeidon.http;

import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.SerializationMapping;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import org.apache.commons.io.IOUtils;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import javax.ws.rs.core.Response.Status;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HttpErrorMessage extends ZeidonException
{
    private static final long serialVersionUID = 1L;

    public final static SerializationMapping JSON_ERROR_MAPPING = new SerializationMapping()
            .addEntityMapping( "RestResponse", "errors" )
            .addAttributeMapping( "RestResponse", "ErrorCode", "error_code" )
            .addAttributeMapping( "RestResponse", "ErrorMessage", "error_message" );

    private String errorCode = "error";
    private int    statusCode = Status.BAD_REQUEST.getStatusCode();

    public HttpErrorMessage( String format, Object... strings )
    {
        super( format, strings );
    }

    public HttpErrorMessage withStatusCode( int statusCode )
    {
        this.statusCode = statusCode;
        return this;
    }

    public HttpErrorMessage withStatusCode( Status status )
    {
        this.statusCode = status.getStatusCode();
        return this;
    }

    public HttpErrorMessage withErrorCode( String errorCode )
    {
        this.errorCode = errorCode;
        return this;
    }

    public int getStatusCode()
    {
        return statusCode;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    static public HttpErrorMessage fromResponse( Task task, CloseableHttpResponse response ) throws Exception
    {
        InputStream stream = response.getEntity().getContent();
        String body = IOUtils.toString( stream, StandardCharsets.UTF_8.name() );

        StatusLine status = response.getStatusLine();
        int statusCode = status.getStatusCode();

        if ( response.getHeaders( "X-Zeidon-Error" ).length == 0 )
            return new HttpErrorMessage( "%s", body )
                    .withStatusCode( statusCode );

        View errors = task.getSystemTask().deserializeOi()
                .setLodDef( "kzrestresponse" )
                .fromString( body )
                .asJson()
                .withMappings( JSON_ERROR_MAPPING )
                .activateFirst();

        EntityCursor cursor = errors.cursor( "RestResponse" );
        return new HttpErrorMessage( "%s", cursor.getAttribute( "ErrorMessage" ).getString() )
                .withErrorCode( cursor.getAttribute( "ErrorCode" ).getString() )
                .withStatusCode( statusCode );
    }
}
