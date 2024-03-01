package org.example.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hello")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloWorld() {
        String result = "Hello World!";
        return Response.ok().entity(result).build();
    }

}
