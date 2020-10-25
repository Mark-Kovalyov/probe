package mayton.probe;

import org.apache.jena.graph.Graph;
import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.graph.impl.CollectionGraph;
import org.apache.jena.mem.GraphMem;
import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.apache.jena.graph.NodeFactory.createLiteral;
import static org.apache.jena.graph.NodeFactory.createURI;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MemGraphTest {

    @Disabled
    @Test
    public void testTriple(){

        Dataset ds = DatasetFactory.createTxnMem() ;

        Triple sigfrid = new Triple(
                createURI(":Sigfrid"),
                createURI("a"),
                createURI("foaf:Person")
        );

        Triple krimhilda = new Triple(
                createURI(":Sigfrid"),
                createURI("rdf:type"),
                createURI("foaf:Person")
        );

        assertEquals(sigfrid, krimhilda);

    }

    @Test
    public void test() throws FileNotFoundException {

        Graph graph = new CollectionGraph();


        RDFDataMgr.read(graph, new FileInputStream("input/nibelungs.ttl"), "http://ex.com", Lang.TURTLE);

        StringWriter sw = new StringWriter();

        RDFDataMgr.write(sw, graph, RDFFormat.JSONLD);

        RDFDataMgr.write(System.out, graph, RDFFormat.JSONLD);
        RDFDataMgr.write(System.out, graph, RDFFormat.TURTLE_PRETTY);
        RDFDataMgr.write(System.out, graph, RDFFormat.RDFXML);

        RDFDataMgr.write(System.out, graph, RDFFormat.NQUADS);

        sw.flush();

        System.out.println(sw.toString());

        StringReader stringReader = new StringReader(sw.toString());

        Graph graph2 = new GraphMem();

        RDFDataMgr.read(graph2, stringReader, "http://ex.org", Lang.JSONLD);


    }

}
