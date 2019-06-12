package mayton.probe.graphql;

import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

    public static final void main(String args[]) throws Exception  {

        //Create the server
        Server server = new Server(8080);

        //Enable parsing of jndi-related parts of web.xml and jetty-env.xml

        org.eclipse.jetty.webapp.Configuration.ClassList classlist = org.eclipse.jetty.webapp.Configuration.ClassList.setServerDefault(server);

        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                           "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                           "org.eclipse.jetty.plus.webapp.PlusConfiguration");

        classlist.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                            "org.eclipse.jetty.annotations.AnnotationConfiguration");

        //Create a WebApp
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar("../../tests/test-webapps/test-servlet-spec/test-spec-webapp/target/test-spec-webapp-9.0.4-SNAPSHOT.war");
        server.setHandler(webapp);

        //Register new transaction manager in JNDI
        //At runtime, the webapp accesses this as java:comp/UserTransaction

        //org.eclipse.jetty.plus.jndi.Transaction transactionMgr = new org.eclipse.jetty.plus.jndi.Transaction(new com.acme.MockUserTransaction());

        //Define an env entry with webapp scope.
        //org.eclipse.jetty.plus.jndi.EnvEntry maxAmount = new org.eclipse.jetty.plus.jndi.EnvEntry (webapp, "maxAmount", new Double(100), true);

        // Register a  mock DataSource scoped to the webapp
        //org.eclipse.jetty.plus.jndi.Resource mydatasource = new org.eclipse.jetty.plus.jndi.Resource(webapp, "jdbc/mydatasource", new com.acme.MockDataSource());

        // Configure a LoginService
        HashLoginService loginService = new HashLoginService();
        loginService.setName("Test Realm");
        loginService.setConfig("src/test/resources/realm.properties");
        server.addBean(loginService);


        server.start();
        server.join();
    }


}
