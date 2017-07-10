package mayton.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 */
public class App {

    @SuppressWarnings({"unchecked",""})
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:./in?noop=true").to("file:./out");
            }
        });


        context.start();

        Thread.sleep(100000);

        context.stop();
    }
}
