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

import com.quinsoft.zeidon.Application;
import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.HashMap;
import java.util.Map;

public class ZeidonHttpClientFactory
{
    /**
     * Keep a cache with a HttpClient for each app.  The HttpClients themselves have
     * a connection pool.
     */
    private final Map<String, CloseableHttpClient> httpClientCache = new HashMap<>();
    private AuthenticationDecorator authenticationDecorator;

    public final static synchronized ZeidonHttpClientFactory getInstance( ObjectEngine oe )
    {
        ZeidonHttpClientFactory httpClientFactory = oe.getCacheMap().get( ZeidonHttpClientFactory.class );
        if ( httpClientFactory == null )
        {
            httpClientFactory = new ZeidonHttpClientFactory();
            oe.getCacheMap().put( httpClientFactory );
        }

        return httpClientFactory;
    }

    public ZeidonHttpClientFactory()
    {
    }

    public ZeidonHttpClient getClient( Task task )
    {
        return new ZeidonHttpClient( task, getCachedHttpClient( task ) )
                .setAuthenticationDecorator( authenticationDecorator );
    }

    public ZeidonHttpClient getClient( View sourceView )
    {
        return getClient( sourceView.getTask() )
                .setSourceView( sourceView );
    }

    private synchronized CloseableHttpClient getCachedHttpClient( Task task )
    {
        Application application = task.getApplication();
        String appName = application.getName();
        CloseableHttpClient client = httpClientCache.get( appName );
        if ( client != null )
            return client;

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
            connectionPoolSize = Integer
                    .parseInt( task.readZeidonConfig( group, "ConnectionPoolSize", "5" ) );
        }
        catch ( Exception e )
        {
            throw new ZeidonException( "Error reading HTTP config: " + e.getMessage() )
                    .appendMessage( "Group = %s, key = 'ConnectionPoolSize'", group );
        }

        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( timeout )
                .setConnectTimeout( timeout ).setConnectionRequestTimeout( timeout ).build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal( connectionPoolSize );
        cm.setDefaultMaxPerRoute( connectionPoolSize );
        client = HttpClients.custom().setConnectionManager( cm ).setDefaultRequestConfig( requestConfig )
                .build();
        httpClientCache.put( appName, client );
        return client;
    }

    public ZeidonHttpClientFactory setAuthenticationDecorator( AuthenticationDecorator decorator )
    {
        this.authenticationDecorator = decorator;
        return this;
    }

    private void loadAuthenticationDecorator( Task task, String group )
    {
        String decorator = task.readZeidonConfig( group, "ClientAuthenticationDecorator" );
        if ( StringUtils.isBlank( decorator ) )
            return;

        ClassLoader classLoader = task.getObjectEngine().getClassLoader( decorator );
        try
        {
            @SuppressWarnings("unchecked")
            Class<? extends AuthenticationDecorator> decoratorClass = (Class<? extends AuthenticationDecorator>)
                    classLoader.loadClass( decorator );
            authenticationDecorator = decoratorClass.getDeclaredConstructor().newInstance();
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }
}
