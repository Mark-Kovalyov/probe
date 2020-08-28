package mayton.compression.encoders.fibonacci;

import mayton.compression.encoders.CompactDigitEncoder;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

public class FibonacciEncoder extends CompactDigitEncoder {

    public FibonacciEncoder(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void writeInt(int v) throws IOException {
        // TODO:
    }

    @Override
    public void writeLong(long v) throws IOException {
        // TODO:
    }

    @Override
    public void writeBigInteger(BigInteger v) throws IOException {
        // TODO:
    }
}
