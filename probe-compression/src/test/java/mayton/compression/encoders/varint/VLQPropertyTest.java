package mayton.compression.encoders.varint;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class VLQPropertyTest {

    @Test
    public void test() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        VLQOutputStream vlqOutputStream = new VLQOutputStream(bos);
        Random r = new Random();
        List<Long> testData = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            // 268_435_455
            long testValue = (long) r.nextInt(268_435_455);
            testData.add(testValue);
            vlqOutputStream.writeLong(testValue);
        }
        vlqOutputStream.flush();

        byte[] buf = bos.toByteArray();

        System.out.println("The size is " + buf.length);

        VLQInputStream vlqInputStream = new VLQInputStream(new ByteArrayInputStream(buf));
        for(long testValue : testData) {
            assertEquals(testValue, vlqInputStream.readLong());
        }
        vlqInputStream.close();
    }

}
