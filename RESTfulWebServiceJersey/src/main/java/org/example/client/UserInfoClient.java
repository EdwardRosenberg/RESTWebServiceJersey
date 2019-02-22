package org.example.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.User;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserInfoClient {

    public static final String BASE_URI = "http://localhost:8080/RESTfulWebServiceJersey";
    public static final String PATH_NAME = "/UserInfoService/name/";
    public static final String PATH_AGE = "/UserInfoService/age/";

    private static final Logger log = LogManager.getLogger(UserInfoClient.class);

    Client client = ClientBuilder.newClient();

    public static void main(String[] args) {

        String name = "John";
        int age = 25;

        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target("http://localhost:8082/spring-jersey");
        WebTarget employeeWebTarget = webTarget.path("resources/employees");

        //log.debug("Client Response: \n" + getClientResponse(nameResource));

        Invocation.Builder invocationBuilder = employeeWebTarget.request(MediaType.APPLICATION_JSON);

        User getResponse = invocationBuilder.get(User.class);

        Response postResponse = invocationBuilder.post(Entity.entity(new User("Bob", "admin"), MediaType.APPLICATION_JSON));

        //log.debug("Client Response \n" + getClientResponse(ageResource));
        //log.debug("Response \n" + getResponse(ageResource));
    }

//    /**
//     * Returns client response status
//     */
//    private static String getClientResponse(WebResource resource) {
//        return resource.accept(MediaType.TEXT_XML).get(ClientResponse.class).toString();
//    }
//
//    /**
//     * Returns client response as XML
//     */
//    private static String getResponse(WebResource resource) {
//        return resource.accept(MediaType.TEXT_XML).get(String.class);
//    }

    public User getJsonEmployee(int id) {
        return client
                .target(BASE_URI + PATH_NAME)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .get(User.class);
    }


//    public Response createJsonEmployee(Employee emp) {
//        return client
//                .target(REST_URI)
//                .request(MediaType.APPLICATION_JSON)
//                .post(Entity.entity(emp, MediaType.APPLICATION_JSON));
//    }

}

