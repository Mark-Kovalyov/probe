package mayton;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import static mayton.Constants.BROKER_URL;
import static mayton.Constants.QUEUE_NAME;

public class JmsConsumer implements Runnable, ExceptionListener {

    private long timeout = 1000;

    static Logger logger = LoggerFactory.getLogger(JmsConsumer.class);

    public void run() {
        try {

            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            connection.setExceptionListener(this);

            // Create a Session
            //     { AUTO_ACKNOWLEDGE |  CLIENT_ACKNOWLEDGE | DUPS_OK_ACKNOWLEDGE | SESSION_TRANSACTED }
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue(QUEUE_NAME);

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);

            // Wait for a message
            Message message = consumer.receive(timeout);

            if (message!=null) {
                //  { BytesMessage,
                //    MapMessage,
                //    ObjectMessage,
                //    StreamMessage,
                //    TextMessage }
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    logger.info("Received text : {}", text);
                } else if (message instanceof ObjectMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    logger.info("Received object message : {}", objectMessage.toString());
                } else if (message instanceof MapMessage) {
                    ObjectMessage objectMessage = (ObjectMessage) message;

                    logger.info("Received object message : {}", objectMessage.toString());
                } else {
                    logger.info("Received raw : {}", message.toString());
                }
            } else {
                logger.warn("Message is null");
            }

            consumer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        }
    }

    public synchronized void onException(JMSException ex) {
        logger.error(ex.toString());
    }
}
