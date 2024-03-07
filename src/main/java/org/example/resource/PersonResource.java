package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.dao.PersonDao;
import org.example.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Path("/person")
public class PersonResource {

    private PersonDao personDao;

    @Autowired
    public PersonResource(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllUsers() {
        List<Person> people = personDao.getAll();
        return Response.ok().entity(new GenericEntity(people) {
        }).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getUser(@PathParam("id") long id) {
        return Response.ok().entity(personDao.get(id)).build();
    }

    @POST
    @Path("/search")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchUsers(Person person) {
        List<Person> people = personDao.search(person);
        return Response.ok().entity(new GenericEntity<List<Person>>(people) {
        }).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response createUser(Person person) {
        personDao.create(person);
        return Response.ok().entity(person).build();
    }
}