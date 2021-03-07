package mayton.tools.transliterate;

import org.junit.Test;

import java.util.Locale;

import static java.lang.Character.UnicodeBlock.BASIC_LATIN;
import static java.lang.Character.UnicodeBlock.CYRILLIC;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnicodeBlockTest {

    @Test
    public void testUnicodeBlockOf() {
        assertEquals(CYRILLIC,Character.UnicodeBlock.of('Я'));
        assertEquals(CYRILLIC,Character.UnicodeBlock.of('ы'));

        assertEquals(BASIC_LATIN,Character.UnicodeBlock.of('s'));
    }

    @Test
    public void testUppercaseDowncase(){

        String s = "Виктор Пелевин";
        assertEquals("ВИКТОР ПЕЛЕВИН", s.toUpperCase());
        assertEquals("виктор пелевин", s.toLowerCase());
    }

}
