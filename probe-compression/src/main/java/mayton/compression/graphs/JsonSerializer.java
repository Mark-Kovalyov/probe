package mayton.compression.graphs;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Map;
import java.util.Properties;

public class JsonSerializer implements GraphSerializer {

    static Logger logger = LoggerFactory.getLogger(JsonSerializer.class);

    @Override
    public void serialize(@NotNull Graph graph, @NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException {
        JsonFactory jfactory = new JsonFactory();
        try(JsonGenerator jGenerator = jfactory.createGenerator(outputStream, JsonEncoding.UTF8)) {
            jGenerator.writeStartObject();
            jGenerator.writeObjectField("vertices", graph.getVertexMap().size());
            jGenerator.writeObjectField("edges", graph.getEdgeWeigthMap().size());
            jGenerator.writeArrayFieldStart("data");

            for (Map.Entry<String, Vertex> e : graph.getVertexMap().entrySet()) {
                jGenerator.writeStartObject();
                jGenerator.writeStringField("name", e.getKey());
                jGenerator.writeNumberField("outgoing-edges-count", e.getValue().getOutgoingEdges().size());
                jGenerator.writeArrayFieldStart("outgoing-edges");
                for (Edge edgeEntry : e.getValue().getOutgoingEdges()) {
                    jGenerator.writeString(edgeEntry.getV2().getId() + ":" + edgeEntry.getWeight());
                }
                jGenerator.writeEndArray();
                jGenerator.writeEndObject();
            }

            jGenerator.writeEndArray();
            jGenerator.writeEndObject();
        }
    }
}
