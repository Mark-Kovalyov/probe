package mayton.probe.ignite.entities;

import mayton.probe.ignite.Benchmark;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProbeBiTemporal {

    static Logger logger = LoggerFactory.getLogger(ProbeBiTemporal.class);

    public static void main(String[] args) {

        logger.info("Start");

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {
            logger.info("igniteClient OK");

            ClientCache<Long, BiTemporalValue> cache = igniteClient.getOrCreateCache("BiTemporalValue");

            logger.info("cache is ready");

            for(int i = 0; i < 15 ; i++) {

                BiTemporalValue btv = cache.get((long)i);

                if (btv == null) {
                    logger.info("null {}", i);
                } else {
                    logger.info("received {}", btv.toString());
                }
            }

            logger.info("value received");


            for(int i = 10; i < 20; i++) {
                BiTemporalValue btv = new BiTemporalValue(1);
                btv.getBeginTs()[0] = 13 * i;
                logger.info(":: put key = {}, value = ...", i);
                cache.put((long) i, btv);
            }


        } catch (Exception ex) {
            logger.error(ex.toString());
        }


        logger.info("Finish");

    }

}
