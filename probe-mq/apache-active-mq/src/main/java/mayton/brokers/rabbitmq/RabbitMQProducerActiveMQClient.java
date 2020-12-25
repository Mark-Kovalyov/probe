package mayton.brokers.rabbitmq;

import mayton.brokers.Constants;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class RabbitMQProducerActiveMQClient {

    public static void main(String[] args) throws Exception {

        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.RABBITMQ_MQTT_BROKER_URL);

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue(Constants.RABBITMQ_QUEUE_NAME);

        // Create a MessageProducer from the Session to the Topic or Queue
        MessageProducer producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        // Create a messages
        String text = "Hello world! From: " + Thread.currentThread().getName();

        TextMessage message = session.createTextMessage(text);

        producer.send(message);

        // Clean up
        session.close();
        connection.close();
    }

}
