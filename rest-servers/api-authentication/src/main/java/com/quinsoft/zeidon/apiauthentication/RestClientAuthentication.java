/**
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
package com.quinsoft.zeidon.apiauthentication;

import com.google.common.collect.ImmutableMap;
import com.quinsoft.zeidon.EntityCursor;
import com.quinsoft.zeidon.EntityInstance;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.http.AuthenticationDecorator;
import com.quinsoft.zeidon.http.ZeidonHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.net.URI;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RestClientAuthentication implements AuthenticationDecorator
{
    private final static DateTimeFormatter timestampFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            .withZone( ZoneId.systemDefault() );

    /**
        Keeps track of servers by address.
            key = privateKey + keyType
            value = ClientAuthentication entity instance.
     */
    private final Map<String, EntityInstance> servers;
    private final ConcurrentMap<String, PrivateKey> privateKeys = new ConcurrentHashMap<>();

    public RestClientAuthentication( View clientAuthentication )
    {
        servers = initializeServers( clientAuthentication );
    }

    private Map<String, EntityInstance> initializeServers( View clientAuthentication )
    {
        // Use map builder because it'll throw an error if there's a duplicate.
        // Same day we might need to figure out how to handle different applications
        // with different keys making requests to the same server.
        ImmutableMap.Builder<String, EntityInstance> builder = new ImmutableMap.Builder<>();
        EntityCursor root = clientAuthentication.cursor( "ClientAuthentication" );
        for ( EntityInstance client : root.eachEntity() )
        {
            if ( clientAuthentication.cursor( "Server" ).getEntityCount() == 0 )
            {
                builder.put( "*", client );
            }
            else
            {
                for ( EntityInstance server : client.getChildren( "Server" ) )
                {
                    String address = server.getAttribute( "Address" ).getString();
                    builder.put( address, client );
                }
            }
        }

        // As a safety precaution, drop the view so it's no longer accessable.
        clientAuthentication.drop();

        return builder.build();
    }

    private EntityInstance getClientAuthentication( URI uri )
    {
        String hostname = uri.getHost();
        if ( uri.getPort() > 0 )
            hostname = hostname + ":" + uri.getPort();

        if ( !servers.containsKey( hostname ) )
        {
            if ( servers.containsKey( "*" ) )
                return servers.get( "*" );

            throw new ZeidonException( "Hostname %s not listed among servers", hostname );
        }

        return servers.get( hostname );
    }

    private PrivateKey getPrivateKey( String privateKeyString, String keyType ) throws Exception
    {
        String key = privateKeyString + ":" + keyType;
        if ( privateKeys.containsKey( key ) )
            return privateKeys.get( key );

        EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec( Base64.getDecoder().decode( privateKeyString ) );
        KeyFactory keyFactory = KeyFactory.getInstance( keyType );
        PrivateKey privateKey = keyFactory.generatePrivate( privateKeySpec );
        privateKeys.putIfAbsent( key, privateKey );
        return privateKey;
    }

    private void addAuthentication( Task task, HttpRequestBase request )
    {
        EntityInstance auth = getClientAuthentication( request.getURI() );
        URI uri = request.getURI().normalize();

        String timestamp = timestampFormatter.format( Instant.now() );
        String baseUrl = uri.getRawPath();
        if ( StringUtils.isNotBlank( uri.getRawQuery() ) )
            baseUrl = baseUrl + "?" + uri.getRawQuery();

        baseUrl = baseUrl + timestamp;
        task.log().debug( "Client baseUrl = %s", baseUrl );

        String algorithm = auth.getAttribute( "SignatureAlgorithm" ).getString();
        String keyType = auth.getAttribute( "KeyType" ).getString();
        String privateKeyString = auth.getAttribute( "ClientPrivateKey" ).getString();
        String clientName = auth.getAttribute( "ClientName" ).getString();

        try
        {
            PrivateKey privateKey = getPrivateKey( privateKeyString, keyType );

            Signature ecdsaSign = Signature.getInstance( algorithm );
            ecdsaSign.initSign( privateKey );
            ecdsaSign.update( baseUrl.getBytes( "UTF-8" ) );
            byte[] signature = ecdsaSign.sign();
            String signatureStr = Base64.getEncoder().encodeToString( signature );

            request.setHeader( "Authorization", "Signature " + clientName + ":" + signatureStr );
            request.setHeader( "Date", timestamp );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    @Override
    public void addAuthentication( ZeidonHttpClient httpClient, HttpGet httpGet )
    {
        addAuthentication( httpClient.getTask(), httpGet );
    }

    @Override
    public void addAuthentication( ZeidonHttpClient httpClient, HttpPost httpPost )
    {
        addAuthentication( httpClient.getTask(), httpPost );
    }
}
