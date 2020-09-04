package mayton.compression.encoders.varint;

import mayton.compression.encoders.CompactDigitInputStream;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/**
 * VLQ (Variable-Length-Quantity) numbers input stream.
 *
 * Only unsigned numbers supported!
 */
public class VLQInputStream extends CompactDigitInputStream {

    private DataInputStream dataInputStream;

    public VLQInputStream(InputStream inputStream) {
        super(inputStream);
        dataInputStream = new DataInputStream(inputStream);
    }

    @Override
    public long readLong() throws IOException {
        long sum = 0;
        boolean isFinished = false;
        try{
            while(true) {
                int res = dataInputStream.readByte();
                isFinished = (0x80 & res) == 0;
                sum <<= 7;
                sum |= (0b1111111 & res);
                if (isFinished) {
                    return sum;
                }
            }
        } catch (EOFException ex) {
            /*if (!isFinished) {
                throw new IllegalStateException("Unable to receive finished 8th bit value! The stream is broken!");
            }*/
            return -1;
        }
    }

    @Override
    public void close() throws IOException {
        dataInputStream.close();
    }
}
