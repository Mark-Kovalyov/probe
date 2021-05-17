package mayton.exods;

import mayton.exods.rope.Rope;
import mayton.exods.rope.SimpleRope;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleRopeTest {

    @Test
    void testConstructor() {
        Rope rope = new SimpleRope("Hello");
        assertEquals("Hello", rope.build());
    }

    @Test
    @Disabled
    void testAppend() {
        Rope rope = new SimpleRope("Hello");
        rope.append(" ");
        rope.append("world");
        assertEquals("Hello world", rope.build());
    }

}
