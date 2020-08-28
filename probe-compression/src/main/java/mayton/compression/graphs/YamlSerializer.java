package mayton.compression.graphs;

import mayton.compression.EncodingTools;
import mayton.compression.Lhm;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class YamlSerializer implements GraphSerializer {

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        DumperOptions dumperOptions = new DumperOptions();
        Yaml yaml = new Yaml(dumperOptions);
        Lhm lhm = new Lhm();

        graph.getVertexMap().entrySet().stream().forEach(entry -> {
            List<String> strings = new ArrayList<>();

            entry.getValue().getOutgoingEdges()
                    .stream()
                    .map(edge -> Pair.of(
                            edge.getV2().getId(),
                            edge.getWeight()))
                    //.sorted((pair1, pair2) -> Integer.compare(pair2.getRight(), pair1.getRight()))
                    .sorted((pair1, pair2) -> pair1.getLeft().compareTo(pair2.getLeft()))
                    .forEach(pair -> strings.add(pair.getLeft() + ":" + pair.getRight()));

            lhm.put(entry.getKey(),
                    EncodingTools.quadroMap(
                            "outgoing-edges-count", strings.size(),
                            "outgoing-edges", strings));
        });

        Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

        yaml.dump(
                Collections.singletonMap("graph", new LinkedHashMap(){{
                    put("statistics", graph.getStatistics());
                    put("data", lhm);
                }}),
                writer);
    }
}
