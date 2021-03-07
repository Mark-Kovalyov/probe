package mayton.servlets;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

public class JettyMain {

    static Logger logger = LoggerFactory.getLogger(JettyMain.class);

    public static void main(String[] args) throws Exception {

        Server server = new Server();

        server.setRequestLog((request, response) -> logger.info("{} {}", request.getRemoteHost(), request.getRequestURI()));

        try(ServerConnector connector = new ServerConnector(server)) {

            connector.setPort(8082);
            connector.setHost("127.0.0.1");
            connector.setName("Connector-1");

            server.setConnectors(new Connector[]{connector});

            Servlet longRunningServlet = new LongRunningServlet();

            Servlet asyncLongRunningServlet = new AsyncLongRunningServlet();

            ResourceHandler resourceHandler = new ResourceHandler();
                resourceHandler.setResourceBase("css");
                resourceHandler.setDirectoriesListed(false);

            HandlerCollection handlers = new HandlerCollection();

            WebAppContext webAppContext = new WebAppContext();
                webAppContext.setResourceBase("/");
                webAppContext.addEventListener(new AppContextListener());
                webAppContext.addEventListener(new AppAsyncListener());

                webAppContext.addServlet(new ServletHolder(longRunningServlet),      "/run");
                webAppContext.addServlet(new ServletHolder(asyncLongRunningServlet), "/asyncrun");

            handlers.addHandler(webAppContext);
            handlers.addHandler(resourceHandler);
            handlers.addHandler(new DefaultHandler());

            server.setHandler(handlers);
            server.start();
            server.join();
        }
    }

}


