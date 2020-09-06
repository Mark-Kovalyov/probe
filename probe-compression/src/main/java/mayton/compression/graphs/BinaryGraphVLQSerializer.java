package mayton.compression.graphs;

import mayton.compression.encoders.varint.VLQOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class BinaryGraphVLQSerializer extends BinaryGraphSerializer {

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        //OutputStreamWriter osv = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
        //PrintWriter pw = new PrintWriter(osv);
        Map<String, Integer> map = createVertexIdToNumber(graph);
        VLQOutputStream vlqOutputStream = new VLQOutputStream(outputStream);
        for(Map.Entry<Edge, Edge> edgeEntry : graph.getEdgeWeigthMap().entrySet()) {
            Edge edge = edgeEntry.getKey();
            vlqOutputStream.writeLong(map.get(edge.getV1().getId()));
            vlqOutputStream.writeLong(map.get(edge.getV2().getId()));
            vlqOutputStream.writeLong(edge.getWeight());
            /*pw.printf("%d;%d;%d\n",
                    map.get(edge.getV1().getId()),
                    map.get(edge.getV2().getId()),
                    edge.getWeight());*/
        }
        vlqOutputStream.close();
        //osv.close();
    }
}
