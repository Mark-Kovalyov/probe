package mayton.parsers;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;

public class JavaParserRouteBuilder extends RouteBuilder {

    public static void main(String[] args) throws Exception {
        JavaParserRouteBuilder jprb = new JavaParserRouteBuilder();
        jprb.run();
    }

    private void run() throws Exception {
        // (1)
        DefaultCamelContext camelContext = new DefaultCamelContext();

        // (2)
        bindBeans(camelContext.getRegistry());

        // (3)
        camelContext.addRoutes(this);

        // (4)
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();

        // (5)
        Thread.sleep(60 * 1000);

        // (6)
        camelContext.stop();
    }

    private void bindBeans(Registry registry) {
        registry.bind("javaFileScanner", new JavaFileScaner());
        registry.bind("javaClassInfoTransformer", new JavaClassInfoTransformer());
        registry.bind("graphWriter", new JavaClassInfoTransformer());
    }

    @Override
    public void configure() throws Exception {

        from("javaFileScanner")
                .split()
                .method("checkPoison")
                .to("direct:javaClassInfoTransformer");

        from("javaClassInfoTransformer").to("graphWriter");

    }
}
