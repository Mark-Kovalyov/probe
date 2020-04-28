package mayton.network.dht.experimental;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DhtVisitor extends SimpleFileVisitor<Path> {

    private static Logger logger = LogManager.getLogger(DhtVisitor.class);

    public DhtVisitor() {
        super();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String path = file.getFileName().toString();
        if (path.endsWith(".pdf")) {

        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) throws IOException {
        DhtVisitor dhtVisitor = new DhtVisitor();
        Files.walkFileTree(Path.of("/storage/documents"), dhtVisitor);
    }
}
