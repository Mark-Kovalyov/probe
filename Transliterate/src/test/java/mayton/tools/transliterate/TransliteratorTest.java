package mayton.tools.transliterate;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransliteratorTest {

    @Test
    public void testCyrillic() {
        assertEquals("00 Viktor Pelevin.mp3",Transliterator.transliterate("00 Виктор Пелевин.mp3"));
    }

}
