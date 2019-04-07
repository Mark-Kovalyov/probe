package mayton;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static mayton.Constants.BROKER_URL;
import static mayton.Constants.QUEUE_NAME;

public class JmsProducer implements Runnable {

    static Logger logger = LoggerFactory.getLogger(JmsProducer.class);

    private String queueName;
    private String brokerUrl;

    public JmsProducer(){
        this.brokerUrl = BROKER_URL;
        this.queueName = QUEUE_NAME;
    }

    // "vm://localhost"
    // "TEST.FOO"
    public JmsProducer(String brokerUrl, String queueName){
        this.brokerUrl = brokerUrl;
        this.queueName = queueName;
    }

    public void run() {
        try {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(queueName);

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create a messages
            String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            logger.info("Sent message: {} : {}", message.hashCode() , Thread.currentThread().getName());
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception e) {
            logger.error(e.toString());
        }
    }
}
