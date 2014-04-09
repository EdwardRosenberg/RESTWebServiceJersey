package org.example.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("UserInfoService")
public class UserInfoService {


	@GET
	@Path("/name/{userName}")
	@Produces(MediaType.TEXT_XML)
	public String userName(@PathParam("userName") String userName) {
		String name = userName;
		return "<User>" + "<Name>" + name + "</Name>" + "</User>";
	}

	@GET 
	@Path("/age/{userAge}") 
	@Produces(MediaType.TEXT_XML)
	public String userAge(@PathParam("userAge") int userAge) {
		int age = userAge;
		return "<User>" + "<Age>" + age + "</Age>" + "</User>";
	}
}