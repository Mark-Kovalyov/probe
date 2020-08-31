package mayton.compression;

import mayton.compression.graphs.*;
import mayton.compression.tokens.TokenProcessor;
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

        GraphProcessor graphProcessor = new TokenProcessor();

        try (Reader reader = new FileReader("text/war-and-society-1-2-3-4.utf-8.txt", UTF_8)) {
            graph = graphProcessor.process(reader);
        }

        new YamlSerializer().serialize(graph, new FileOutputStream("graphs/war-and-society-1-2-3-4.yaml"));

        Properties properties = new Properties();
        properties.put("limit",     Integer.valueOf(args[0]));
        properties.put("selection", args[1]);

        new GraphVizSerializer().serialize(graph, new FileOutputStream("graphviz/war-and-society-1-2-3-4.dot"), properties);
        logger.info(graph.toString());

        logger.info("FINISH!");
    }

}
