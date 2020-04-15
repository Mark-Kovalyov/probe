package mayton.probe.ignite;

import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.cache.CachePeekMode;
import org.apache.ignite.cache.affinity.AffinityFunction;
import org.apache.ignite.cache.affinity.rendezvous.RendezvousAffinityFunction;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.ClientCacheConfiguration;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.configuration.DataRegionConfiguration;
import org.apache.ignite.configuration.DataStorageConfiguration;
import org.apache.ignite.spi.discovery.tcp.ipfinder.multicast.TcpDiscoveryMulticastIpFinder;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Benchmark {

    static Logger logger = LoggerFactory.getLogger(Benchmark.class);

    public static void main(String[] args) {

        ClientConfiguration cfg = new ClientConfiguration().setAddresses(
                "10.0.0.1:10800",
                "10.0.0.2:10800");

        logger.info("Start");

        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {

            final String CACHE_NAME = "benchmarkBackup";

            RendezvousAffinityFunction rendezvousAffinityFunction = new RendezvousAffinityFunction();
            rendezvousAffinityFunction.setExcludeNeighbors(true);
            rendezvousAffinityFunction.setPartitions(20);

            ClientCacheConfiguration ccc = new ClientCacheConfiguration()
                    .setBackups(1)
                    .setAtomicityMode(CacheAtomicityMode.ATOMIC)
                    .setName(CACHE_NAME);

            ClientCache<String, Boolean> cache = igniteClient.getOrCreateCache(ccc);




            logger.info("Current cache.size = {}", cache.size(CachePeekMode.ALL));

            logger.info("Clear cache");

            cache.clear();

            logger.info("Start benchmark");

            long t1 = System.currentTimeMillis();

            int inserts = 100_000;

            for(int i = 0 ; i < inserts ; i++) {
                cache.put(UUID.randomUUID().toString(), true);
            }

            long t2 = System.currentTimeMillis();

            logger.info("Inserts: {} ", inserts);
            long elapsed = t2 - t1;
            logger.info("Elapsed: {} sec", elapsed/1000);
            logger.info("Speed: {} inserts per second", inserts / ( elapsed / 1000));

            logger.info("Finish");

        }
        catch (Exception e) {
            logger.error("Exception", e);
        }
    }

}
