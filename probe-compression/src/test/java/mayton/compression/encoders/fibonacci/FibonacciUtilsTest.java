package mayton.compression.encoders.fibonacci;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FibonacciUtilsTest {

    // Encoder

    @Test
    public void testEncoder() {

        assertEquals("1", FibonacciUtils.encodeF(1));
        assertEquals("01", FibonacciUtils.encodeF(2));
        assertEquals("001", FibonacciUtils.encodeF(3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEncoderNeg() {
        assertEquals("", FibonacciUtils.encodeF(-1));
    }

    @Test
    public void testEncoderMarginal() {
        assertEquals("0010010000001000010100100101000001", FibonacciUtils.encodeF(10_000_000));
        assertEquals("00100001010101000001010000101010010101", FibonacciUtils.encodeF(100_000_000));
        assertEquals("1001010001010001000001010101000010010000101", FibonacciUtils.encodeF(1_000_000_000));
        assertEquals("101001001001001010010001010001001010101000001", FibonacciUtils.encodeF(2_000_000_000));
    }

    // Decoder

    @Test(expected = IllegalArgumentException.class)
    public void testDecoderEmpty() {
        FibonacciUtils.decodeF("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecoderIllegalSymbol() {
        FibonacciUtils.decodeF("10x");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImpossibleCombinations() {
        FibonacciUtils.decodeF("100110001");
    }

    @Test
    public void testMarginal() {
        assertEquals(1L, FibonacciUtils.decodeF("1"));
    }

    @Test
    public void testDecoder() {
        assertEquals(1L, FibonacciUtils.decodeF("1"));
        assertEquals(2L, FibonacciUtils.decodeF("01"));
        assertEquals(3L, FibonacciUtils.decodeF("001"));
        assertEquals(4L, FibonacciUtils.decodeF("101"));
        assertEquals(5L, FibonacciUtils.decodeF("0001"));
        assertEquals(6L, FibonacciUtils.decodeF("1001"));
        assertEquals(7L, FibonacciUtils.decodeF("0101"));
        assertEquals(8L, FibonacciUtils.decodeF("00001"));
        assertEquals(9L, FibonacciUtils.decodeF("10001"));
        assertEquals(10L, FibonacciUtils.decodeF("01001"));
        assertEquals(11L, FibonacciUtils.decodeF("00101"));
        assertEquals(12L, FibonacciUtils.decodeF("10101"));
        assertEquals(13L, FibonacciUtils.decodeF("000001"));
    }

}
