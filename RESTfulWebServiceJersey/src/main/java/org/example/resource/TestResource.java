package org.example.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hello")
public class TestResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloWorld() {
        String result = "Hello World!";
        return Response.ok().entity(result).build();
    }

}
