package mayton.compression.graphs;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class GraphVizSerializer implements GraphSerializer {

    static Logger logger = LoggerFactory.getLogger(GraphVizSerializer.class);

    enum Selection {
        RANDOM,
        ORDERED
    }

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        logger.info("STAT serialization into '.dot' format");
        int limit           = parseInt(valueOf(properties.getOrDefault("limit", valueOf(Integer.MAX_VALUE))));
        Selection selection = Selection.valueOf((String) properties.getOrDefault("selection", Selection.ORDERED.name()));

        PrintWriter printWriter = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
        renderHead(printWriter);

        if (selection == Selection.ORDERED) {
            renderOrdered(graph, limit, printWriter);
        } else {
            renderRandom(graph, limit, printWriter);
        }

        printWriter.println("}");
        logger.info("FINISH serialization");
    }

    @NotNull
    private void renderHead(PrintWriter printWriter) {
        printWriter.println("digraph D {");
        printWriter.println("node [shape=oval, arrowhead=vee];");
    }

    private void renderEdges(Collection<Edge> edges, @NotNull Graph graph, int limit, PrintWriter printWriter,
                             Consumer<Vertex> vertexRenderer,
                             Consumer<Edge> edgeRenderer) {
        Map<String,Vertex> usedVertices = new HashMap<>();
        edges.stream().map(Edge::getV1).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));
        edges.stream().map(Edge::getV2).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));
        usedVertices.values().forEach(vertex ->
                printWriter.printf("\"%s\" [style=filled, fillcolor = \"%s\" ];\n",
                        vertex.getId(),
                        vertex.getOutgoingEdges().size() > 50 ? "red" : "blue"));

        edges.stream()
                .forEach(edge -> {
                    StringJoiner attributes = new StringJoiner(",","[","]");
                    attributes.add("label =\"" + edge.getWeight() + "\"");
                    attributes.add(format("color=\"%s\"", edge.getWeight() > 200 ? "red" : "blue"));
                    printWriter.printf("\"%s\" -> \"%s\" %s;\n",
                            edge.getV1().getId(),
                            edge.getV2().getId(),
                            attributes.toString()
                    );
                });
    }

    private void renderRandom(@NotNull Graph graph, int limit, PrintWriter printWriter) {
        logger.info("random selection with limit = {}", limit);
        // Render vertices:
        Map<Integer, Edge> index2Edge = new HashMap<>();
        int i = 0;
        for(Edge edge : graph.getEdgeWeigthMap().values()) {
            index2Edge.put(i++, edge);
        }
        Random random = new Random();
        int size = index2Edge.size();
        List<Edge> randomEdges = new ArrayList<>();
        IntStream.range(0, limit).forEach(idx ->
                randomEdges.add(idx, index2Edge.get(random.nextInt(size))));
        renderEdges(randomEdges, graph, limit, printWriter, null, null);
    }

    private void renderOrdered(@NotNull Graph graph, int limit, PrintWriter printWriter) {
        logger.info("ordered selection with limit = {}", limit);
        // Render vertices:
        Map<String,Vertex> usedVertices = new HashMap<>();
        // TODO: Optimize with flatten
        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .map(Edge::getV1).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));

        logger.info("Gathered {} vertices during 1 phase", usedVertices.size());

        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .map(Edge::getV2).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));

        logger.info("Gathered {} vertices during 2 phase", usedVertices.size());

        usedVertices.values().forEach(vertex ->
            printWriter.printf("\"%s\" [style=filled, fillcolor = \"%s\" ];\n",
                    vertex.getId(),
                    vertex.getOutgoingEdges().size() > 50 ? "red" : "blue"));

        int edgesCount = 0;
        // Render edges:
        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .forEach(edge -> {
                    StringJoiner attributes = new StringJoiner(",","[","]");
                    attributes.add("label =\"" + edge.getWeight() + "\"");
                    attributes.add(format("color=\"%s\"", edge.getWeight() > 200 ? "red" : "blue"));
                    printWriter.printf("\"%s\" -> \"%s\" %s;\n",
                            edge.getV1().getId(),
                            edge.getV2().getId(),
                            attributes.toString()
                    );
                });
    }

}
