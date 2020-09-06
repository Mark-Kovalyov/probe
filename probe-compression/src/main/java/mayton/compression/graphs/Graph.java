package mayton.compression.graphs;

import com.google.common.math.Quantiles;
import com.google.common.math.Stats;
import mayton.compression.Lhm;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Directed, Weight graph
 */
public class Graph implements Serializable {

    static Logger logger = LoggerFactory.getLogger(Graph.class);

    private Map<Edge, Edge> edgeWeigthMap = new HashMap<>();
    private Map<String, Vertex> vertexMap = new HashMap<>();
    private LinkedHashMap<String, Object> statistics;

    private double fd(double v) {
        //return String.format("%.4f", v);
        return v;
    }

    public Graph() {
        // Just for serialization
    }

    public Vertex addVertex(@NotNull String id) {
        if (vertexMap.containsKey(id)) {
            return vertexMap.get(id);
        } else {
            Vertex t = new Vertex(id);
            vertexMap.put(id, t);
            return t;
        }
    }

    /**
     * Integer invariants
     *
     *     Order, the number of vertices
     *     Size, the number of edges
     *     Number of connected components
     *     Circuit rank, a linear combination of the numbers of edges, vertices, and components
     *     diameter, the longest of the shortest path lengths between pairs of vertices
     *     girth, the length of the shortest cycle
     *     Vertex connectivity, the smallest number of vertices whose removal disconnects the graph
     *     Edge connectivity, the smallest number of edges whose removal disconnects the graph
     *     Chromatic number, the smallest number of colors for the vertices in a proper coloring
     *     Chromatic index, the smallest number of colors for the edges in a proper edge coloring
     *     Choosability (or list chromatic number), the least number k such that G is k-choosable
     *     Independence number, the largest size of an independent set of vertices
     *     Clique number, the largest order of a complete subgraph
     *     Arboricity
     *     Graph genus
     *     Pagenumber
     *     Hosoya index
     *     Wiener index
     *     Colin de Verdière graph invariant
     *     Boxicity
     *
     * Real number invariants
     *
     *     Clustering coefficient
     *     Betweenness centrality
     *     Fractional chromatic number
     *     Algebraic connectivity
     *     Isoperimetric number
     *     Estrada index
     *     Strength
     */
    public void reCalculateStatistics() {
        statistics = new LinkedHashMap<>();
        statistics.put("Vertices", String.valueOf(vertexMap.size()));
        statistics.put("Edges",    String.valueOf(edgeWeigthMap.size()));

            List<Integer> weights = edgeWeigthMap.keySet().stream()
                    .map(Edge::getWeight)
                    .collect(Collectors.toList());

            if (weights.size() > 0) {

                Stats stats = Stats.of(weights);
                Lhm weightStatsMap = new Lhm();
                weightStatsMap.put("Max edge weight", fd(stats.max()));
                weightStatsMap.put("AVG edge weight", fd(stats.mean()));
                weightStatsMap.put("Median edge weight", fd(Quantiles.median().compute(weights)));
                weightStatsMap.put("75-th percentille edge weight", fd(Quantiles.percentiles().index(75).compute(weights)));
                weightStatsMap.put("80-th percentille edge weight", fd(Quantiles.percentiles().index(80).compute(weights)));
                weightStatsMap.put("85-th percentille edge weight", fd(Quantiles.percentiles().index(85).compute(weights)));
                weightStatsMap.put("90-th percentille edge weight", fd(Quantiles.percentiles().index(90).compute(weights)));
                weightStatsMap.put("95-th percentille edge weight", fd(Quantiles.percentiles().index(95).compute(weights)));
                weightStatsMap.put("97-th percentille edge weight", fd(Quantiles.percentiles().index(97).compute(weights)));

                statistics.put("weightStats", weightStatsMap);

            } else {
                logger.warn("Unable to calculate weight statistics!");
            }



            List<Integer> joins = vertexMap.entrySet().stream()
                    .map(entry -> entry.getValue())
                    .map(vertex -> vertex.getOutgoingEdges().size() + vertex.getIncomingEdges().size())
                    .collect(Collectors.toList());

            Stats joinsStats = Stats.of(joins);

            Lhm joinsStatsMap = new Lhm();
            joinsStatsMap.put("Max joins",               fd(joinsStats.max()));
            joinsStatsMap.put("AVG joins",               fd(joinsStats.mean()));
            joinsStatsMap.put("Median joins",            fd(Quantiles.median().compute(joins)));
            joinsStatsMap.put("3-th quartille joins",    fd(Quantiles.percentiles().index(75).compute(joins)));
            joinsStatsMap.put("97-th percentille joins", fd(Quantiles.percentiles().index(97).compute(joins)));

        statistics.put("joinsStats", joinsStatsMap);

        Lhm nameStats = new Lhm();

            List<Integer> nameLength = vertexMap.keySet().stream().map(String::length).collect(Collectors.toList());

            Stats nameLengthStats = Stats.of(nameLength);
            nameStats.put("Max length",               fd(nameLengthStats.max()));
            nameStats.put("AVG length",               fd(nameLengthStats.mean()));
            nameStats.put("Median length",            fd(Quantiles.median().compute(nameLength)));
            nameStats.put("3-th quartille length",    fd(Quantiles.percentiles().index(75).compute(nameLength)));
            nameStats.put("97-th percentille length", fd(Quantiles.percentiles().index(97).compute(nameLength)));

        statistics.put("nameStats", nameStats);
    }

