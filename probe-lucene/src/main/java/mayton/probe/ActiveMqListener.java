package mayton.probe;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.jms.*;

public class ActiveMqListener {

    static Logger logger = LogManager.getLogger(ActiveMqListener.class);

    public static void main(String[] args) throws JMSException, InterruptedException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(Constants.BROKER_URL);
        Destination destination = new ActiveMQQueue("mt-documents");

        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        MessageConsumer consumer = session.createConsumer(destination);

        for (;;) {
            System.out.println("Waiting for message.");
            Message message = consumer.receive();
            if (message == null) {
                break;
            }
            System.out.println("Got message: " + message);

        }

        connection.close();
    }

}
