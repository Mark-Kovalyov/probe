package mayton.crypto;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PwdProcTest {

    @Test
    void testDecimal() {
        BigInteger b = new BigInteger("0123456789", 10);
        assertEquals(123456789, b.intValue());
    }

    @Test
    void testHex() {
        BigInteger b = new BigInteger("CAFEBABE", 16);
        assertEquals(0xCAFEBABE, b.intValue());
    }

    @Test
    void testBase36() {
        assertEquals(36, new BigInteger("10", 36).intValue());
        assertEquals(36 * 36, new BigInteger("100", 36).intValue());
        assertEquals((36 * 36 * 1) + (36 * 2) + 5, new BigInteger("125", 36).intValue());
    }

    @Test
    void testRevPwd1() {
        // dbeead -> ddddbb
        String hash1 = PwdProcessor.hashPwd("dbeead");
        assertEquals("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", hash1);
        String revPwd1 = PwdProcessor.reversePwd("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", "abcdefghjklmnopqrstvwxyz0123456789", 6);
        assertEquals("ddddbb", revPwd1);
    }


}
