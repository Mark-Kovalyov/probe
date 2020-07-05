package mayton.probe.docindexer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DocFileVizitor extends SimpleFileVisitor<Path> {

    private DocConsumer docConsumer = new DocConsumer();

    static Logger logger = LogManager.getLogger(DocFileVizitor.class);

    private int count;

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return super.preVisitDirectory(dir, attrs);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String absolute = file.toAbsolutePath().toString();
        if (absolute.toLowerCase().endsWith(".pdf")) {
            logger.info("absolute = '{}'", absolute);
            docConsumer.accept(new FileInputStream(file.toFile()));
            count++;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return super.visitFileFailed(file, exc);
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return super.postVisitDirectory(dir, exc);
    }

    public DocConsumer getDocConsumer() {
        return docConsumer;
    }

    public void setDocConsumer(DocConsumer docConsumer) {
        this.docConsumer = docConsumer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
