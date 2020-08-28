package mayton.compression.encoders;

import java.io.IOException;
import java.io.OutputStream;

/**
 * PKCS#5 and PKCS#7 padding
 */
public class PaddedBitOutputStream implements AutoCloseable{

    private OutputStream outputStream;
    private int register;
    private int cnt = 0;

    public PaddedBitOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeBitChain(int chain, int count) throws IOException {
        // TODO:
    }

    public void writeBit(boolean v) throws IOException {
        register |= v ? 0x8000_0000 : 0;
        register >>= 1;
        cnt++;
        if (cnt == 32) {
            outputStream.write(0xFF & (register >> 3));
            outputStream.write(0xFF & (register >> 2));
            outputStream.write(0xFF & (register >> 1));
            outputStream.write(0xFF & (register));
            cnt = 0;
            register = 0;
        }
    }

    @Override
    public void close() throws Exception {
        outputStream.write(0xFF & (register >> 3));
        outputStream.write(0xFF & (register >> 2));
        outputStream.write(0xFF & (register >> 1));
        outputStream.write(0xFF & (register));

        outputStream.close();
    }
}
