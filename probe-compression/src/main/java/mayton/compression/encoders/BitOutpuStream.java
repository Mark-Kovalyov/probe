package mayton.compression.encoders;

import mayton.compression.NumericUtils;
import org.jetbrains.annotations.NotNull;

import java.io.*;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Stream of variable-length bit blocks witn PKCS#7 padding
 */
public class BitOutpuStream implements Closeable, Flushable {

    private int masks[] = new int[32];

    private int buffer;
    private int bufferFilledBits;
    private boolean pkcs7closed = false;

    private DataOutputStream dataOutputStream;

    private void initMasks() {
        int filler = 0x01;
        for(int i = 0 ; i < 32 ; i++) {
            masks[i] = filler;
            filler |= (filler << 1);
        }
    }

    public BitOutpuStream(@NotNull OutputStream dataOutputStream) {
        this.dataOutputStream = new DataOutputStream(dataOutputStream);
        this.bufferFilledBits = 0;
        this.buffer = 0;
        initMasks();
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

    public int bitsFree() {
        return 32 - bufferFilledBits;
    }

    public void writePkcs7() throws IOException {
        int bitsFree = bitsFree();
        buffer <<= bitsFree();
        dataOutputStream.writeByte(bitsFree);
        dataOutputStream.writeByte(bitsFree);
        dataOutputStream.writeByte(bitsFree);
        dataOutputStream.writeByte(bitsFree);
        pkcs7closed = true;
    }

    public void writeFullFilledBuf() throws IOException {
        dataOutputStream.writeInt(buffer);
        bufferFilledBits = 0;
    }

    public void writeIntBits(int bits, int count) throws IOException {
        checkArgument(pkcs7closed == false, "Unable to append stream, if PKCS footer is closed!");
        checkArgument(bits >= 0, "Unable to encode negative value " + bits);
        checkArgument(count > 0 && count < 32, "The count must be in range [1..31], actual = " + count);

        // Case #1
        // Buffer : 00000000_00000000_00000000_00000000_00000000_00000000_00000000_00000000
        // bits   :                                               aaaaaaa_aaaaaaaa_aaaaaaaa count = 23, free = 32 - 23 = 19
        // Output : aaaaaaaa_aaaaaaaa_aaaaaaa0_00000000_00000000_00000000_00000000_00000000
        //
        if (bitsFree() >= count) {
            buffer <<= count;
            buffer |= (bits & masks[count - 1]);
            bufferFilledBits += count;
            if (bitsFree() == 0) {
                writeFullFilledBuf();
            }
        } /*else {
            buffer |= (bits & masks[bitsFree() - 1]);
            writeFullFilledBuf();
        }*/
    }

    @Override
    public void close() throws IOException {
        dataOutputStream.close();
    }

    @Override
    public void flush() throws IOException {
        if (!pkcs7closed) {
            buffer <<= bitsFree();
            writeFullFilledBuf();
            writePkcs7();
            dataOutputStream.flush();
        }
    }
}
