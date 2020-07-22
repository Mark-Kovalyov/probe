package mayton.probe.ignite.entities;

import mayton.probe.ignite.Benchmark;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class ProbeBiTemporal {

    static Logger logger = LoggerFactory.getLogger(ProbeBiTemporal.class);

    public static void main(String[] args) {

        logger.info("Start");

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {
            logger.info("igniteClient OK");

            ClientCache<Long, BiTemporalValue> cache = igniteClient.getOrCreateCache("BiTemporalValue");

            logger.info("cache is ready");

            BiTemporalValue biTemporalValue = new BiTemporalValue(1000);

            Random random = new Random();

            for(int k = 0;k < 1000 ; k++) {
                biTemporalValue.getBeginTs()[k] = random.nextLong();
                biTemporalValue.getEndTs()[k] = random.nextLong();
                biTemporalValue.getKeys()[k] = random.nextLong();
                biTemporalValue.getValues()[k] = random.nextDouble();
            }

            cache.put(555L, biTemporalValue);


        } catch (Exception ex) {
            logger.error(ex.toString());
        }


        logger.info("Finish");

    }

}
