package mayton.network;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NetworkUtilsTest {

    @Test
    public void testFormat() {
        assertEquals("0.0.0.0", NetworkUtils.formatIpV4(0));
        assertEquals("255.255.255.255", NetworkUtils.formatIpV4(4294967295L));
    }

    @Test
    public void test() {
        assertEquals(0L, NetworkUtils.parseIpV4("0.0.0.0"));
        assertEquals(16843009L, NetworkUtils.parseIpV4("1.1.1.1"));
        assertEquals(4294967295L, NetworkUtils.parseIpV4("255.255.255.255"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionEmpty() {
        NetworkUtils.parseIpV4("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionToLong() {
        NetworkUtils.parseIpV4("1000.1000.1000.100");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionNull() {
        NetworkUtils.parseIpV4(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExceptionIllegalSymbol() {
        NetworkUtils.parseIpV4("a.b.c.d");
    }

}

