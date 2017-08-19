package mayton.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.concurrent.locks.LockSupport;

public class MainApp {

    public static void main(String[] args) throws Exception{


        MyRouteBuilder routeBuilder = new MyRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();

        ctx.addRoutes(routeBuilder);
        ctx.start();
        Thread.sleep(1000000);
        ctx.stop();


    }

}
