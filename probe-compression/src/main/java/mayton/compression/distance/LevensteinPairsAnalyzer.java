package mayton.compression.distance;

import mayton.compression.GenericTextTransformer;
import mayton.compression.Main;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

public class LevensteinPairsAnalyzer implements GenericTextTransformer {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    static final String IGNORED_SYMBOLS = " /.,!?\"()[]*'-\t^";

    private Map<String, Integer> map = new HashMap<>();

    private void handleToken(String token) {
        map.computeIfPresent(token, (k,v) -> v + 1);
        map.putIfAbsent(token, 1);
    }

    @Override
    public void transform(@NotNull Reader in, @NotNull Writer out) throws IOException {
        PrintWriter pw = new PrintWriter(out);
        pw.println("# Warning! Next sybols will be ignored : " + IGNORED_SYMBOLS);
        pw.println("# Warning! All letters will be in lower-case");
        BufferedReader bufferedReader = new BufferedReader(in);
        LevenshteinDistance levenstein = LevenshteinDistance.getDefaultInstance();
        String line;
        int lines = 0;
        while((line = bufferedReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line, IGNORED_SYMBOLS);
            Arrays.stream(tokens)
                    .map(token -> token.toLowerCase())
                    .forEach(token -> handleToken(token));
            lines++;
        }
        /*for(Map.Entry<String,Integer> entry : map.entrySet()) {
            pw.printf("%s - %s\n", levenstein.apply());
        }*/
    }

    public static void main(String[] args) throws IOException {

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            new LevensteinPairsAnalyzer().transform(
                    reader, new FileWriter("csv/war-and-society-1-2-3-4.levenstein-pairs.csv"));
        }
    }

}
