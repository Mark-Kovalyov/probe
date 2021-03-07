package mayton.brokers.activemq;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) throws Exception {

        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        Thread.sleep(1000);

        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        Thread.sleep(1000);

        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        Thread.sleep(1000);

        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsConsumer(), false);
        startNewDaemonThread(new JmsProducer(), false);
    }

    public static void startNewDaemonThread(Runnable runnable, boolean daemon) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(daemon);
        brokerThread.start();
    }

   
}
