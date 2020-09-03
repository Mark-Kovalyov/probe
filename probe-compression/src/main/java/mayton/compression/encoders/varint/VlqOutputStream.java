package mayton.compression.encoders.varint;

import mayton.compression.encoders.CompactDigitOutputStream;

import java.io.IOException;
import java.io.OutputStream;

public class VlqOutputStream extends CompactDigitOutputStream {

    public VlqOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override
    public void writeLong(long v) throws IOException {
        long vv = v;
        do {

        } while(vv > 0);
    }


}
