package mayton.compression.encoders;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public abstract class CompactDigitEncoder {

    private OutputStream outputStream;

    public CompactDigitEncoder(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeInt(int v) throws IOException {
        writeBigInteger(BigInteger.valueOf(v));
    }

    public void writeLong(long v) throws IOException {
        writeBigInteger(BigInteger.valueOf(v));
    }

    public abstract void writeBigInteger(BigInteger v) throws IOException;

}
