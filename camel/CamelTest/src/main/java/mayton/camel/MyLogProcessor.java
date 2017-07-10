package mayton.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import static java.lang.System.out;

public class MyLogProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        out.println("Processing the String : " + exchange.getIn().getBody(String.class));
    }
}
