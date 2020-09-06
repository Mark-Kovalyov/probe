package mayton.compression.encoders;

import mayton.compression.NumericUtils;

import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;

public class BitOutpuStream implements Closeable, Flushable {

    public static int masks[] = {
            0b00000000_00000000_00000000_00000001,
            0b00000000_00000000_00000000_00000011,
            0b00000000_00000000_00000000_00000111,
            0b00000000_00000000_00000000_00001111,
            0b00000000_00000000_00000000_00011111,
            0b00000000_00000000_00000000_00111111,
            0b00000000_00000000_00000000_01111111,
            0b00000000_00000000_00000000_11111111,
            0b00000000_00000000_00000001_11111111,
            0b00000000_00000000_00000011_11111111,
            0b00000000_00000000_00000111_11111111,
            0b00000000_00000000_00001111_11111111,
            0b00000000_00000000_00011111_11111111,
            0b00000000_00000000_00111111_11111111,
            0b00000000_00000000_01111111_11111111,
            0b00000000_00000000_11111111_11111111
    };

    private int buffer;
    private int cnt;

    private DataOutputStream dataOutputStream;

    public BitOutpuStream(OutputStream dataOutputStream) {
        this.dataOutputStream = new DataOutputStream(dataOutputStream);
        this.cnt = 0;
        this.buffer = 0;
    }

    public void writeBit(int x)  throws IOException {
        checkArgument(x >= 0, "Unable to encode negative value " + x);
        writeIntBits(1,1);
    }

    public void writeQubit(int x)  throws IOException {
        checkArgument(x >= 0, "Unable to encode negative value " + x);
        writeIntBits(2,1);
    }

    public void writeTriplex(int x)  throws IOException {
        checkArgument(x >= 0, "Unable to encode negative value " + x);
        writeIntBits(3,1);
    }

    public void writeQuadrit(int x)  throws IOException {
        checkArgument(x >= 0, "Unable to encode negative value " + x);
        writeIntBits(4,1);
    }

    public void writeIntBits(int bits) throws IOException {
        writeIntBits(bits, NumericUtils.detectWidthInBits(bits));
    }

    public void writeIntBits(int bits, int count) throws IOException {
        checkArgument(bits >= 0, "Unable to encode negative value " + bits);
        checkArgument(count > 0, "Unable to encode zero or negative count of bits = " + count);
        // TODO:
        /*boolean isOverlapped = false;
        bits <<= (cnt - count);
        if (isOverlapped) {
            dataOutputStream.writeInt(buffer);
        } else {
            cnt+=count;
            int x = 0;
            buffer |= ( bits & masks[count - 1] << x );
        }*/
        bits <<= 31;
        buffer |= bits;
    }

    @Override
    public void close() throws IOException {
        dataOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        dataOutputStream.writeInt(buffer);
        dataOutputStream.flush();
    }
}
