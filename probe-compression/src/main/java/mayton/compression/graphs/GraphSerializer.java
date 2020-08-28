package mayton.compression.graphs;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public interface GraphSerializer {

    void serialize(@NotNull Graph graph,@NotNull OutputStream outputStream, @NotNull Properties properties) throws IOException;

    default void serialize(@NotNull Graph graph,@NotNull OutputStream outputStream) throws IOException {
        serialize(graph, outputStream, new Properties());
    }

}
