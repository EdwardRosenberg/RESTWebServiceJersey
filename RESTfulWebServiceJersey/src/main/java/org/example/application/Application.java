package org.example.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.client.UserInfoClient;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.request.RequestContextListener;

public class Application {

    private static final Logger log = LogManager.getLogger(UserInfoClient.class);
    private static final int PORT = 8080;
    private static final String ROOT_CONTEXT_PATH = "/";

    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath(ROOT_CONTEXT_PATH);
        context.addEventListener(new ContextLoaderListener());
        context.addEventListener(new RequestContextListener());
        context.setInitParameter("contextConfigLocation", "classpath:applicationContext.xml");

        Server jettyServer = new Server(PORT);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, ROOT_CONTEXT_PATH + "*");
        jerseyServlet.setInitOrder(0);

        // Tell Jersey Servlet to load classes in 'resource' package as REST resource.
        jerseyServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES, "org.example.resource");

        try {
            jettyServer.start();
            jettyServer.join();
            log.info("Jetty server started on port {}", PORT);
        } finally {
            jettyServer.destroy();
        }
    }
}
