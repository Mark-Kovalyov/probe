package mayton.compression.syllable;

import mayton.compression.graphs.Graph;
import mayton.compression.graphs.GraphProcessor;
import mayton.compression.languagespec.SyllableSplitter;
import mayton.compression.languagespec.ru.RuSyllableSplitter;
import mayton.compression.languagespec.ru.RuUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class SyllableProcessor implements GraphProcessor {

    static Logger logger = LoggerFactory.getLogger(SyllableProcessor.class);

    private String prevSyllable = null;

    private void processGraphNode(@NotNull String syllable, @NotNull Graph graph) {
        if (prevSyllable != null) {
            logger.debug("::::[4] {} -> {}", prevSyllable, syllable);
            if (prevSyllable.equals("$") && syllable.equals("$")) {

            } else {
                graph.linkEdge(prevSyllable, syllable);
            }
        }
        prevSyllable = syllable;
    }

    @Override
    public @NotNull Graph upgrade(@NotNull Reader reader, @NotNull Graph graph) throws IOException {
        SyllableSplitter splitter = new RuSyllableSplitter();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        int lines = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (line.length() == 0 || StringUtils.isBlank(line)) {
                lines++;
            } else {
                String[] tokens = StringUtils.split(line, " ,.!?");
                logger.info(":[1] {}", line);
                prevSyllable = null;
                processGraphNode("$", graph);
                Arrays.asList(tokens).forEach(token -> {
                    logger.debug("::[2] {}", token);
                    if (RuUtils.isCyrillic(token)) {
                        splitter.split(token).forEach(syllable -> {
                            logger.debug(":::[3] {}", syllable);
                            processGraphNode(syllable, graph);
                        });
                    }
                    processGraphNode("$", graph);
                });
                lines++;
            }
        }
        logger.info("Lines  : {}", lines);
        return graph;
    }
}
