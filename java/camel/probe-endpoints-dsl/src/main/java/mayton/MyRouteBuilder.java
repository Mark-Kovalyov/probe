package mayton;

import org.apache.camel.builder.endpoint.EndpointRouteBuilder;
import org.apache.camel.builder.endpoint.dsl.FileEndpointBuilderFactory;

/**
 * A Camel Java DSL Router
 */
public class MyRouteBuilder extends EndpointRouteBuilder {

    /**
     * Let's configure the Camel routing rules using Java & Endpoint DSLs
     */
    public void configure() {

        //This route has a timer component 'myTimer', that produces tick events, every 5 seconds, at most 10 times.
        //At each tick, we write a message to an output file named 'timer.log', in the 'target' directory.
        //You'll notice the usage of strongly typed methods from Endpoint DSL, like 'timer', 'period','fileExist' etc.
        from(timer("myTimer").period(5_000).repeatCount(10))
                .transform()
                .simple("Timer: ${in.header.CamelTimerName} fired at: ${in.header.CamelTimerFiredTime}") //Here we use simple expression to access exchange properties
                .log("${body}")
                .to(file("target")
                        .fileName("timer.log")
                        .fileExist(FileEndpointBuilderFactory.GenericFileExist.Append) //If the file exists, append to it
                        .appendChars("\n")
                );
    }
}
