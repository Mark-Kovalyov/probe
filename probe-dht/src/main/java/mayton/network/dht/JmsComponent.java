package mayton.network.dht;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.concurrent.ThreadSafe;
import javax.jms.*;
import java.util.Optional;

@ThreadSafe
@Deprecated
public class JmsComponent {

    private static Logger logger = LogManager.getLogger(JmsComponent.class);

    public static JmsComponent instance = new JmsComponent();

    private Optional<Session> reCreateSession() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("amqp://localhost:5672");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            return Optional.of(session);
        } catch (JMSException ex) {
            logger.warn("Unable to create session", ex);
        }
        return Optional.empty();
    }

    private Optional<Session> sessionOptional;

    private JmsComponent() {
        sessionOptional = reCreateSession();
    }

    public synchronized void publish(String dhtMessage) {
        if (sessionOptional.isEmpty()) {
            sessionOptional = reCreateSession();
        }
        if (sessionOptional.isPresent()) {
            Session session = sessionOptional.get();
            Destination destination = null;
            try {
                destination = session.createQueue("probe-dht-queue");
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                TextMessage message = session.createTextMessage(dhtMessage);
                logger.info("Sent message: {} : {}", message.hashCode(), Thread.currentThread().getName());
                producer.send(message);
            } catch (JMSException e) {
                logger.warn("Unable to send message", e);
            }
        } else {
            logger.warn("Unable to re-create session right now!");
        }
    }


}
