package mayton.compression.graphs;

import com.google.common.math.Quantiles;
import com.google.common.math.Stats;
import mayton.compression.Lhm;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Collections.unmodifiableMap;

/**
 * Directed, Weight graph
 */
public class Graph implements Serializable {

    static Logger logger = LoggerFactory.getLogger(Graph.class);

    private Map<Edge, Edge> edgeWeigthMap = new HashMap<>();
    private Map<String, Vertex> vertexMap = new HashMap<>();
    private transient LinkedHashMap<String, Object> statistics;

    private double fd(double v) {
        return v;
    }

    public Graph() {
        vertexMap = new HashMap<>();
        edgeWeigthMap = new HashMap<>();
    }

    public Graph(@IntRange(from = 0) int estimatedVertices, @IntRange(from = 0) int estimatedEdges) {
        vertexMap = new HashMap<>(estimatedVertices);
        edgeWeigthMap = new HashMap<>(estimatedEdges);
    }

    @Nullable
    public Vertex findVertexById(@NotNull String id) {
        return vertexMap.get(id);
    }

    @NotNull
    public Vertex addVertex(@NotNull String id) {
        if (vertexMap.containsKey(id)) {
            return vertexMap.get(id);
        } else {
            Vertex t = new Vertex(id);
            vertexMap.put(id, t);
            return t;
        }
    }

    public void reCalculateStatistics() {
        statistics = new LinkedHashMap<>();

        statistics.put("Vertices", valueOf(vertexMap.size()));
        statistics.put("Edges",    valueOf(edgeWeigthMap.size()));

            List<Integer> weights = edgeWeigthMap.keySet()
                    .stream()
                    .map(Edge::getWeight)
                    .collect(Collectors.toList());

            if (!weights.isEmpty()) {

                Stats stats = Stats.of(weights);
                Lhm weightStatsMap = new Lhm();
                weightStatsMap.put("Max edge weight", fd(stats.max()));
                weightStatsMap.put("AVG edge weight", fd(stats.mean()));
                weightStatsMap.put("Median edge weight", fd(Quantiles.median().compute(weights)));

                Quantiles.percentiles().indexes(75, 80, 85, 90, 95, 97)
                        .compute(weights).entrySet()
                        .stream()
                        .forEach(item ->
                                weightStatsMap.put("" + item.getKey() + "-th percentille edge weight", item.getValue()));

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

            Quantiles.percentiles().indexes(75, 80, 85, 90, 95, 97)
                .compute(joins).entrySet()
                .stream()
                .forEach(item ->
                        joinsStatsMap.put("" + item.getKey() + "-th percentille joins", item.getValue()));

        statistics.put("joinsStats", joinsStatsMap);

        Lhm nameStats = new Lhm();

            List<Integer> nameLength = vertexMap.keySet().stream().map(String::length).collect(Collectors.toList());

            Stats nameLengthStats = Stats.of(nameLength);

            Lhm nameStatsMap = new Lhm();
            nameStatsMap.put("Max length",               fd(nameLengthStats.max()));
            nameStatsMap.put("AVG length",               fd(nameLengthStats.mean()));
            nameStatsMap.put("Median length",            fd(Quantiles.median().compute(nameLength)));

            Quantiles.percentiles().indexes(75, 80, 85, 90, 95, 97)
                .compute(nameLength).entrySet()
                .stream()
                .forEach(item ->
                        nameStatsMap.put("" + item.getKey() + "-th percentille name length", item.getValue()));

        statistics.put("nameStats", nameStatsMap);
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

    @NotNull
    public Optional<List<Edge>> extractOutgoingEdges(@NotNull String parentVertexId) {
        Vertex parentVertex = vertexMap.get(parentVertexId);
        if (parentVertex == null) {
            return Optional.empty();
        } else {
            return Optional.of(parentVertex.getOutgoingEdges());
        }
    }

    @NotNull
    public Optional<List<Edge>> extractIncomingEdges(@NotNull String destinationVertexId) {
        Vertex parentVertex = vertexMap.get(destinationVertexId);
        if (parentVertex == null) {
            return Optional.empty();
        } else {
            return Optional.of(parentVertex.getIncomingEdges());
        }
    }

    public boolean containsDirectedEdgeByIds(@NotNull String id1, @NotNull String id2) {
        return edgeWeigthMap.containsKey(new Edge(new Vertex(id1), new Vertex(id2)));
    }

    public boolean containsDirectedEdge(@NotNull Vertex v1, @NotNull Vertex v2) {
        return edgeWeigthMap.containsKey(new Edge(v1, v2));
    }

    @NotNull
    public Edge extractEdgeByIds(@NotNull String id1,@NotNull String id2) {
        Edge keyEdge = new Edge(new Vertex(id1), new Vertex(id2));
        return edgeWeigthMap.get(keyEdge);
    }

    @NotNull
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
            v1real.addOutgoingEdgeWithWeight(v2real, 1);
            v2real.addIncomingEdge(v1real);
            edgeWeigthMap.put(newEdge, newEdge);
            return newEdge;
        }
    }

    // graph:
    //  statistics:
    //    Vertices: '48955'
    //    Edges: '258215'

    @NotNull
    public Map<Edge, Edge> getEdgeWeigthMap() {
        return unmodifiableMap(edgeWeigthMap);
    }

    @NotNull
    public Map<String, Vertex> getVertexMap() {
        return unmodifiableMap(vertexMap);
    }

    @Override
    public String toString() {
        return format("G(|V|=%d,|E|=%d)", vertexMap.size(), edgeWeigthMap.size());
    }
}
