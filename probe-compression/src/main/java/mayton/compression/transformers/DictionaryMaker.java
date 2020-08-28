package mayton.compression.transformers;

import mayton.compression.GenericTextTransformer;
import mayton.compression.MarkoffChainAnalyzer;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.TreeMap;

import static java.nio.charset.StandardCharsets.UTF_8;

public class DictionaryMaker implements GenericTextTransformer {

    static Logger logger = LoggerFactory.getLogger(MarkoffChainAnalyzer.class);

    private OrderBy orderBy;

    public DictionaryMaker(OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    enum OrderBy {
        TOKEN,
        FREQ
    }

    static final String IGNORED_SYMBOLS = " /.,!?\"()[]*'-\t^";

    private TreeMap<String, Integer> map = new TreeMap<>();

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
        String line;
        int lines = 0;
        while((line = bufferedReader.readLine()) != null) {
            String[] tokens = StringUtils.split(line, IGNORED_SYMBOLS);
            Arrays.stream(tokens)
                    .map(token -> token.toLowerCase())
                    .forEach(token -> handleToken(token));
            lines++;
        }
        int n = 0;
        switch (orderBy) {
            case TOKEN:
                map.forEach((key, value) -> {
                    pw.print(key);
                    pw.print(",");
                    pw.println(value);
                });
                break;
            case FREQ:
                map.entrySet().stream()
                        .sorted((e1,e2) -> Integer.compare(e2.getValue(),e1.getValue()))
                        .forEach(entry -> {
                    pw.print(entry.getKey());
                    pw.print(",");
                    pw.println(entry.getValue());
                });
                break;
        }
        logger.info("lines         : {}", lines);
        logger.info("unique tokens : {}", map.size());
    }

    public static void main(String[] args) throws IOException {

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            new DictionaryMaker(OrderBy.TOKEN).transform(
                    reader, new FileWriter("war-and-society-1-2-3-4.dictionary-by-token.csv"));
        }

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            new DictionaryMaker(OrderBy.FREQ).transform(
                    reader, new FileWriter("war-and-society-1-2-3-4.dictionary-by-freq.csv"));
        }
    }

}
