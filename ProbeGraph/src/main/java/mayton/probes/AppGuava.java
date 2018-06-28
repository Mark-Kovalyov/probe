package mayton.probes;

import com.google.common.graph.*;
import mayton.probes.oracle.OracleConstraint;
import mayton.probes.oracle.OracleObject;
import mayton.probes.oracle.OracleTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppGuava {



    static Logger logger = LoggerFactory.getLogger(AppGuava.class);

    public static void main(String[] args) throws Exception {

        logger.info("Begin");

        MutableGraph<Integer> graph = GraphBuilder.undirected().build();

        ImmutableGraph<Integer> immutableGraph = ImmutableGraph.copyOf(graph);

        MutableNetwork<String, Integer> flightNetwork = NetworkBuilder.directed().allowsParallelEdges(true).build();
        flightNetwork.addEdge("LAX", "ATL", 3025);
        flightNetwork.addEdge("LAX", "ATL", 1598);
        flightNetwork.addEdge("ATL", "LAX", 2450);

        MutableValueGraph<String, Double> mutableValueGraph = ValueGraphBuilder.undirected().allowsSelfLoops(true).build();
        mutableValueGraph.putEdgeValue("San Francisco", "San Francisco", 0.0);
        mutableValueGraph.putEdgeValue("San Jose", "San Jose", 0.0);
        mutableValueGraph.putEdgeValue("San Francisco", "San Jose", 48.4);



    }
}
