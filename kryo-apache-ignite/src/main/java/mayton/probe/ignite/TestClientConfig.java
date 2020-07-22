package mayton.probe.ignite;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

import mayton.probe.ignite.entities.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClientConfig {
    
    static Logger logger = LoggerFactory.getLogger(TestClientConfig.class);

    public static void main(String[] args) {

        logger.info("java.version = " + System.getProperty("java.version"));

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {

            logger.info(">>> Thin client put-get example started.");

            final String CACHE_NAME = "put-get-example";

            ClientCache<Integer, Address> cache = igniteClient.getOrCreateCache(CACHE_NAME);

            logger.info(">>> Created cache [{}].", CACHE_NAME);

            Integer key = 1;

            Address val = new Address("1545 Jackson Street", 94612);

            cache.put(key, val);

            logger.info(">>> Saved [{}] in the cache.", val);

            Address cachedVal = cache.get(key);

            logger.info(">>> Loaded [{}] from the cache.", cachedVal);

        }
        catch (ClientException e) {
            logger.error("ClientException", e);
        }
        catch (Exception e) {
            logger.error("Exception", e);
        }

    }

}
