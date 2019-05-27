package mayton.probe.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App {

    static Logger logger = LoggerFactory.getLogger(App.class);


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

        // From docker image: 11211/tcp, 47100/tcp, 47500/tcp, 49112/tcp
        // From client code: 10983/tcp

        // docker run -d  -p 11211:11211 -p 47100:47100 -p 47500:47500 -p 49112:49112  apacheignite/ignite:latest

        // docker stop
        // docker inspect apacheignite/ignite:latest

        // docker run -it --net=host -e "CONFIG_URI=https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml" apacheignite/ignite

        // docker run -it --net=host -e "examples/config/example-cache.xml" apacheignite/ignite

        logger.info("::1");

        ClientConfiguration configuration = new ClientConfiguration().setAddresses("localhost");

        //IgniteConfiguration igniteConfiguration = new IgniteConfiguration();
        //igniteConfiguration.setLifecycleBeans(new MyLifecycleBean());

        logger.info("::2");

        IgniteClient igniteClient = Ignition.startClient(configuration);

        logger.info("::3");

        igniteClient.close();

        logger.info("::OK");

    }
}
