package mayton.compression;

import mayton.compression.graphs.*;
import mayton.compression.tokens.TokenSentenceProcessor;
import mayton.compression.trie.DictionaryCompactTrie;
import mayton.compression.trie.DictionaryExpanedTrie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("START!");
        Graph graph;

        GraphProcessor graphProcessor = new TokenSentenceProcessor();

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            graph = graphProcessor.process(reader);
        }

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            new DictionaryCompactTrie().transform(reader, new OutputStreamWriter(new FileOutputStream("trie/war-and-society-1-2-3-4.dict.trie")));
        }

        /*try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            new DictionaryExpanedTrie().transform(reader, new OutputStreamWriter(new FileOutputStream("trie/war-and-society-1-2-3-4.expandedtrie")));
        }*/

        new CSVSerializer().serialize(graph,  new FileOutputStream("graphs/war-and-society-1-2-3-4-graph-normalized.csv"));
        new YamlSerializer().serialize(graph, new FileOutputStream("graphs/war-and-society-1-2-3-4-graph.yaml"));
        new JsonSerializer().serialize(graph, new FileOutputStream("graphs/war-and-society-1-2-3-4-graph.json"));


        Properties properties = new Properties();
        properties.put("limit",     Integer.valueOf(args[0]));
        properties.put("selection", args[1]);

        new GraphVizSerializer().serialize(graph, new FileOutputStream("graphviz/war-and-society-1-2-3-4.dot"), properties);

        logger.info(graph.toString());

        logger.info("FINISH!");
    }

}
