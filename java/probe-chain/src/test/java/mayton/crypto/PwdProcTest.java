package mayton.crypto;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PwdProcTest {

    @Test
    @Order(1)
    void testDecimal() {
        BigInteger b = new BigInteger("0123456789", 10);
        assertEquals(123456789, b.intValue());
    }

    @Test
    @Order(2)
    void testHex() {
        BigInteger b = new BigInteger("CAFEBABE", 16);
        assertEquals(0xCAFEBABE, b.intValue());
    }

    @Test
    @Order(3)
    void testBase36() {
        assertEquals(36, new BigInteger("10", 36).intValue());
        assertEquals(36 * 36, new BigInteger("100", 36).intValue());
        assertEquals((36 * 36 * 1) + (36 * 2) + 5, new BigInteger("125", 36).intValue());
    }

    @Test
    @Order(4)
    void testRevPwd1() {
        // dbeead -> ddddbb
        String hash1 = PwdProcessor.hashPwd("dbeead");
        assertEquals("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", hash1);
        String revPwd1 = PwdProcessor.reversePwd("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", "abcdefghjklmnopqrstvwxyz0123456789", 6);
        assertEquals("ky9aew", revPwd1);
    }

    @Test
    @Order(5)
    void testRevPwd2() {
        // dbeead -> ddddbb
        String hash1 = PwdProcessor.hashPwd("dbeead");
        assertEquals("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", hash1);
        String revPwd1 = PwdProcessor.reversePwd2("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", "abcdefghjklmnopqrstvwxyz0123456789", 6);
    }

    @Test
    @Order(6)
    void testRevPwd3() {
        // dbeead -> ddddbb
        String hash1 = PwdProcessor.hashPwd("dbeead");
        assertEquals("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", hash1);
        String revPwd1 = PwdProcessor.reversePwd3("1347d73da9d496705783af314ce992137503e649a10c3b96026b00c2922440f9", "abcdefghjklmnopqrstvwxyz0123456789", 6);
    }


}
