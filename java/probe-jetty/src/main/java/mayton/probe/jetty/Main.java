package mayton.probe.jetty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    private static ResourceHandler getResourceHandler() {
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "html/index.html" });
        resource_handler.setResourceBase(".");
        return resource_handler;
    }

    private static ContextHandler getContextHandler(Server server) {
        ContextHandler context = new ContextHandler();
        context.setContextPath("/random");
        context.setResourceBase(".");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        server.setHandler(context);
        return context;
    }

    public static void main(String[] args) {

        Server server = new Server(5555);

        try {

            HandlerList handlers = new HandlerList();

            handlers.setHandlers(new Handler[] {

            });

            server.setHandler(handlers);

            server.start();
            server.join();
        } catch (Exception e) {
            logger.error(e);
        }

    }



}
