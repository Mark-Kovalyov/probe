package mayton.probe.ignite.jdbc;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientException;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class MainEquity {

    static Logger logger = LoggerFactory.getLogger(mayton.probe.ignite.jdbc.MainEquity.class);

    public static void main(String[] args) {

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {
            ClientCache<Integer, Equity> cache = igniteClient.getOrCreateCache("equity-cache");
            for(int i=0;i<1000;i++) {
                cache.put(i, new Equity(i, "", UUID.randomUUID().toString()));
            }
            Thread.sleep(1000000);
        }
        catch (ClientException e) {
            logger.error("ClientException", e);
        }
        catch (Exception e) {
            logger.error("Exception", e);
        }

    }

}
