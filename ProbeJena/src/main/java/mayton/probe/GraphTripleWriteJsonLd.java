package mayton.probe;


import org.apache.jena.graph.*;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.riot.RDFWriter;
import org.apache.jena.sparql.core.Quad;

public class GraphTripleWriteJsonLd {

    public static void main(String[] args) {

        Graph graph = new GraphMem();

        Node subj = NodeFactory.createURI("org:Siegfrid");
        Node pred = NodeFactory.createURI("org:hero");
        Node obj = NodeFactory.createLiteral("true");

        graph.add(new Triple(subj, pred, obj));

        RDFDataMgr.write(System.out, graph, RDFFormat.JSONLD);
    }

}
