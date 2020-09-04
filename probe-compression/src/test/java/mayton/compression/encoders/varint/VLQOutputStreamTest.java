package mayton.compression.encoders.varint;

import org.apache.commons.io.output.NullOutputStream;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VLQOutputStreamTest {

    @Test(expected = IllegalArgumentException.class)
    public void negativeValue() throws IOException {
        VLQOutputStream vlqOutputStream = new VLQOutputStream(new NullOutputStream());
        vlqOutputStream.writeLong(-1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void hugeValue() throws IOException {
        VLQOutputStream vlqOutputStream = new VLQOutputStream(new NullOutputStream());
        vlqOutputStream.writeLong(Long.MAX_VALUE);
    }

    @Test
    public void trivialCasesWithNumberLessThan127() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        VLQOutputStream vlqOutputStream = new VLQOutputStream(bos);
        vlqOutputStream.writeLong(0L);
        vlqOutputStream.writeLong(1L);
        vlqOutputStream.writeLong(2L);
        vlqOutputStream.writeLong(4L);
        vlqOutputStream.writeLong(8L);
        vlqOutputStream.writeLong(16L);
        vlqOutputStream.writeLong(32L);
        vlqOutputStream.writeLong(64L);
        vlqOutputStream.writeLong(127L);
        vlqOutputStream.flush();
        assertEquals(9, bos.size());
        byte[] arr = bos.toByteArray();
        assertEquals(0, arr[0]);
        assertEquals(1, arr[1]);
        assertEquals(2, arr[2]);
        assertEquals(4, arr[3]);
        assertEquals(8, arr[4]);
        assertEquals(16, arr[5]);
        assertEquals(32, arr[6]);
        assertEquals(64, arr[7]);
        assertEquals(127, arr[8]);
    }

    @Test
    public void test_range_from_128_to_16383() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        VLQOutputStream vlqOutputStream = new VLQOutputStream(bos);
        Random r = new Random();
        vlqOutputStream.writeLong(128);   // +2 bytes
        vlqOutputStream.writeLong(16383); // +2 bytes
        vlqOutputStream.flush();
        assertEquals(4, bos.size());
        byte[] arr = bos.toByteArray();
        assertTrue((arr[0] & 0x80) != 0); // First byte must contains 8th bit set
        assertTrue((arr[1] & 0x80) == 0); // Last byte must contains 8th bit unset
        assertTrue((arr[2] & 0x80) != 0);
        assertTrue((arr[3] & 0x80) == 0);
    }

    @Test
    public void test_range_from_16384_to_2097151() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        VLQOutputStream vlqOutputStream = new VLQOutputStream(bos);
        Random r = new Random();
        vlqOutputStream.writeLong(16384);    // +3 bytes
        vlqOutputStream.writeLong(2097151);  // +3 bytes
        vlqOutputStream.flush();
        assertEquals(6, bos.size());
        byte[] arr = bos.toByteArray();

        assertTrue((arr[0] & 0x80) != 0);
        assertTrue((arr[1] & 0x80) != 0);
        assertTrue((arr[2] & 0x80) == 0); // Last byte must contains 8th bit unset

        assertTrue((arr[3] & 0x80) != 0);
        assertTrue((arr[4] & 0x80) != 0);
        assertTrue((arr[5] & 0x80) == 0); // Last byte must contains 8th bit unset
    }

}
