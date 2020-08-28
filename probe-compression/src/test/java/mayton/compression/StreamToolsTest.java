package mayton.compression;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static org.junit.Assert.*;

public class StreamToolsTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        ByteArrayInputStream bai = new ByteArrayInputStream("".getBytes("UTF-8"));
        Iterator<Character> iter = StreamTools.inputStreamReaderToIterable(new InputStreamReader(bai, StandardCharsets.UTF_8));
        assertFalse(iter.hasNext());
    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        ByteArrayInputStream bai = new ByteArrayInputStream("*/".getBytes("UTF-8"));
        Iterator<Character> iter = StreamTools.inputStreamReaderToIterable(new InputStreamReader(bai, StandardCharsets.UTF_8));
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertTrue(iter.hasNext());
        assertEquals((Character)'*', iter.next());
        assertTrue(iter.hasNext());
        assertEquals((Character)'/', iter.next());
        assertFalse(iter.hasNext());
        assertFalse(iter.hasNext());
    }

}