    public Map<String, Object> getStatistics() {
        if (statistics == null) {
            reCalculateStatistics();
        }
        return statistics;
    }

    public boolean containsVertexWithId(@NotNull String id) {
        return vertexMap.containsKey(id);
    }

    public List<Edge> extractOutgoingEdges(@NotNull String parentVertexId) {
        Vertex parentVertex = vertexMap.get(parentVertexId);
        return parentVertex.getOutgoingEdges();
    }

    public boolean containsDirectedEdgeByIds(@NotNull String id1, @NotNull String id2) {
        return edgeWeigthMap.containsKey(new Edge(new Vertex(id1), new Vertex(id2)));
    }

    public boolean containsDirectedEdge(@NotNull Vertex v1, @NotNull Vertex v2) {
        return edgeWeigthMap.containsKey(new Edge(v1, v2));
    }

    public Edge extractEdgeByIds(@NotNull String id1,@NotNull String id2) {
        Edge keyEdge = new Edge(new Vertex(id1), new Vertex(id2));
        return edgeWeigthMap.get(keyEdge);
    }

    public Edge linkEdge(@NotNull String id1,@NotNull String id2) {
        return linkEdge(new Vertex(id1), new Vertex(id2));
    }

    public Edge linkEdge(Vertex v1, Vertex v2) {
        // TODO: Simplify with putIfAbsent
        Vertex v1real = v1;
        Vertex v2real = v2;
        if (!vertexMap.containsKey(v1.getId())) {
            vertexMap.put(v1.getId(), v1);
        } else {
            v1real = vertexMap.get(v1.getId());
        }
        if (!vertexMap.containsKey(v2.getId())) {
            vertexMap.put(v2.getId(), v2);
        } else {
            v2real = vertexMap.get(v2.getId());
        }

        Edge newEdge = new Edge(v1real, v2real, 1);
        // TODO: Simplify with computeIfPresent()
        if (edgeWeigthMap.containsKey(newEdge)) {
            // Existing edges must upgade weight value
            Edge realEdge = edgeWeigthMap.get(newEdge);
            realEdge.setWeight(realEdge.getWeight() + 1);
            return realEdge;
        } else {
            // New egdes must be added into parent vertex list
            v1real.getOutgoingEdges().add(newEdge);
            edgeWeigthMap.put(newEdge, newEdge);
            return newEdge;
        }
    }

    public Map<Edge, Edge> getEdgeWeigthMap() {
        return edgeWeigthMap;
    }

    public Map<String, Vertex> getVertexMap() {
        return vertexMap;
    }

    @Override
    public String toString() {
        return String.format("Statistics : vertices : %d, edges : %d", vertexMap.size(), edgeWeigthMap.size());
    }
}