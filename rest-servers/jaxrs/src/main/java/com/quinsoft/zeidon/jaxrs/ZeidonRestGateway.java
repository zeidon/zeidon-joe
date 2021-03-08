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
package com.quinsoft.zeidon.jaxrs;

import com.quinsoft.zeidon.ObjectEngine;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import com.quinsoft.zeidon.standardoe.ZeidonRestEngine;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/zeidon-api")
public class ZeidonRestGateway
{
    private final ObjectEngine     oe;
    private final ZeidonRestEngine restEngine;


    public ZeidonRestGateway()
    {
        this.oe = JavaObjectEngine.getInstance();
        this.restEngine = new ZeidonRestEngine( oe );
    }

    @GET
    @Path("/echo/{echoString}")
    @Produces("application/text")
    public Response echo( @PathParam("echoString") String echoString )
    {
        return Response.ok( echoString ).build();
    }

    @GET
    @Path("/{applicationName}/{lodName}")
    @Produces({"application/xml", "application/json"})
    public Response activate( @Context HttpServletRequest request,
                              @PathParam("applicationName") String applicationName,
                              @PathParam("lodName")         String lodName,
                              @QueryParam("qual")           String jsonQual,
                              @QueryParam("qualOi")         String qualOi )
    {
        // Since these are path params and not query params these need to be added to the attributes.
        request.setAttribute("applicationName", applicationName );
        request.setAttribute( "lodName", lodName );

        return restEngine.withTask(request, (handler) -> {
            return handler.activate();
        } );
    }

    @GET
    @Path("/{applicationName}/{lodName}/{key}")
    @Produces({"application/xml", "application/json"})
    public Response activateByKey( @Context HttpServletRequest request,
                                   @PathParam("applicationName") String applicationName,
                                   @PathParam("lodName")         String lodName,
                                   @PathParam("key")             String key )
    {
        // Since these are path params and not query params these need to be added to the attributes.
        request.setAttribute("applicationName", applicationName );
        request.setAttribute( "lodName", lodName );

        return restEngine.withTask(request, (handler) -> {
            return handler.activate( key );
        } );
    }

    @POST
    @Path("/{applicationName}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Response commit( @Context HttpServletRequest request,
                            @PathParam("applicationName") String applicationName,
                            String body )
    {
        oe.getSystemTask().log().debug( "Commit ====================== " + applicationName );
        // Since these are path params and not query params these need to be added to the attributes.
        request.setAttribute("applicationName", applicationName );

        return restEngine.withTask(request, (handler) -> {
            return handler.commit( body );
        } );
    }
}
