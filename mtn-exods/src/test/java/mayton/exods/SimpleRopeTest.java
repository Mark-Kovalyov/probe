package mayton.exods;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleRopeTest {

    @Test
    public void testConstructor() {
        Rope rope = new SimpleRope("Hello");
        assertEquals("Hello", rope.build());
    }

    @Test
    public void testAppend() {
        Rope rope = new SimpleRope("Hello");
        rope.append(" ");
        rope.append("world");
        assertEquals("Hello world", rope.build());
    }

}
