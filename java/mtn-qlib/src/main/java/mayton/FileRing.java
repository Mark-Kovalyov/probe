package mayton;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import javax.annotation.concurrent.ThreadSafe;
import java.io.*;

@ThreadSafe
public class FileRing implements Q {

    static Logger logger = org.apache.log4j.Logger.getLogger(FileRing.class);

    private int maxFileSize;
    private int reserv;
    private String fname;
    private long readPos;
    private long flushedWritePos;
    private long flushedFileSize;
    private FileInputStream inStream;
    private MyFileOutputStream outStream;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public FileRing(@NotNull String fname, int maxFileSize, int reserv) throws IOException {
        this.fname = fname;
        this.maxFileSize = maxFileSize;
        this.reserv = reserv;
        outStream = new MyFileOutputStream(fname);
        out = new ObjectOutputStream(outStream);
        inStream = new FileInputStream(fname);
        in = new ObjectInputStream(inStream);
    }

    public void add(@NotNull Serializable entity) throws IOException {
        out.writeObject(entity);
        this.flush();
    }

    /* Достигли конца очереди при записи, переходим в начало */
    private void writeEndOfCircle() throws IOException {
        //
        logger.debug("writeEndOfCircle!");
        this.flushedFileSize = this.flushedWritePos;
        logger.debug("this.flushedFileSize=" + this.flushedFileSize);
        // позиционируемся в начало
        // "Как-бы" закрываем поток и пересоздаем его
        this.out.close();
        this.out = new ObjectOutputStream(this.outStream);
    }

    public void flush() throws IOException {
        out.flush();
        this.flushedWritePos = outStream.getChannel().position();
        logger.debug("flushedWritePos=" + flushedWritePos);
        // достигли конец файла
        if (this.flushedWritePos > (this.maxFileSize - this.reserv)) {
            this.writeEndOfCircle();
        }
    }

    public boolean isEmpty() {
        return this.readPos == this.flushedWritePos;
    }

    public Serializable poll() throws IOException, ClassNotFoundException {
        Serializable o;
        // Достигли конца файла
        if (this.readPos == this.flushedFileSize) {
            logger.info("end of file at read. readPos=" + this.readPos);
            // Достигли конца файла, переоткрываем его
            this.in.close();
            this.inStream = new FileInputStream(this.fname);
            this.in = new ObjectInputStream(this.inStream);
            this.readPos = 0;
        }
        if (isEmpty()) {
            return null;
        }
        o = (Serializable) in.readObject();
        this.readPos = this.inStream.getChannel().position();
        logger.debug("readPos=" + readPos);
        return o;
    }

    public void purge() {
        this.readPos = this.flushedWritePos = 0;
    }
}