package mayton.probe.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class TestStartClient {

    static Logger logger = LoggerFactory.getLogger(TestStartClient.class);


    public static void putAndGet() {
        Ignite ignite = Ignition.ignite();

        // Get an instance of named cache.
        final IgniteCache<Integer, String> cache = ignite.cache("cacheName");

        // Store keys in cache.
        for (int i = 0; i < 10; i++)
            cache.put(i, Integer.toString(i));

        // Retrieve values from cache.
        for (int i = 0; i < 10; i++)
            System.out.println("Got [key=" + i + ", val=" + cache.get(i) + ']');

        // Remove objects from cache.
        for (int i = 0; i < 10; i++)
            cache.remove(i);

        // Atomic put-if-absent.
        cache.putIfAbsent(1, "1");

        // Atomic replace.
        cache.replace(1, "1", "2");
    }

    public static void main( String[] args ) throws Exception {

        System.out.println("java.version = " + System.getProperty("java.version"));

        logger.info("::1");

        ClientConfiguration configuration = new ClientConfiguration().setAddresses("127.0.0.1");

        //IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        //igniteConfiguration.setLifecycleBeans(new MyLifecycleBean());

        logger.info("::2");

        IgniteClient igniteClient = Ignition.startClient(configuration);

        logger.info("::3");

        igniteClient.close();

        logger.info("::OK");

    }
}
