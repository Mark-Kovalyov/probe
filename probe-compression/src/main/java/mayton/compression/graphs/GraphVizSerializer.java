package mayton.compression.graphs;

import mayton.compression.Main;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GraphVizSerializer implements GraphSerializer {

    static Logger logger = LoggerFactory.getLogger(GraphVizSerializer.class);

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        logger.info("STAT serialization into '.dot' format");
        int limit = Integer.parseInt(String.valueOf(properties.getOrDefault("limit", String.valueOf(Integer.MAX_VALUE))));
        PrintWriter printWriter = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
        printWriter.println("digraph D {");
        printWriter.println("node [shape=oval, arrowhead=vee];");
        //printWriter.println("node[style=filled, color=cornflowerblue, fontcolor=white, fontsize=10,  fontname='Helvetica'];");
        //printWriter.println("edge[arrowhead=vee, arrowtail=inv, arrowsize=.7, color=maroon, fontsize=10,  fontcolor=navy];");

        // Render vertices:
        Map<String,Vertex> usedVertices = new HashMap<>();
        // TODO: Optimize with flatten
        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .map(edge -> edge.getV1()).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));

        logger.info("Gathered {} vertices during 1 phase", usedVertices.size());

        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .map(edge -> edge.getV2()).forEach(vertex -> usedVertices.put(vertex.getId(),vertex));

        logger.info("Gathered {} vertices during 2 phase", usedVertices.size());

        usedVertices.values().forEach(vertex -> {
            printWriter.printf("\"%s\" [style=filled, fillcolor = \"%s\" ];\n",
                    vertex.getId(),
                    vertex.getOutgoingEdges().size() > 50 ? "red" : "blue");
        });

        int edgesCount = 0;
        // Render edges:
        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .forEach(edge -> {
                    StringJoiner attributes = new StringJoiner(",","[","]");
                    attributes.add("label =\"" + edge.getWeight() + "\"");
                    if (edge.getWeight() > 200) {//  95-th percentille edge weight: 3.0
                        attributes.add("color=\"red\"");
                    } else {
                        attributes.add("color=\"blue\"");
                    }
                    printWriter.printf("\"%s\" -> \"%s\" %s;\n",
                            edge.getV1().getId(),
                            edge.getV2().getId(),
                            attributes.toString()
                    );
                });

        printWriter.println("}");
        logger.info("FINISH serialization");
    }

}
