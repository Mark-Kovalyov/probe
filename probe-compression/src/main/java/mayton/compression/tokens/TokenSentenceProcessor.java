package mayton.compression.tokens;

import mayton.compression.graphs.Graph;
import mayton.compression.graphs.GraphProcessor;
import mayton.compression.languagespec.ru.RuUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class TokenSentenceProcessor implements GraphProcessor {

    static Logger logger = LoggerFactory.getLogger(TokenSentenceProcessor.class);

    public static final String IGNORED_SYMBOLS_WITH_SENTENCE_SEMANTICS = " ,()[]:;\"";

    protected String prevToken = null;

    public void processGraphNode(@NotNull String token, @NotNull Graph graph) {
        if (prevToken != null) {
            logger.debug(":: link {} -> {}", prevToken, token);
            graph.linkEdge(prevToken, token);
        }
        prevToken = token;
    }

    @Override
    public @NotNull Graph upgrade(@NotNull Reader reader, @NotNull Graph graph) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int lines = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (!(line.length() == 0 || StringUtils.isBlank(line))) {
                logger.trace(":: [0] line {}", line);
                String[] tokens = StringUtils.split(line, IGNORED_SYMBOLS_WITH_SENTENCE_SEMANTICS);
                Arrays.stream(tokens)
                        .peek(item -> logger.trace(":: [1] token: '{}'", item))
                        .forEach(token -> {
                            int length = token.length();
                            if (RuUtils.isCyrillicOrHyphensInTheMiddleOrSentenceEnd(token)) {
                                logger.trace(":: [2] pricess token : '{}'", token);
                                if (RuUtils.endSentenceSymbol(token.charAt(length - 1))) {
                                    String lastSentenceToken = token.toLowerCase().substring(0, length - 1);
                                    processGraphNode(lastSentenceToken, graph);
                                    char endSentenceSymbol = token.charAt(length - 1);
                                    processGraphNode(String.valueOf(endSentenceSymbol), graph);
                                    processGraphNode("$", graph);
                                } else {
                                    processGraphNode(token.toLowerCase(), graph);
                                }
                            } else {
                                logger.warn(":: [2] ignored token : '{}'", token);
                            }
                        });
            }
            lines++;
        }
        logger.info("Lines  : {}", lines);
        return graph;
    }
}
