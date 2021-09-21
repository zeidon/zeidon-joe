/**
 *
 */
package com.quinsoft.zeidon.test;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.View;
import com.quinsoft.zeidon.jaxrs.ZeidonRestGateway;
import com.quinsoft.zeidon.standardoe.DefaultJavaOeConfiguration;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.utils.QualificationBuilder;
import org.apache.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author DG
 *
 */
public class TestRest
{
    private ObjectEngine restClientOe;
    private Server       jettyServer;

    @Before
    public void setUp() throws Exception
    {
        System.out.println( "CWD = " + System.getProperty( "user.dir" ) );

        // Create an object engine that sets oiSourceUrl to be the jetty server.
        restClientOe = new JavaObjectEngine( new DefaultJavaOeConfiguration()
                .setPreferencesFilename( "zeidon-rest-client.properties" ) );

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter( "jersey.config.server.provider.classnames",
                                        ZeidonRestGateway.class.getCanonicalName() );

        // Start Server
        jettyServer.start();
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
            System.out.println();
        } );
    }
}
