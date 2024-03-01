package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Path("/user")
public class UserResource {

	private UserDao userDao;

	@Autowired
	public UserResource(UserDao userDao) {
		this.userDao = userDao;
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAllUsers() {
		List<User> users = userDao.getAllUsers();
		return Response.ok().entity(new GenericEntity(users) {}).build();
	}

	@GET
	@Path("/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getUser(@PathParam("id") long id) {
		return Response.ok().entity(userDao.getUser(id)).build();
	}

	@POST
	@Path("/search")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response searchUsers(User user) {
		List<User> users = userDao.searchUsers(user);
		return Response.ok().entity(new GenericEntity<List<User>>(users) {}).build();
	}

	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createUser(User user) {
		userDao.createUser(user);
		return Response.ok().entity(user).build();
	}
}