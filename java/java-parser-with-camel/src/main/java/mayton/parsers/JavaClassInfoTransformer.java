package mayton.parsers;

import com.github.javaparser.ast.CompilationUnit;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

public class JavaClassInfoTransformer implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        CompilationUnit compilationUnit = exchange.getIn().getBody(CompilationUnit.class);

        // Order order = exchange.getIn().getBody(Order.class);
        //        OrderResponse answer = new OrderResponse()
        //            .setAccepted(true)
        //            .setOrderId(order.getOrderId())
        //            .setDescription(String.format("Order accepted:[item='%s' quantity='%s']", order.getItemId(), order.getQuantity()));
        //        exchange.getOut().setBody(answer);

        Message message = exchange.getOut();
        message.setBody(new JavaSourceInfo());
    }
}
