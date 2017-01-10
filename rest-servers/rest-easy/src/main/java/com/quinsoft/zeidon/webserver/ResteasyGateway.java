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
package com.quinsoft.zeidon.webserver;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.standardoe.DefaultJavaOeConfiguration;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.standardoe.JavaOeConfiguration;
import com.quinsoft.zeidon.utils.RestServerImplementation;
import com.quinsoft.zeidon.utils.RestServerImplementation.DataFormat;

/**
 * A simple RESTEasy service gateway for activating and committing OIs.
 *
 * @author dgc
 *
 */
@Path("/")
public class ResteasyGateway extends Application
{
    @Resource private JavaOeConfiguration oeConfig;
    @Resource private ObjectEngine objectEngine; // = JavaObjectEngine.getInstance();
    private final RestServerImplementation restImpl;

    public ResteasyGateway( @Context ServletContext servletContext )
    {
        if ( objectEngine == null )
        {
            if ( oeConfig == null )
            {
                String contextPath = servletContext.getContextPath();
                oeConfig = new DefaultJavaOeConfiguration().setJmxAppName( contextPath.substring( 1 ) );
            }

            objectEngine = new JavaObjectEngine( oeConfig );
        }
        
        restImpl = new RestServerImplementation( objectEngine );
    }

    @GET
    @Path("/activate")
    @Produces("text/plain")
    public String activateGet( @QueryParam("application") String applicationName,
                               @QueryParam("lodDefName")  String lodDefName )
    {
        return "<zOI></zOI>";
    }

    @POST
    @Path("/activate")
    @Produces("application/json")
    @Consumes("application/json")
    public String activatePost( @QueryParam("application") String applicationName,
                                @QueryParam("lodDefName")  String lodDefName,
                                String content )
    {
        return restImpl.activate( applicationName, lodDefName, content, DataFormat.JSON );
    }

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello()
    {
        return "Hello Zeidon World";
    }

    @GET
    @Path("/echo/{message}")
    @Produces("text/plain")
    public String echo( @PathParam("message") String message )
    {
        return message;
    }

    public ObjectEngine getObjectEngine()
    {
        return objectEngine;
    }

    public void setObjectEngine( ObjectEngine objectEngine )
    {
        this.objectEngine = objectEngine;
    }
}
