package mayton.compression.trie;

import mayton.compression.graphs.Graph;
import mayton.compression.tokens.TokenProcessor;
import mayton.compression.trigrams.TrigramProcessor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;

public class TrieMaker extends TokenProcessor {

    Trie trie = new Trie();

    @Override
    public void processGraphNode(@NotNull String token, @NotNull Graph graph) {

    }

    @Override
    public @NotNull Graph process(@NotNull Reader reader) throws IOException {
        return null;
    }
}
