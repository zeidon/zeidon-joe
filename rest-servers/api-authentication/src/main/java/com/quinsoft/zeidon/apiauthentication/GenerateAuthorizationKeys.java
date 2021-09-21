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

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.Task;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class GenerateAuthorizationKeys
{
    public static void main(String[] args) throws Exception
    {
        if ( args.length < 2 )
        {
            System.out.println("Usage:\n    CLIENT-NAME TARGET-DIR");
            return;
        }

        GenerateAuthorizationKeys generator = new GenerateAuthorizationKeys( args[ 0 ], args[ 1 ] );
        generator.generateKeys();
        return;
    }

    private final ObjectEngine oe = JavaObjectEngine.getInstance();
    private final String       clientName;
    private final String       targetFile;
    private final String       keyType = "EC";

    public GenerateAuthorizationKeys( String clientName, String targetDir )
    {
        this.clientName = clientName;
        this.targetFile = targetDir;
    }

    private void generateKeys() throws Exception
    {
        Task task = oe.createTask( "RestApiAuthentication" );
        View config = task.activateEmptyObjectInstance( "ClientAuthentication" );
        config.cursor( "ClientAuthentication" )
                .createEntity()
                .getAttribute( "ClientName" ).setValue( clientName )
                .getAttribute( "SignatureAlgorithm" ).setValue( "SHA256withECDSA" )
                .getAttribute( "KeyType" ).setValue( keyType );

        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        KeyPairGenerator g = KeyPairGenerator.getInstance( keyType );
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();
        String publicKeyString = Base64.getEncoder().encodeToString( publicKey.getEncoded() );
        String privateKeyString = Base64.getEncoder().encodeToString( privateKey.getEncoded() );
        config.cursor("ClientAuthentication")
                .getAttribute( "ClientPublicKey" ).setValue( publicKeyString )
                .getAttribute( "ClientPrivateKey" ).setValue( privateKeyString );

        config.serializeOi().toFile( targetFile );
    }
}
