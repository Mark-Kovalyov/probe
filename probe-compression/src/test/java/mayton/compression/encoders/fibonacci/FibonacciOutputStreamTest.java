package mayton.compression.encoders.fibonacci;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FibonacciOutputStreamTest {

    @Test
    public void test1() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FibonacciOutputStream fibonacciOutputStream = new FibonacciOutputStream(bos);
        fibonacciOutputStream.writeLong(1);
        fibonacciOutputStream.writeLong(2);
        fibonacciOutputStream.flush();

        assertEquals(8, bos.size());

        byte[] arr = bos.toByteArray();

        /*assertEquals(64, arr[0]);
        assertEquals(0,  arr[1]);
        assertEquals(0,  arr[2]);
        assertEquals(0,  arr[3]);

        assertEquals(1,  arr[4]);
        assertEquals(1,  arr[5]);
        assertEquals(1,  arr[6]);
        assertEquals(1,  arr[7]);*/
    }

}
