package mayton.compression.graphs;

import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testHashMapContractByStringKeys() {
        Graph graph = new Graph();
        for (int i = 0; i < 100; i++) {
            graph.linkEdge("a" + i, "a" + (i + 1));
        }

        assertEquals("Assume after 100 linking edges chain must be 101 vertex",
                101, graph.getVertexMap().size());
        assertEquals("Assume after 100 linking edges chain must contains 100 edges",
                100, graph.getEdgeWeigthMap().size());

        for (int i = 0; i < 100; i++) {
            assertTrue("Graph must contans every edge", graph.containsDirectedEdgeByIds("a" + i, "a" + (i + 1)));
        }

        for (int i = 0; i < 100; i++) {
            assertTrue("Graph must contans every edge", graph.containsVertexWithId("a" + i));
            assertTrue("Graph must contans every edge", graph.containsVertexWithId("a" + (i + 1)));
        }
    }

    @Test
    public void testHashMapContractByObjectKeys() {
        Graph graph = new Graph();

        for (int i = 0; i < 100; i++) {
            graph.linkEdge(new Vertex("a" + i), new Vertex("a" + (i + 1)));
        }

        assertEquals("Assume after 100 linking edges chain must be 101 vertex",
                101, graph.getVertexMap().size());
        assertEquals("Assume after 100 linking edges chain must contains 100 edges",
                100, graph.getEdgeWeigthMap().size());

        for (int i = 0; i < 100; i++) {
            assertTrue("Graph must contans every edge", graph.containsDirectedEdge(
                    new Vertex("a" + i),
                    new Vertex("a" + (i + 1))));
        }

        for (int i = 0; i < 100; i++) {
            assertTrue("Graph must contans every edge", graph.containsVertexWithId("a" + i));
            assertTrue("Graph must contans every edge", graph.containsVertexWithId("a" + (i + 1)));
        }
    }

    @Test
    public void testGraphContainsEdges() {
        Graph graph = new Graph();
        graph.addVertex("id1");
        graph.addVertex("id2");
        graph.linkEdge("id1", "id2");
        assertFalse(graph.containsDirectedEdgeByIds("id1", "id1"));
        assertTrue(graph.containsDirectedEdgeByIds("id1", "id2"));
        assertTrue(graph.containsDirectedEdge(new Vertex("id1"), new Vertex("id2")));
        Edge edge = graph.extractEdgeByIds("id1", "id2");
        assertEquals(1, edge.getWeight());
        graph.linkEdge("id1", "id2");
        assertEquals(2, edge.getWeight());
        graph.linkEdge("id1", "id2");
        assertEquals(3, edge.getWeight());
        graph.linkEdge("id1", "id2");
        assertEquals(4, edge.getWeight());
    }

    @Test
    public void testGraphOutgoingEdges() {
        Graph graph = new Graph();
        graph.addVertex("id1");
        graph.addVertex("id2");
        graph.addVertex("id3");
        assertEquals(0, graph.extractOutgoingEdges("id1").size());
        assertEquals(0, graph.extractOutgoingEdges("id2").size());
        assertEquals(0, graph.extractOutgoingEdges("id3").size());
        graph.linkEdge("id1", "id2");
        assertEquals(1, graph.extractOutgoingEdges("id1").size());
        assertEquals(0, graph.extractOutgoingEdges("id2").size());
        assertEquals(0, graph.extractOutgoingEdges("id3").size());
        graph.linkEdge("id1", "id3");
        assertEquals(2, graph.extractOutgoingEdges("id1").size());
        assertEquals(0, graph.extractOutgoingEdges("id2").size());
        assertEquals(0, graph.extractOutgoingEdges("id3").size());
        graph.linkEdge("id1", "id3"); // Double link should not produce any outgoing edges
        assertEquals(2, graph.extractOutgoingEdges("id1").size());
        assertEquals(0, graph.extractOutgoingEdges("id2").size());
        assertEquals(0, graph.extractOutgoingEdges("id3").size());
    }

}
