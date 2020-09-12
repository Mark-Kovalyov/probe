package mayton.compression.graphs;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class SimpleEdgeSerializerCSV extends BinaryGraphSerializer {

    static Logger logger = LoggerFactory.getLogger(SimpleEdgeSerializerCSV.class);

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        OutputStreamWriter osv = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        PrintWriter pw = new PrintWriter(osv);
        Map<String, Integer> map = createVertexIdToNumber(graph);
        for(Edge edge : graph.getEdgeWeigthMap().keySet()) {
            pw.print(map.get(edge.getV1().getId()));
            pw.print(",");
            pw.println(map.get(edge.getV2().getId()));
        }
        pw.close();
    }
}
