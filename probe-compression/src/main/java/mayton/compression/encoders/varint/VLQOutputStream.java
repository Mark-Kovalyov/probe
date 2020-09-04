package mayton.compression.encoders.varint;

import mayton.compression.encoders.CompactDigitOutputStream;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * VLQ (Variable-Length-Quantity) numbers output stream.
 *
 * Only unsigned numbers supported!
 */
public class VLQOutputStream extends CompactDigitOutputStream {

    private DataOutputStream dataOutputStream;

    public VLQOutputStream(OutputStream outputStream) {
        super(outputStream);
        dataOutputStream = new DataOutputStream(outputStream);
    }

    @Override
    public void writeLong(long v) throws IOException {
        checkArgument(v >= 0, "Unable to encode negative value " + v);
        if (v <= 0b1111111) {
            dataOutputStream.writeByte((byte) v);
        } else if (v <= 0b1111111_1111111) {
            dataOutputStream.writeByte((byte) (v >>> 7) | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v & 0b1111111));
        } else if (v <= 0b1111111_1111111_1111111) {
            dataOutputStream.writeByte((byte) (v >>> 14) | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v >>> 7)  | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v & 0b1111111));
        } else if (v <= 0b1111111_1111111_1111111_1111111) {
            dataOutputStream.writeByte((byte) (v >>> 21) | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v >>> 14) | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v >>> 7)  | 0x80);  // not last will have 8th bit set
            dataOutputStream.writeByte((byte) (v & 0b1111111));
        } else  {
            // TODO: Implement other length
            throw new IllegalArgumentException("Unable to encode " + v + " value. The maximum possible for this encoder = " + 0b1111111_1111111_1111111_1111111);
        }
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
