package mayton.compression.graphs;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class GraphTestAdjucentControl {

    //
    //  (1)
    //    \
    //    (2) -- (3)
    //
    @Test
    public void test() {
        Graph graph = new Graph();
        graph.linkEdge("1","2");
        graph.linkEdge("2","3");
        graph.linkEdge("3","4");
        graph.linkEdge("3","4");
        graph.linkEdge("3","5");
        graph.linkEdge("3","6");
        graph.linkEdge("7","2");

        assertNotNull(graph.findVertexById("1"));
        assertNotNull(graph.findVertexById("2"));
        assertNotNull(graph.findVertexById("3"));
        assertNotNull(graph.findVertexById("4"));
        assertNotNull(graph.findVertexById("5"));
        assertNotNull(graph.findVertexById("6"));
        assertNotNull(graph.findVertexById("7"));

        Optional<List<Edge>> incomingEdgesOptional = graph.extractIncomingEdges("2");
        assertTrue(incomingEdgesOptional.isPresent());
        assertEquals(2, incomingEdgesOptional.get().size());

        Optional<List<Edge>> outgoingEdgesOptional = graph.extractOutgoingEdges("3");
        assertTrue(outgoingEdgesOptional.isPresent());
        assertEquals(3, outgoingEdgesOptional.get().size());
    }

}
