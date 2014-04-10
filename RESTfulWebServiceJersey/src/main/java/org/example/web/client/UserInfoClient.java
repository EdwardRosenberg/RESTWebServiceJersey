package org.example.web.client;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UserInfoClient {

	public static final String BASE_URI = "http://localhost:8080/RESTfulWebServiceJersey";
	public static final String PATH_NAME = "/UserInfoService/name/";
	public static final String PATH_AGE = "/UserInfoService/age/";
	
	private static final Logger log = Logger.getLogger(UserInfoClient.class);
	
	public static void main(String[] args) {
		
		String name = "John";
		int age = 25;

		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource resource = client.resource(BASE_URI);

		WebResource nameResource = resource.path("rest").path(PATH_NAME + name);
		log.debug("Client Response \n"
				+ getClientResponse(nameResource));
		log.debug("Response \n" + getResponse(nameResource) + "\n\n");

		WebResource ageResource = resource.path("rest").path(PATH_AGE + age);
		log.debug("Client Response \n"
				+ getClientResponse(ageResource));
		log.debug("Response \n" + getResponse(ageResource));
	}

	/**
	 * Returns client response status
	 * 
	 */
	private static String getClientResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class)
				.toString();
	}

	/**
	 * Returns client response as XML
	 */
	private static String getResponse(WebResource resource) {
		return resource.accept(MediaType.TEXT_XML).get(String.class);
	}
}