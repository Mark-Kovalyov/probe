package mayton.compression.trigrams;

import mayton.compression.EncodingTools;
import mayton.compression.graphs.Edge;
import mayton.compression.graphs.Graph;
import mayton.compression.graphs.Vertex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static mayton.compression.EncodingTools.quadroMap;
import static mayton.compression.languagespec.ru.RuUtils.isCyrillic;

public class TrigramProcessor {

    static Logger logger = LoggerFactory.getLogger(TrigramProcessor.class);

    static Set<String> ignored = new HashSet<>();

    String prevTrigram = null;

    int skipTrigram = 0;

    private Graph graph;

    Map<String, Integer> trigrams = new HashMap<>();

    static String trigramToStr(int c1, int c2, int c3) {
        return StringUtils.trim("" + (char) c1 + (char) c2 + (char) c3);
    }

    void upgradeTrigrams(String currentTrigram) {
        String wrapped = EncodingTools.escape(currentTrigram);
        trigrams.putIfAbsent(wrapped, 1);
        trigrams.computeIfPresent(wrapped, (key, oldValue) -> oldValue + 1);
        if (prevTrigram == null) {
            prevTrigram = currentTrigram;
        } else {
            skipTrigram++;
            if (skipTrigram == 3) {
                logger.info("add Next syllable : {}", currentTrigram);
                Vertex v1 = graph.addVertex(currentTrigram);
                Vertex v2 = graph.addVertex(prevTrigram);
                graph.linkEdge(v1, v2);
                skipTrigram = 0;
            }
            prevTrigram = currentTrigram;
        }
    }

    private void processTrigrams() throws IOException {
        int c1 = -1;
        int c2 = -1;
        int c3 = -1;
        try (InputStreamReader reader = new InputStreamReader(new FileInputStream("text/war-and-society-3.txt"), StandardCharsets.UTF_8)) {
            while((c1 = reader.read()) != -1) {
                if (c1 == '\n') {
                    continue;
                }
                if (c1 != -1 && c2 != -1 && c3 != -1) {
                    if (isCyrillic(c1)) {
                        if (c2 == ' ' && c3 == ' ') {
                            upgradeTrigrams(trigramToStr(c1,c2,c3).toLowerCase());
                        } else if (isCyrillic(c2) && c3 == ' '){
                            upgradeTrigrams(trigramToStr(c1,c2,c3).toLowerCase());
                        } else if (isCyrillic(c2) && isCyrillic(c3)){
                            upgradeTrigrams(trigramToStr(c1,c2,c3).toLowerCase());
                        }
                    } else {
                        //logger.info("Skipping trigram {}", trigramToStr(c1,c2,c3));
                        ignored.add(EncodingTools.escape(trigramToStr(c1,c2,c3)));
                    }
                }
                c3 = c2;
                c2 = c1;
            }
        }

        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setPrettyFlow(false);
        Yaml yaml = new Yaml(dumperOptions);

        LinkedHashMap<String, Integer> sortedByAlpabet = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> sortedByFreq = new LinkedHashMap<>();

        trigrams.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .forEach(item -> sortedByAlpabet.put(item.getKey(), item.getValue()));

        trigrams.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .forEach(item -> sortedByFreq.put(item.getKey(), item.getValue()));

        LinkedHashMap<String, Object> trigramReport = new LinkedHashMap<>();
        trigramReport.put("sorted-by-alpha",
                quadroMap("count", sortedByAlpabet.size(), "values", sortedByAlpabet));
        trigramReport.put("sorted-by-freq",
                quadroMap("count", sortedByFreq.size(), "values", sortedByFreq));

        trigramReport.put("ignored", ignored.stream().collect(Collectors.toList()));

        yaml.dump(
                Collections.singletonMap("trigrams", trigramReport),
                new FileWriter("war-and-society-3.trigrams.yaml"));
    }

}
