package mayton.camel;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {
        from("file:./in?move=./done")
                .process(new MyLogProcessor())
                .bean(new UpperCaseTransformer(),"transform")
                .bean(new ReverseTransformer(),"reverse")
                .to("file:./out");
    }

}
