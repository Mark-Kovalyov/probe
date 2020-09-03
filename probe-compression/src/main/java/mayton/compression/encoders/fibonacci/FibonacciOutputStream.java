package mayton.compression.encoders.fibonacci;

import mayton.compression.encoders.CompactDigitOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static mayton.compression.encoders.fibonacci.FibonacciUtils.encodeF;

public class FibonacciOutputStream extends CompactDigitOutputStream {

    protected int register = 0;
    protected int shiftCount = 0;
    protected boolean paddingBlockWrote = false;
    protected DataOutputStream dataOutputStream;

    public FibonacciOutputStream(OutputStream outputStream) {
        super(outputStream);
        dataOutputStream = new DataOutputStream(outputStream);
    }

    private void writeBit(boolean v) throws IOException {
        register |= v ? 0x8000_0000 : 0x0000_0000;
        register >>>= 1;
        shiftCount++;
        if (shiftCount == 32) {
            dataOutputStream.writeInt(register);
            register = 0;
        }
    }

    @Override
    public void writeLong(long v) throws IOException {
        writeBit(true);
        String res = encodeF(v + 1);
        int length = res.length();
        for (int i = 0; i < length; i++) {
            if (res.charAt(i) == '1') {
                register |= 0x8000_0000;
            }
            register >>>= 1;
            shiftCount++;
            if (shiftCount == 32) {
                dataOutputStream.writeInt(register);
                register = 0;
            }
        }
    }

    private void writePaddingMarker() throws IOException {
        dataOutputStream.writeByte(shiftCount);
        dataOutputStream.writeByte(shiftCount);
        dataOutputStream.writeByte(shiftCount);
        dataOutputStream.writeByte(shiftCount);
    }

    @Override
    public void close() throws Exception {
        outputStream.flush();
        outputStream.close();
    }

    @Override
    public void flush() throws IOException {
        if (!paddingBlockWrote) {
            if (shiftCount > 0) {
                dataOutputStream.writeInt(register);
            }
            writePaddingMarker();
            dataOutputStream.flush();
            paddingBlockWrote = true;
        }
    }
}
