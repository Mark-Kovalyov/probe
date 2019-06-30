package mayton.probe.ignite;

import org.apache.ignite.IgniteDataStreamer;
import org.apache.ignite.Ignition;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.internal.GridKernalContextImpl;
import org.apache.ignite.internal.processors.datastreamer.DataStreamerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;


public class BenchmarkDataStreamer {

    static Logger logger = LoggerFactory.getLogger(BenchmarkDataStreamer.class);

    public static void main(String[] args) {

        ClientConfiguration cfg = new ClientConfiguration().setAddresses("127.0.0.1:10800");



        logger.info("Start");
        try (IgniteClient igniteClient = Ignition.startClient(cfg)) {



            final String CACHE_NAME = "benchmark_stream";

            GridKernalContext ctx = new GridKernalContextImpl();

            DelayQueue<DataStreamerImpl<String, Boolean>> flushQueue = new DelayQueue<>();

            IgniteDataStreamer<String,Boolean> ids = new DataStreamerImpl<>(ctx, CACHE_NAME, flushQueue);

            ids.close();

            logger.info("Finish");

        }
        catch (Exception e) {
            logger.error("Exception", e);
        }
    }

}
