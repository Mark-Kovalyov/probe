package mayton.lucene.text;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class DocVizitor extends SimpleFileVisitor<Path> {

    static Logger logger = LogManager.getLogger(DocVizitor.class);
    int commitAfter = 100;
    int commits = 0;
    int files = 0;
    boolean ignoreAccessDenied = true;

    private IndexWriter writer;

    public static Set<String> textExtensions = unmodifiableSet(new HashSet() {{
        add("txt");
        add("htm");
        add("html");
        add("ps");
        add("c");
        add("java");
        add("cpp");
        add("h");
        add("hpp");
        add("lst");
        add("csv");
        add("xml");
        add("xhtml");
    }});

    public DocVizitor(IndexWriter writer, boolean ignoreAccessDenied) {
        this.writer = writer;
        this.ignoreAccessDenied = ignoreAccessDenied;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        if (dir.toString().startsWith(".") && !dir.startsWith("./")) {
            logger.info(":: Skipped folder {}", dir.toString());
            return FileVisitResult.SKIP_SUBTREE;
        } else {
            return FileVisitResult.CONTINUE;
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try {
            Document document = new Document();
            String path = file.toString();
            String lowerPath = path.toLowerCase();
            int index = lowerPath.indexOf('.');
            if (index >= 0 && textExtensions.contains(lowerPath.substring(index + 1))) {
                File fileObject = new File(path);
                logger.info(":: {}", path);
                document.add(new TextField("body", IOUtils.toString(new FileInputStream(fileObject), StandardCharsets.UTF_8), Field.Store.NO));
                document.add(new StringField("length", String.valueOf(fileObject.length()), Field.Store.YES));
                document.add(new StringField("path", file.toString(), Field.Store.YES));
                // TODO: format
                document.add(new StringField("lastUpdate", String.valueOf(fileObject.lastModified()), Field.Store.YES));
                writer.addDocument(document);
                commits++;
                files++;
                if (commits > commitAfter) {
                    writer.commit();
                    logger.info(":: commit, files = {}", files);
                    commits = 0;
                }
            }
        } catch (NoSuchFileException ex) {
            logger.warn(ex);
            return FileVisitResult.CONTINUE;
        } catch (Exception ex) {
            logger.error(ex);
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        logger.warn(":: visitFileFailed for {}", file.toString());
        if (ignoreAccessDenied) {
            logger.warn(":: skip siblings");
            return FileVisitResult.SKIP_SIBLINGS;
        } else {
            return super.visitFileFailed(file, exc);
        }
    }


}
