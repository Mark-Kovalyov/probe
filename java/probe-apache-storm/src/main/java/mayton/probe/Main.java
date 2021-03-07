package mayton.probe;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.testing.TestWordCounter;
import org.apache.storm.topology.TopologyBuilder;

public class Main {

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {

        Config config = new Config();
        config.setNumWorkers(2);

        TopologyBuilder topologyBuilder = new TopologyBuilder();

        topologyBuilder.setSpout("Fb2-spout", new Fb2Spout());

        topologyBuilder.setBolt("Lucene-bolt", new LuceneBolt())
                .setNumTasks(4)
                .shuffleGrouping("Fb2-spout");


//        topologyBuilder.setBolt("Sphinx-bolt", new SphinxBolt(),6)
//                .shuffleGrouping("Lucene-bolt");

        StormSubmitter.submitTopology("fb2-indexer-topology",
                config,
                topologyBuilder.createTopology()
        );


    }

}
