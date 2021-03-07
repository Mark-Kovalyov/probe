package mayton.unicode;

import org.junit.Test;

import java.text.Normalizer;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TestNormalization {

    @Test
    public void testNormalization() {
        // (1)
        assertTrue(Normalizer.isNormalized("schön", Normalizer.Form.NFC));
        assertTrue(Normalizer.isNormalized("schön", Normalizer.Form.NFKC));
        assertFalse(Normalizer.isNormalized("schön", Normalizer.Form.NFKD));
        assertFalse(Normalizer.isNormalized("schön", Normalizer.Form.NFD));

        // (2)
        assertFalse(Normalizer.isNormalized("scho\u0308n", Normalizer.Form.NFC));
        assertFalse(Normalizer.isNormalized("scho\u0308n", Normalizer.Form.NFKC));
        assertTrue( Normalizer.isNormalized("scho\u0308n", Normalizer.Form.NFD));
        assertTrue( Normalizer.isNormalized("scho\u0308n", Normalizer.Form.NFKD));

        assertEquals("schön", Normalizer.normalize("scho\u0308n", Normalizer.Form.NFC));

        assertEquals("El nío come camarön", Normalizer.normalize("El ni\u0301o come camaro\u0308n", Normalizer.Form.NFC));

        //text with the acute accent symbol
        assertEquals("touché", Normalizer.normalize("touch\u00e9", Normalizer.Form.NFC));

        //text with ligature
        assertEquals("aﬃance", Normalizer.normalize("a\ufb03ance", Normalizer.Form.NFC));

        //text with the cedilla
        assertEquals("fa\u00e7ade", Normalizer.normalize("fa\u00e7ade", Normalizer.Form.NFC));
        assertEquals("fa\u00e7ade", Normalizer.normalize("fa\u00e7ade", Normalizer.Form.NFKC));

        //text with half-width katakana
        assertEquals("ﾁｮｺﾚｰﾄ", Normalizer.normalize("\uff81\uff6e\uff7a\uff9a\uff70\uff84", Normalizer.Form.NFC));
        assertEquals("チョコレート", Normalizer.normalize("\uff81\uff6e\uff7a\uff9a\uff70\uff84", Normalizer.Form.NFKC));

        //from Microsoft.Net https://docs.microsoft.com/en-us/dotnet/api/system.text.normalizationform?view=netcore-3.1
        assertEquals("ắ", Normalizer.normalize("\u1EAF", Normalizer.Form.NFC));
        assertEquals("ắ", Normalizer.normalize("\u0103\u0301", Normalizer.Form.NFC));
        assertEquals("ắ", Normalizer.normalize("\u0061\u0306\u0301", Normalizer.Form.NFC));



    }

}
