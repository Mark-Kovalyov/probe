package mayton.probe.docindexer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAccumulator;

public class Main {

    static Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        logger.info("Begin");
        DocFileVizitor docFileVizitor = new DocFileVizitor();
        Files.walkFileTree(Paths.get("/documents") , docFileVizitor);
        logger.info("End files = {}", docFileVizitor);
    }

}
