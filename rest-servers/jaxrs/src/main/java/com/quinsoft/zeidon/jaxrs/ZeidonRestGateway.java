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
import com.quinsoft.zeidon.StreamFormat;
import com.quinsoft.zeidon.ZeidonException;
import com.quinsoft.zeidon.standardoe.JavaObjectEngine;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    public Response activate( @PathParam("applicationName") String applicationName,
                              @PathParam("lodName")         String lodName,
                              @HeaderParam("content-type")  String contentType,
                              @QueryParam("qual")           String jsonQual,
                              @QueryParam("qualOi")         String qualOi )
    {
        return restEngine.activate( applicationName, lodName, jsonQual, qualOi, interpretContentType( contentType ) );
    }

    @GET
    @Path("/{applicationName}/{lodName}/{key}")
    @Produces({"application/xml", "application/json"})
    public Response activateByKey( @PathParam("applicationName") String applicationName,
                                   @PathParam("lodName")         String lodName,
                                   @HeaderParam("content-type")  String contentType,
                                   @PathParam("key")             String key )
    {
        oe.getSystemTask().log().debug( "ActivateKey %s", applicationName );
        String jsonQual = "{\root\": {\"key\": \"" + key + "\"}}";
        return restEngine.activate( applicationName, lodName, jsonQual, null, interpretContentType( contentType ) );
    }

    @POST
    @Path("/{applicationName}")
    @Consumes({"application/xml", "application/json"})
    @Produces({"application/xml", "application/json"})
    public Response commit( @PathParam("applicationName") String applicationName,
                            @HeaderParam("content-type") String contentType,
                            String body )
    {
        oe.getSystemTask().log().debug( "Commit %s", applicationName );
        return restEngine.commit( applicationName, body, interpretContentType( contentType ) );
    }

    private StreamFormat interpretContentType( String contentType )
    {
        if ( StringUtils.isBlank( contentType ) )
            return null;

        switch ( contentType )
        {
            case MediaType.APPLICATION_XML:
                return StreamFormat.XML;

            case MediaType.APPLICATION_JSON:
                return StreamFormat.JSON;

            default:
                throw new ZeidonException( "Unsupported content-type: %s", contentType );
        }
    }
}
