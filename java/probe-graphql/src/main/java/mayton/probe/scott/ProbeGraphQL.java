package mayton.probe.scott;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.getProperty;

public class ProbeGraphQL {

    static Logger logger = LoggerFactory.getLogger(ProbeGraphQL.class);

    public ProbeGraphQL(String[] args) throws Exception {

        Server server = new Server();

        server.setRequestLog((request, response) ->
                logger.info("{} {}",
                        request.getRemoteHost(),
                        request.getRequestURI()
                ));

        ServerConnector connector = new ServerConnector(server);

        int port    = 7777;
        String host = "127.0.0.1";

        connector.setPort(port);
        connector.setHost(host);
        connector.setName("Connector-1");

        server.addConnector(connector);



        ResourceHandler staticResourceHandler = new ResourceHandler();

        if (args.length > 1) {
            logger.info("Custom resource base '{}' detected",args[1]);
            staticResourceHandler.setResourceBase(args[1]);
        } else {
            String userHome = getProperty("user.home");
            logger.info("Default resource base is user.home = '{}' folder",userHome);
            staticResourceHandler.setResourceBase(userHome);
        }

        staticResourceHandler.setDirectoriesListed(true);
        staticResourceHandler.setWelcomeFiles(new String[]{"index.html"});

        ContextHandler staticContextHandler = new ContextHandler();
        staticContextHandler.setContextPath("/*");

        staticContextHandler.setHandler(staticResourceHandler);
        staticContextHandler.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "true");

        staticContextHandler.setMaxFormKeys(0);
        staticContextHandler.setMaxFormContentSize(0);

        if (false) {
            staticContextHandler.setProtectedTargets(new String[]{"/doc/.index",".index",".git"});
            staticContextHandler.setInitParameter("","");
        }

        // Create a handler list to store our static and servlet context handlers.
        HandlerList handlers = new HandlerList();

        handlers.setHandlers(new Handler[]{
                staticContextHandler

        });

        // Add the handlers to the server and start jetty.
        server.setHandler(handlers);
        server.start();
        server.join();
    }

    public static void main(String[] args) throws Exception {

        new ProbeGraphQL(args);

    }

}
