package mayton.probespringjmsactivemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MyReceiver {

    static Logger logger = LoggerFactory.getLogger(MyReceiver.class);

    @JmsListener(
            destination  = "mailbox1",
            subscription = "subscription1", // The name for the durable subscription, if any.
            selector     = "selector1",     // The JMS message selector expression, if any.
            concurrency  = "1-10" // The concurrency limits for the listener, if any. Overrides the value defined
                                  // by the container factory used to create the listener container.
                                  // The concurrency limits can be a "lower-upper" String &mdash; for example,
                                  // "5-10" &mdash; or a simple upper limit String &mdash; for example, "10", in
                                  // which case the lower limit will be 1.
                                  // <p>Note that the underlying container may or may not support all features.
                                  // For instance, it may not be able to scale, in which case only the upper limit
                                  // is used.
    )
    public void receive(Email email) {
        logger.info("Receive", email.toString());
    }

}
