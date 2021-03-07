package mayton.lucene.bitemporal;

import org.apache.commons.io.input.CountingInputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.GZIPInputStream;

public class GZipVizitor extends SimpleFileVisitor<Path> {

    private IndexWriter writer;

    private int processedFiles;

    private long processedBytes = 0;

    private long startTime = 0;

    static Logger logger = LogManager.getLogger(GZipVizitor.class);

    public GZipVizitor(IndexWriter writer) {
        this.writer = writer;
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        try {
            String path = file.toString();
            String lowerPath = path.toLowerCase();
            int index = lowerPath.indexOf('.');
            if (index >= 0 && lowerPath.endsWith(".gz")) {
                logger.info("::1 {}", path);
                File fileObject = new File(path);
                try(GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(fileObject))) {
                    logger.info("::2 add");
                    Document document = new Document();
                    document.add(new StringField("id", path, Field.Store.YES));
                    CountingInputStream cis = new CountingInputStream(gzipInputStream);
                    document.add(new TextField("body", new InputStreamReader(cis, "UTF-8")));
                    writer.addDocument(document);
                    logger.info("::3 close. Readed {} bytes", cis.getByteCount());
                    processedBytes += cis.getByteCount();
                    processedFiles++;
                    if (processedFiles % 1000 == 0) {
                        logger.info("commit");
                        writer.commit();
                    }
                } catch (IOException ex) {
                    logger.warn(ex);
                    throw ex;
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

    public int getProcessedFiles() {
        return processedFiles;
    }

    public long getProcessedBytes() {
        return processedBytes;
    }
}
