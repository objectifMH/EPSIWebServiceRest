/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soa.jaxrslabs.helloepsiwebservicerestexercice1;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * REST Web Service
 *
 * @author epsi
 */
@Path("helloepsi")
public class HelloEpsi {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloEpsi
     */
    public HelloEpsi() {
    }

    /**
     * Retrieves representation of an instance of
     * soa.jaxrslabs.helloepsiwebservicerestexercice1.HelloEpsi
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        return "<bonjour>Bonjour EPSI</bonjour>";
    }

    @GET
    @Path("{id}")
    @Produces("application/xml")
    public Response getXmlWithParams(@PathParam("id") String id,
            @DefaultValue("all")
            @HeaderParam("name") String name) {
        System.out.println(id);
        System.out.println(name);
        return Response
                .status(Status.OK)
                .entity("<bonjour>Bonjour ENSMA de la part de " + name + "</bonjour>")
                .build();
    }

}
