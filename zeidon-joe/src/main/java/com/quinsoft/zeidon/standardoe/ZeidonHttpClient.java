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

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.ZeidonException;

/**
 * Wrapper around Apache HTTP client code.  This is configured using zeidon.ini.
 *
 */
class ZeidonHttpClient
{
    private final RequestConfig requestConfig;
    private final CloseableHttpClient httpClient;

    static ZeidonHttpClient getClient( Task task, Application application )
    {
        synchronized ( application )
        {
            ZeidonHttpClient client = application.getCacheMap().get( ZeidonHttpClient.class );
            if ( client == null )
            {
                client = new ZeidonHttpClient( task, application );
                application.getCacheMap().put( client );
            }

            return client;
        }
    }

    /**
     *
     */
    ZeidonHttpClient( Task task, Application application )
    {
        String group = application.getName() + ".Http";
        int timeout;
        try
        {
            timeout = Integer.parseInt( task.readZeidonConfig( group, "HttpTimeout", "2000" ) );
        }
        catch ( Exception e )
        {
            throw new ZeidonException( "Error reading HTTP config: " + e.getMessage() )
                        .appendMessage( "Group = %s, key = 'HttpTimeout'", group );
        }

        int connectionPoolSize;
        try
        {
            connectionPoolSize = Integer.parseInt( task.readZeidonConfig( group, "ConnectionPoolSize", "5" ) );
        }
        catch ( Exception e )
        {
            throw new ZeidonException( "Error reading HTTP config: " + e.getMessage() )
                        .appendMessage( "Group = %s, key = 'ConnectionPoolSize'", group );
        }

        requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout)
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal( connectionPoolSize );
        cm.setDefaultMaxPerRoute( connectionPoolSize );
        httpClient = HttpClients.custom().setConnectionManager( cm ).build();
    }

    CloseableHttpResponse callGet( String url )
    {
        try
        {
            URI urlObject = new URI( url );
            HttpGet httpGet = new HttpGet( urlObject );
            httpGet.setConfig( requestConfig );

            return httpClient.execute(httpGet);
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e ).appendMessage( "URL = %s", url );
        }
    }

    CloseableHttpResponse callPost( HttpPost httpPost )
    {
        try
        {
            httpPost.setConfig( requestConfig );
            return httpClient.execute(httpPost);
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }
}
