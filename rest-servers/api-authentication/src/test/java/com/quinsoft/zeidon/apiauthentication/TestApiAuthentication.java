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
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.http.HttpErrorMessage;
import com.quinsoft.zeidon.http.ZeidonHttpClientFactory;
import com.quinsoft.zeidon.http.ZeidonRestServerEngine;
import com.quinsoft.zeidon.jaxrs.ZeidonRestGateway;
import com.quinsoft.zeidon.standardoe.DefaultJavaOeConfiguration;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import org.apache.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.function.Function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * @author DG
 *
 */
public class TestApiAuthentication
{
    private Server       jettyServer;

    @Before
    public void setUp() throws Exception
    {
        System.out.println( "CWD = " + System.getProperty( "user.dir" ) );

        ServletContextHandler context = new ServletContextHandler( ServletContextHandler.SESSIONS );
        context.setContextPath( "/" );

        jettyServer = new Server( 8080 );
        jettyServer.setHandler( context );

        ObjectEngine oe = JavaObjectEngine.getInstance();
        ZeidonRestServerEngine restEngine = new ZeidonRestServerEngine( oe );
        View serverAuthenticationConfig = createClientAuthenticationOi( oe );
        RestServerAuthentication authenticator = new RestServerAuthentication( serverAuthenticationConfig );
        restEngine.addActivateValidation( authenticator );
        restEngine.addCommitValidation( authenticator );

        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register( new ZeidonRestGateway( oe, restEngine ) );
        ServletContainer container = new ServletContainer( resourceConfig );
        ServletHolder jerseyServlet = new ServletHolder( container );
        context.addServlet( jerseyServlet, "/*" );

        jerseyServlet.setInitOrder( 0 );

        // Start Server
        jettyServer.start();
    }

    private ObjectEngine createClientObjectEngine(Function<ObjectEngine, View> proc )
    {
        JavaObjectEngine oe = new JavaObjectEngine( new DefaultJavaOeConfiguration()
                .setPreferencesFilename( "zeidon-rest-client-authentication.properties" ) );
        View clientAuthenticationConfig = proc.apply( oe );
        RestClientAuthentication decorator = new RestClientAuthentication( clientAuthenticationConfig );
        ZeidonHttpClientFactory clientFactory = oe.getInjector().get( ZeidonHttpClientFactory.class );
        clientFactory.setAuthenticationDecorator( decorator );
        return oe;
    }

    private View createClientAuthenticationOi( ObjectEngine oe )
    {
        View config = oe.getSystemTask()
                        .activateEmptyObjectInstance( "ClientAuthentication", "RestApiAuthentication" );
        config.cursor("ClientAuthentication")
            .createEntity()
            .getAttribute("ClientName").setValue("Test")
            .getAttribute("SignatureAlgorithm").setValue("SHA256withECDSA")
            .getAttribute( "KeyType" ).setValue( "EC" )
            .getAttribute( "ClientPublicKey" ).setValue( "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEK3oAJIfw+yPunvB1cth2CnPI0sT6/JGSt3HJ2gcb98/A2iumbIJEcxnbEbFcj9YSH+Ohf3X+bYmau2n+tnV7yw==" )
            .getAttribute( "ClientPrivateKey" ).setValue( "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCDHGA+od6+2ofMrKaRhHNLIWRU/tEglmPvGwMVObSAxhw==" );

        return config;
    }

    private View createClientAuthenticationOi2( ObjectEngine oe )
    {
        View config = oe.getSystemTask()
                        .activateEmptyObjectInstance( "ClientAuthentication", "RestApiAuthentication" );
        config.cursor("ClientAuthentication")
            .createEntity()
            .getAttribute("ClientName").setValue("Test")
            .getAttribute("SignatureAlgorithm").setValue("SHA256withECDSA")
            .getAttribute( "KeyType" ).setValue( "EC" )
            .getAttribute( "ClientPublicKey" ).setValue( "MFYwEAYHKoZIzj0CAQYFK4EEAAoDQgAEtdI+sXKwxyfFjHBniTP/q2AxskiWK5bOShNyfYGyicKmzlkd7RJ/sW0vdadtr7z6EZU1V0xGWZEusaV0Wu3eVw==" )
            .getAttribute( "ClientPrivateKey" ).setValue( "MD4CAQAwEAYHKoZIzj0CAQYFK4EEAAoEJzAlAgEBBCDduhpwvxYh6qsmULG5b9o4G+Qm/b1Jb5sybYxFYwTktg==" );

        return config;
    }

    @After
    public void stopJetty()
    {
        try
        {
            jettyServer.stop();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() throws Exception
    {
        HttpURLConnection http = (HttpURLConnection) new URL( "http://localhost:8080/zeidon-api/echo/this-is-a-test" ).openConnection();
        http.connect();
        assertThat( "Response Code", http.getResponseCode(), is( HttpStatus.SC_OK ) );
    }

    @Test
    public void testRestActivateCommit()
    {
        ObjectEngine restClientOe = createClientObjectEngine( o -> createClientAuthenticationOi( o ) );
        restClientOe.withTask( "Northwind", ( task ) -> {
            String jsonQual = "{\"OrderId\": 10250}";
            View order = QualificationBuilder.activateFromJson( task, "Order", jsonQual );
            assertThat( "Returned view has something", order.getEntityCount( false ), is( 13 ) );
            order.cursor( "Order" ).getAttribute( "ShipName" ).setValue( "New Name" );
            order.commit();

            View newOrder = QualificationBuilder.activateFromJson( task, "Order", jsonQual );
            assertThat( "ShipName was changed",
                        newOrder.cursor( "Order" ).getAttribute( "ShipName" ).getString(),
                        is( "New Name" ) );
        } );
    }

    @Test
    public void testDifferentKeys()
    {
        ObjectEngine oe = createClientObjectEngine( o -> createClientAuthenticationOi2( o ) );

        oe.withTask( "Northwind", ( task ) -> {
            String jsonQual = "{\"OrderId\": 10250}";
            try
            {
                QualificationBuilder.activateFromJson( task, "Order", jsonQual );
                throw new RuntimeException( "Should have thrown an exception" );
            }
            catch ( HttpErrorMessage e )
            {
                assertThat( "Response Code", e.getStatusCode(), is( HttpStatus.SC_FORBIDDEN ) );
                assertThat( "Error code", e.getErrorCode(), is( "authorization" ) );
                assertThat( "Error message", e.getMessage(), startsWith( "Invalid signature" ) );
            }
        } );
    }

    @Test
    public void testNoKeys()
    {
        JavaObjectEngine oe = new JavaObjectEngine( new DefaultJavaOeConfiguration()
                .setPreferencesFilename( "zeidon-rest-client-authentication.properties" ) );

        oe.withTask( "Northwind", ( task ) -> {
            String jsonQual = "{\"OrderId\": 10250}";
            try
            {
                QualificationBuilder.activateFromJson( task, "Order", jsonQual );
                throw new RuntimeException( "Should have thrown an exception" );
            }
            catch ( HttpErrorMessage e )
            {
                assertThat( "Response Code", e.getStatusCode(), is( HttpStatus.SC_FORBIDDEN ) );
                assertThat( "Error code", e.getErrorCode(), is( "authorization" ) );
                assertThat( "Error message", e.getMessage(), startsWith( "Authorization is required" ) );
            }
        } );
    }
}
