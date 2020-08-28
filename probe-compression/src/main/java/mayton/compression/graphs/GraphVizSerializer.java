package mayton.compression.graphs;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class GraphVizSerializer implements GraphSerializer {

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        int limit = Integer.parseInt(String.valueOf(properties.getOrDefault("limit", "10000000")));
        PrintWriter printWriter = new PrintWriter(outputStream, true, StandardCharsets.UTF_8);
        printWriter.println("digraph D {");
        printWriter.println("node [shape=oval];");
        graph.getEdgeWeigthMap().keySet().stream()
                .sorted((e1,e2) -> Integer.compare(e2.getWeight(), e1.getWeight()))
                .limit(limit)
                .forEach(edge -> {
            printWriter.printf("\"%s\" -> \"%s\" [ label = \"%s\" ]; \n",
                    edge.getV1().getId(),
                    edge.getV2().getId(),
                    edge.getWeight()
            );
        });
        printWriter.println("}");
    }

}
