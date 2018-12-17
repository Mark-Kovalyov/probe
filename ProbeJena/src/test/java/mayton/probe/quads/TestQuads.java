package mayton.probe.quads;

import org.apache.jena.graph.NodeFactory;
import org.apache.jena.graph.Triple;
import org.apache.jena.riot.lang.CollectorStreamQuads;
import org.apache.jena.riot.lang.CollectorStreamTriples;
import org.apache.jena.riot.system.StreamRDF;
import org.apache.jena.sparql.core.Quad;
import org.apache.jena.sparql.util.NodeFactoryExtra;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.jena.graph.NodeFactory.createBlankNode;
import static org.apache.jena.graph.NodeFactory.createURI;

public class TestQuads {

    private List<Triple> writeTriples(StreamRDF out, int size) {

        List<Triple> results = new ArrayList<>();
        out.start();
        for (int i = 1; i <= size; i++) {

            Triple t = new Triple(
                    createBlankNode(),
                    createURI("http://predicate"),
                    NodeFactoryExtra.intToNode(i));

            out.triple(t);
            results.add(t);
        }
        out.finish();
        return results;
    }


    @Test
    public void test_streamed_triples() {
        CollectorStreamTriples out = new CollectorStreamTriples();
        List<Triple> expected = writeTriples(out, 10);

        Assert.assertEquals(expected, out.getCollected());
    }

    private List<Quad> writeQuads(StreamRDF out, int size) {
        List<Quad> results = new ArrayList<>();
        out.start();
        for (int i = 1; i <= size; i++) {

            Quad q = new Quad(
                    createURI("http://graph"),
                    createBlankNode(),
                    createURI("http://predicate"),
                    NodeFactoryExtra.intToNode(i));

            out.quad(q);
            results.add(q);
        }
        out.finish();
        return results;
    }


    @Test
    public void test_streamed_quads() {

        CollectorStreamQuads out = new CollectorStreamQuads();

        List<Quad> expected = writeQuads(out, 10);

        Assert.assertEquals(expected, out.getCollected());
    }


}
