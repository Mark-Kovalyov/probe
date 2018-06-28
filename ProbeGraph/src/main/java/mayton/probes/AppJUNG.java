package mayton.probes;

import edu.uci.ics.jung.algorithms.cluster.BicomponentClusterer;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppJUNG {

    static Logger logger = LoggerFactory.getLogger(AppJUNG.class);

    public static void main(String[] args) throws Exception {

        Graph<Vertex,Edge> g = new DirectedSparseGraph();

        Vertex v1 = new Vertex("V1");
        Vertex v2 = new Vertex("V2");
        Vertex v3 = new Vertex("V3");
        Vertex v4 = new Vertex("V4");

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        Edge e1 = new Edge("E1");
        Edge e2 = new Edge("E1");
        Edge e3 = new Edge("E1");

        g.addEdge(e1, v1,v2);
        g.addEdge(e2, v2,v3);
        g.addEdge(e3, v3,v4);

        logger.info("Is v1,v2 neigbour = {}", g.isNeighbor(v1,v2));

        logger.info("Is v1,e1 incident = {}", g.isIncident(v1,e1));

        logger.info("Is v1,e3 incident = {}", g.isIncident(v1,e3));

        BicomponentClusterer bc = new BicomponentClusterer();





    }
}
