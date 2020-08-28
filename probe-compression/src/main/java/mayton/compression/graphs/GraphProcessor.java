package mayton.compression.graphs;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;

public interface GraphProcessor {

    @NotNull
    default Graph process(@NotNull Reader reader) throws IOException {
        return upgrade(reader, new Graph());
    }

    @NotNull
    Graph upgrade(@NotNull Reader reader, @NotNull Graph graph) throws IOException;

}
