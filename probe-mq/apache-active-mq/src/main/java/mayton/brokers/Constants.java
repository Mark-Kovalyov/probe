package mayton.brokers;

public class Constants {

    // ActiveMQ (OpenWire ?)
    public static final String ACTIVEMQ_BROKER_URL = "tcp://127.0.0.1:61616";
    public static final String ACTIVEMQ_QUEUE_NAME = "ApacheMQ-queue-1";

    // RabbitMQ (with AMQP protocol)
    // TODO: Exception in thread "main" javax.jms.JMSException: Could not create Transport. Reason: java.io.IOException: Transport scheme NOT recognized: [amqp]
    // TODO: Caused by: java.io.IOException: Transport scheme NOT recognized: [amqp]
    // TODO: Caused by: java.io.IOException: Could not find factory class for resource: META-INF/services/org/apache/activemq/transport/amqp
    public static final String RABBITMQ_AMQP_BROKER_URL = "amqp://127.0.0.1:5672";
    public static final String RABBITMQ_QUEUE_NAME = "Rabiit-Queue-1";

    // RabbitMQ (STOMP)
    // TODO: Caused by: java.lang.ClassNotFoundException: com.thoughtworks.xstream.io.HierarchicalStreamDriver
    public static final String RABBITMQ_STOMP_BROKER_URL = "stomp://127.0.0.1:61613";

    // RabbitMQ (MQTT) 1883
    // TODO: Exception in thread "main" javax.jms.JMSException: Could not connect to broker URL: mqtt://127.0.0.1:1883. Reason: java.net.ConnectException: Connection refused (Connection refused)
    // TODO: Caused by: java.net.ConnectException: Connection refused (Connection refused)
    public static final String RABBITMQ_MQTT_BROKER_URL = "mqtt://127.0.0.1:1883";
}
