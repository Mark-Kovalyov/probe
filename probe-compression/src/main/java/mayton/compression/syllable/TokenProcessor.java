package mayton.compression.syllable;

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

public class TokenProcessor implements GraphProcessor {

    static Logger logger = LoggerFactory.getLogger(TokenProcessor.class);

    private String prevToken = null;

    private static final String IGNORED_SYMBOLS = " ,.!?()[]:;\"";

    private void processGraphNode(@NotNull String token, @NotNull Graph graph) {
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
                String[] tokens = StringUtils.split(line, IGNORED_SYMBOLS);
                Arrays.asList(tokens)
                        .stream()
                        .peek((item) -> logger.trace(":: [1] token: '{}'", item))
                        .forEach(token -> {
                    if (RuUtils.isCyrillicOrHyphensInTheMiddle(token)) {
                        logger.trace(":: [2] pricess token : '{}'", token);
                        processGraphNode(token.toLowerCase(), graph);
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
