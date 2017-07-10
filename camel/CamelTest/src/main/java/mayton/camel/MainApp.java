package mayton.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class MainApp {

    public static void main(String[] args) throws Exception{

        for(int i=0;i<1;i++){
            for(int j=0;i<1;j++){
                boolean res = i==0 ? j==0 ? 1 : 0 : 1;
            }
        }




        MyRouteBuilder routeBuilder = new MyRouteBuilder();
        CamelContext ctx = new DefaultCamelContext();

        ctx.addRoutes(routeBuilder);
        ctx.start();
        Thread.sleep(1000000);
        ctx.stop();


    }

}
