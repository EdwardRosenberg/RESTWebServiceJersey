package org.example.resource;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Controller
@Path("/users")
public class UserResource {

    @Autowired
    private UserDao userDao;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public Response helloWorld() {
        String result = "Hello World!";
        return Response.status(200).entity(result).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        return Response.status(200).entity(userDao.getAllUsers()).build();
    }

    public List<User> getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public void createUser(User user) {
        userDao.createUser(user);
    }
}