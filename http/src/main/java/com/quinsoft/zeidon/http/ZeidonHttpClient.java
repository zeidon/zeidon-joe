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

import com.quinsoft.zeidon.DeserializeOi;
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.objectdefinition.LodDef;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * HTTP wrapper to make it easy to make GET and POST calls with Zeidon objects.
 * Uses a connection pool that is configured using zeidon.ini.
 *
 */
public class ZeidonHttpClient
{
    private final Task                task;
    private final CloseableHttpClient httpClient;
    private String                    url;
    private View                      sourceView;
    private StreamFormat              format = StreamFormat.JSON;
    private View                      qualOi;
    private AuthenticationDecorator   authenticationDecorator;
    private LodDef                    responseLodDef; // LodDef of the OI being returned.

    ZeidonHttpClient( Task task, CloseableHttpClient httpClient )
    {
        this.task = task;
        this.httpClient = httpClient;
    }

    public ZeidonHttpClient setSourceView( View sourceView )
    {
        this.sourceView = sourceView;
        responseLodDef = sourceView.getLodDef();
        return this;
    }

    public ZeidonHttpClient setUrl( String url )
    {
        this.url = url;
        return this;
    }

    public ZeidonHttpClient setFormat( StreamFormat format )
    {
        this.format = format;
        return this;
    }

    public ZeidonHttpClient setQualParam( View qualOi )
    {
        this.qualOi = qualOi;
        return this;
    }

    public ZeidonHttpClient setAuthenticationDecorator( AuthenticationDecorator authenticationDecorator )
    {
        this.authenticationDecorator = authenticationDecorator;
        return this;
    }

    public Task getTask()
    {
        return task;
    }

    public ZeidonHttpClientResponse callGet( LodDef responseLodDef )
    {
        if ( sourceView != null )
            throw new ZeidonException( "SourceView is not allowed for GET" );

        this.responseLodDef = responseLodDef;

        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException("URL is required for GET");

        UriBuilder builder = UriBuilder.fromUri( url );

        if ( qualOi != null )
        {
            String qualStr = qualOi.serializeOi().asJson().compressed().withIncremental().toString();
            builder.queryParam( "qualOi", qualStr );
        }

        URI uri = builder.build();
        CloseableHttpResponse response = null;

        try
        {
            HttpGet httpGet = new HttpGet( uri );
            httpGet.setHeader( "Content-type", MediaType.APPLICATION_JSON );
            if ( authenticationDecorator != null )
                authenticationDecorator.addAuthentication( this, httpGet );

            response = httpClient.execute( httpGet );
            return new ZeidonHttpClientResponse( task, response, StreamFormat.JSON );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "URL = %s", url );
        }
    }

    public ZeidonHttpClientResponse callPost()
    {
        if ( sourceView == null )
            throw new ZeidonException( "SourceView is required for POST" );

        if ( StringUtils.isBlank( url ) )
            throw new ZeidonException( "URL is required for POST" );

        try
        {
            sourceView.log().debug( "Sending POST to url %s", url );
            HttpPost httpPost = new HttpPost( url );
            String body;
            switch ( format )
            {
                case JSON:
                    body = sourceView.toJson( true );
                    httpPost.setHeader( "Content-Type", "application/json" );
                    break;

                case XML:
                    body = sourceView.serializeOi().asXml().withIncremental().toString();
                    httpPost.setHeader( "Content-Type", "application/xml" );
                    break;

                case POR:
                    body = sourceView.serializeOi().withIncremental().toString();
                    httpPost.setHeader( "Content-Type", "application/text" );
                    break;

                default:
                    throw new ZeidonException( "Unsupported format for POST body: %s", format );
            }

            StringEntity entity = new StringEntity( body );
            httpPost.setEntity( entity );
            if ( authenticationDecorator != null )
                authenticationDecorator.addAuthentication( this, httpPost );

            CloseableHttpResponse response = httpClient.execute( httpPost );
            return new ZeidonHttpClientResponse( task, response, format );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    public class ZeidonHttpClientResponse
    {
        private final int  statusCode;
        private final View returnView;

        private ZeidonHttpClientResponse( Task task, CloseableHttpResponse response, StreamFormat format )
        {
            String body = null;
            try
            {
                StatusLine status = response.getStatusLine();
                statusCode = status.getStatusCode();
                if ( statusCode < 200 || statusCode > 299 )
                    throw HttpErrorMessage.fromResponse( task, response );

                InputStream stream = response.getEntity().getContent();
                body = IOUtils.toString( stream, StandardCharsets.UTF_8.name() );

                returnView = new DeserializeOi( task )
                        .setFormat( format )
                        .setLodDef( responseLodDef )
                        .fromString( body )
                        .activateFirst();
            }
            catch ( Exception e )
            {
                if ( body != null )
                    task.log().error( "body = %s", body );

                throw ZeidonException.wrapException( e );
            }
            finally
            {
                EntityUtils.consumeQuietly( response.getEntity() );
                IOUtils.closeQuietly( response );
            }
        }

        public View getResponseView()
        {
            return returnView;
        }

        public int getStatusCode()
        {
            return statusCode;
        }
    }
}
