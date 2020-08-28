package mayton.compression.character;

import mayton.compression.graphs.Graph;
import mayton.compression.graphs.GraphProcessor;
import mayton.compression.languagespec.ru.RuUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;

public class CharacterProcessor implements GraphProcessor {

    void linkChars(Graph graph, int v1, int v2) {
        graph.linkEdge("" + Character.toLowerCase((char) v1), "" + Character.toLowerCase((char) v2));
    }

    @Override
    public @NotNull Graph upgrade(@NotNull Reader reader, @NotNull Graph graph) throws IOException {
        int c = 0;
        int cp = -1;
        while ((c = reader.read()) >= 0) {
            if (cp != -1) {
                if (RuUtils.isCyrillic(c)) {
                    linkChars(graph, cp, c);
                } else {
                    c = (int)('$');
                    if (cp != '$') {
                        linkChars(graph, cp, c);
                    }
                }
                cp = c;
            }
        }
        return graph;
    }
}