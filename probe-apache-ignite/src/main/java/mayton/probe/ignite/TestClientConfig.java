package mayton.probe.ignite;

import mayton.probe.ignite.entities.Address;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

public class TestClientConfig {

    public static void main(String[] args) {

        System.out.println("java.version = " + System.getProperty("java.version"));

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        // docker run -it --net=host -e "CONFIG_URI=https://raw.githubusercontent.com/apache/ignite/master/examples/config/example-cache.xml" apacheignite/ignite
        // OK

        // docker run -it --net=host -e "CONFIG_URI=config/example-cache.xml" apacheignite/ignite
        //   Spring XML configuration path is invalid: config/example-cache.xml. Note that this path should be either
        //   absolute or a relative local file system path, relative to META-INF in classpath or valid URL to IGNITE_HOME.

        // docker run -it --net=host -e "CONFIG_URI=file:///home/mayton/git/probe/probe-apache-ignite/config/example-cache.xml" apacheignite/ignite
        //   Failed to instantiate Spring XML application context [springUrl=file:/home/mayton/git/probe/probe-apache-ignite/config/example-cache.xml,
        //   err=IOException parsing XML document from URL [file:/home/mayton/git/probe/probe-apache-ignite/config/example-cache.xml];
        //   nested exception is java.io.FileNotFoundException: /home/mayton/git/probe/probe-apache-ignite/config/example-cache.xml (No such file or directory)]

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {
            System.out.println();
            System.out.println(">>> Thin client put-get example started.");

            final String CACHE_NAME = "put-get-example";

            ClientCache<Integer, Address> cache = igniteClient.getOrCreateCache(CACHE_NAME);

            System.out.format(">>> Created cache [%s].\n", CACHE_NAME);

            Integer key = 1;
            Address val = new Address("1545 Jackson Street", 94612);

            cache.put(key, val);

            System.out.format(">>> Saved [%s] in the cache.\n", val);

            Address cachedVal = cache.get(key);

            System.out.format(">>> Loaded [%s] from the cache.\n", cachedVal);
        }
        catch (ClientException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception e) {
            System.err.format("Unexpected failure: %s\n", e);
        }

    }

}
