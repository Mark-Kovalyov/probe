package mayton.exods;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleRopeTest {

    @Test
    public void testConstructor() {
        Rope rope = new SimpleRope("Hello");
        assertEquals("Hello", rope.build());
    }

    @Test
    @Disabled
    public void testAppend() {
        Rope rope = new SimpleRope("Hello");
        rope.append(" ");
        rope.append("world");
        assertEquals("Hello world", rope.build());
    }

}
