package mayton.probe.graphql;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main2 {

    public static void main(String[] args) throws Exception {

        Server server = new Server(8080);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new GraphQLEndpoint()),"/graphql");

        context.addServlet(new ServletHolder(new RandomServlet()),"/random");

        server.start();
        server.join();

    }

}
