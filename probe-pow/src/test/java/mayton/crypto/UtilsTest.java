package mayton.crypto;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testSubArray() {
        byte[] b = new byte[2];
        assertEquals(1, Utils.subArray(b, 0, 1).length);
    }

    @Test
    public void testBase64() {
        byte[] b = new byte[3];
        b[0] = (byte)0xFF;
        b[1] = (byte)0xFF;
        b[2] = (byte)0xFF;
        assertEquals("////", Utils.encodeBase64(b));
        b[0] = 0;
        b[1] = 0;
        b[2] = 0;
        assertEquals("AAAA", Utils.encodeBase64(b));
    }

}
