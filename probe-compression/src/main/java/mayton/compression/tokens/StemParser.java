package mayton.compression.tokens;

import mayton.compression.graphs.Graph;

import org.apache.lucene.analysis.ru.RussianLightStemFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;

import org.jetbrains.annotations.NotNull;

import org.tartarus.snowball.ext.RussianStemmer;

public class StemParser extends TokenProcessor {

    private static RussianLightStemFilter russianLightStemFilter = null;
    private static RussianStemmer russianStemmer = new RussianStemmer();
    private static SnowballFilter snowballFilter = null;

    static {
        /*TokenStream tokenStream = new RussianLightStemFilter(new To);
        snowballFilter = new SnowballFilter(tokenStream,"");*/
    }

    @Override
    public void processGraphNode(@NotNull String token, @NotNull Graph graph) {
        if (prevToken != null) {
            logger.debug(":: link {} -> {}", prevToken, token);
            graph.linkEdge(prevToken, token);
        }
        prevToken = token;
    }
}
