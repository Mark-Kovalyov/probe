package mayton.compression.languagespec.ru;

import org.junit.Test;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RuSyllableSplitterTest {

    @Test
    public void testEmpty() {
        RuSyllableSplitter ruSyllableSplitter = new RuSyllableSplitter();
        assertEquals(Collections.emptyList(), ruSyllableSplitter.split(""));
    }

    @Test
    public void testSimple() {
        RuSyllableSplitter ruSyllableSplitter = new RuSyllableSplitter();
        List<String> res = ruSyllableSplitter.split("а");
        assertEquals(1, res.size());
        assertEquals("а", res.get(0));
    }

    @Test
    public void testBaraban() {
        RuSyllableSplitter ruSyllableSplitter = new RuSyllableSplitter();
        List<String> res = ruSyllableSplitter.split("барабан");
        assertEquals(3, res.size());
        assertEquals("ба", res.get(0));
        assertEquals("ра", res.get(1));
        assertEquals("бан", res.get(2));
    }

    @Test
    public void testAue() {
        RuSyllableSplitter ruSyllableSplitter = new RuSyllableSplitter();
        List<String> res = ruSyllableSplitter.split("aye");
        assertEquals(1, res.size());
        assertEquals("aye", res.get(0));
    }

    @Test
    public void testMkrtchn() {
        RuSyllableSplitter ruSyllableSplitter = new RuSyllableSplitter();
        List<String> res = ruSyllableSplitter.split("мкртчян");
        assertEquals(1, res.size());
        assertEquals("мкртчян", res.get(0));
    }

}
