package mayton.compression.encoders;

import java.io.IOException;
import java.io.InputStream;

public abstract class CompactDigitInputStream implements AutoCloseable {

    protected InputStream inputStream;

    public CompactDigitInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    /**
     * Tries to read next compact digit
     *
     * @return -1 when compact input stream is finished
     * @throws IOException
     */
    public abstract long readLong() throws IOException;

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
