package mayton.compression.encoders;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class BitOutpuStreamTest {

    @Test
    public void test() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BitOutpuStream bitOutpuStream = new BitOutpuStream(bos);
        bitOutpuStream.writeBit(1);
        bitOutpuStream.flush();
        byte[] buf = bos.toByteArray();
        assertEquals(1, buf[0]);
    }

}
