package mayton.compression.languagespec.ru;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RuUtilsTest {

    @Test
    public void testStrings() {
        assertTrue(RuUtils.isCyrillicOrHyphensInTheMiddle("Из-за"));
        assertFalse(RuUtils.isCyrillicOrHyphensInTheMiddle("Hello бобер"));
        assertFalse(RuUtils.isCyrillicOrHyphensInTheMiddle("[(сноска"));
    }

    @Test
    public void testChars() {
        assertTrue(RuUtils.isCyrillic('И'));
        assertTrue(RuUtils.isCyrillic('и'));
        assertFalse(RuUtils.isCyrillic('s'));
        assertFalse(RuUtils.isCyrillic('W'));
    }

}
