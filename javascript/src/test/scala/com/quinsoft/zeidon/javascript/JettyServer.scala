package com.quinsoft.zeidon.javascript

import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import com.quinsoft.zeidon.jaxrs.ZeidonRestGateway

object JettyServer {
    def main(args: Array[String]) {
        val context = new ServletContextHandler(ServletContextHandler.SESSIONS)
        context.setContextPath("/")

        val jettyServer = new Server(8080)
        jettyServer.setHandler(context)

        val jerseyServlet = context.addServlet(
            classOf[ org.glassfish.jersey.servlet.ServletContainer ], "/*")
        jerseyServlet.setInitOrder(0)

        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter( "jersey.config.server.provider.classnames",  classOf[ ZeidonRestGateway ].getCanonicalName() )

        try {
            jettyServer.start()
            jettyServer.join()
        } finally {
            jettyServer.destroy()
        }
    }
}
