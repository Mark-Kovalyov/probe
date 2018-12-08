package mayton.probe;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.impl.ModelCom;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.core.Quad;

public class GraphQuadWriteTurtle {

    public static void main(String[] args) {

        Graph graph = new GraphMem();

        Node graphName = NodeFactory.createLiteral("Graph-1");

        Node subj = NodeFactory.createURI("org:Siegfrid");
        Node pred = NodeFactory.createURI("org:hero");
        Node obj = NodeFactory.createLiteral("true");

        Triple siegfrid = new Triple(subj, pred, obj);

        Quad quad = new Quad(graphName, siegfrid);

        Model model = null;

        RDFDataMgr.write(System.out, graph, RDFFormat.TURTLE_PRETTY);
    }

}
