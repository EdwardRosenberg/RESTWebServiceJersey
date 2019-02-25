package org.example.resource;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/user")
public class UserResource {

    @Autowired
    private UserDao userDao;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        List<User> users = userDao.getAllUsers();
        return Response.ok().entity(new GenericEntity<List<User>>(users) {}).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public User getUser(@PathParam("id") long id){
        return userDao.getUser(id);
    }

    @POST
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> searchUsers(User user){
        return userDao.searchUsers(user);
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createUser(User user) {
        userDao.createUser(user);
    }
}