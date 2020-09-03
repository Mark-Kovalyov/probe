package mayton.compression.encoders;

import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public abstract class CompactDigitOutputStream implements AutoCloseable, Flushable {

    protected OutputStream outputStream;

    public CompactDigitOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeLong(long v) throws IOException {

    }

    @Override
    public void close() throws Exception {
        outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        outputStream.flush();
    }
}
