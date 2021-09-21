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
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.ZeidonLogger;
import com.quinsoft.zeidon.http.AuthorizationError;
import com.quinsoft.zeidon.http.HttpErrorMessage;
import com.quinsoft.zeidon.http.ZeidonRestServerEngine;
import com.quinsoft.zeidon.http.ZeidonRestServerEngine.RestEngineHandler;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class RestServerAuthentication implements ZeidonRestServerEngine.RestValidator
{
    private final static DateTimeFormatter timestampFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
            .withZone( ZoneId.systemDefault() );

    private final View clientAuthentication;
    private final ConcurrentMap<String, PublicKey> publicKeys = new ConcurrentHashMap<>();

    /**
        Keeps track of servers by address.
            key = publicKey + keyType
            value = ClientAuthentication entity instance.
     */
    private final Map<String, EntityInstance> servers;

    public RestServerAuthentication( View clientAuthentication )
    {
        this.clientAuthentication = clientAuthentication;
        servers = initializeServers();
    }

    private Map<String, EntityInstance> initializeServers()
    {
        // Use map builder because it'll throw an error if there's a duplicate.
        ImmutableMap.Builder<String, EntityInstance> builder = new ImmutableMap.Builder<>();
        EntityCursor root = clientAuthentication.cursor( "ClientAuthentication" );
        for ( EntityInstance client : root.eachEntity() )
        {
            String clientName = client.getAttribute( "ClientName" ).getString();
            builder.put( clientName, client );
        }

        return builder.build();
    }

    @Override
    public void validate( RestEngineHandler handler )
    {
        try
        {
            authenticate( handler );
        }
        catch ( Exception e )
        {
            throw ZeidonException.wrapException( e );
        }
    }

    private PublicKey getPublicKey( String publicKeyString, String keyType ) throws Exception
    {
        String key = publicKeyString + ":" + keyType;
        if ( publicKeys.containsKey( key ) )
            return publicKeys.get( key );

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec( Base64.getDecoder().decode( publicKeyString ) );
        KeyFactory keyFactory = KeyFactory.getInstance( keyType );
        PublicKey publicKey = keyFactory.generatePublic( publicKeySpec );
        publicKeys.putIfAbsent( key, publicKey );
        return publicKey;
    }

    private void authenticate( RestEngineHandler handler ) throws Exception
    {
        ZeidonLogger logger = handler.getTask().log();

        HttpServletRequest request = handler.getRequest();
        String authorization = request.getHeader( "Authorization" );
        if ( StringUtils.isBlank( authorization ) )
            throw new AuthorizationError( "Authorization is required" );

        String[] tokens = authorization.split( "[ :]" );

        if ( tokens.length != 3 )
            throw new HttpErrorMessage( "Expected Authorization header to be of form 'Signature client-name:signature' but was %s",
                                          authorization );

        if ( StringUtils.compareIgnoreCase( "Signature", tokens[0] ) != 0 )
            throw new HttpErrorMessage( "Expected Authorization header auth-scheme to be 'Signature' but was %s",
                                        tokens[ 0 ] );

        String clientName = tokens[ 1 ];
        EntityInstance clientConfig = servers.get( clientName );
        if ( clientConfig == null )
            throw new HttpErrorMessage( "Unknown client name '%s' in Authorization header", clientName );

        String timestampStr = request.getHeader( "Date" );
        Instant timestamp = Instant.from( timestampFormatter.parse( timestampStr ) );
        Instant now = Instant.now().minusSeconds( 30 );
        if ( timestamp.compareTo( now ) < 0 )
            throw new AuthorizationError( "Authorization timestamp expiration" );

        String signature = tokens[ 2 ];

        String publicKeyString = clientConfig.getAttribute( "ClientPublicKey" ).getString();
        String keyType = clientConfig.getAttribute( "KeyType" ).getString();

        PublicKey publicKey = getPublicKey( publicKeyString, keyType );

        String baseUrl = request.getRequestURI();
        if ( StringUtils.isNotBlank( request.getQueryString() ) )
            baseUrl = baseUrl + "?" + request.getQueryString();

        baseUrl = baseUrl + timestampStr;
        logger.debug( "Server baseUrl = %s", baseUrl );

        String algorithm = clientConfig.getAttribute( "SignatureAlgorithm" ).getString();
        Signature ecdsaVerify = Signature.getInstance( algorithm );
        ecdsaVerify.initVerify( publicKey );
        ecdsaVerify.update( baseUrl.getBytes( "UTF-8" ) );
        boolean result = ecdsaVerify.verify( Base64.getDecoder().decode( signature ) );

        if ( ! result )
        {
            logger.warn( "Request has failed authorization" );
            throw new AuthorizationError( "Invalid signature" );
        }
    }
}
