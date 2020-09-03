package mayton.compression.encoders.bcd;

import mayton.compression.encoders.CompactDigitOutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class BcdOutputStream extends CompactDigitOutputStream {

    public BcdOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void writeLong(long v) throws IOException {
        // 64 bit = 5 + 59
    }
}
