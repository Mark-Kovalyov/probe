package mayton.finance;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CusipTest {

    @Test
    public void test() {
        assertFalse(Cusip.isValid(""));
        assertFalse(Cusip.isValid("xxxxxxxx"));
        assertFalse(Cusip.isValid("0A0A0A0A"));
        // Apple
        assertTrue(Cusip.isValid("037833100"));
        // Google
        assertTrue(Cusip.isValid("38259P508"));
        // Wallmart stores
        assertTrue(Cusip.isValid("931142103"));

    }

}
