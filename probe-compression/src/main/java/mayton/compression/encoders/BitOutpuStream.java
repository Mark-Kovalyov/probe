package mayton.compression.encoders;

import java.io.*;

public class BitOutpuStream implements Closeable, Flushable {

    private long buffer;
    private int cnt;

    private DataOutputStream dataOutputStream;

    public BitOutpuStream(OutputStream dataOutputStream) {
        this.dataOutputStream = new DataOutputStream(dataOutputStream);
        this.cnt = 0;
        this.buffer = 0L;
    }

    public void writeBit(int x) {
        writeIntBits(1,1);
    }

    public void writeQubit(int x) {
        writeIntBits(2,1);
    }

    public void writeTriplex(int x) {
        writeIntBits(3,1);
    }

    public void writeQuadrit(int x) {
        writeIntBits(4,1);
    }

    public void writeIntBits(int bits, int count) {

    }

    @Override
    public void close() throws IOException {
        dataOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        dataOutputStream.flush();
    }
}
