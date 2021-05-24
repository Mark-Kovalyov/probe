package mayton.exods.heaps;

import mayton.exods.heaps.HeapUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("fast")
class HeapUtilsTest {

    @Test
    public void testEmptyHeap() {
        assertTrue(HeapUtils.checkHeap(new Integer[0]));
    }

    @Test
    public void testIllegalHeap() {
        int[] illegalHeap = {44, 55, 12, 42, 94, 18, 6, 67 };
        assertFalse(HeapUtils.checkHeap(Arrays.stream(illegalHeap).boxed().toArray(Integer[]::new)));
    }

    @Test
    public void testLegalHeap() {
        int[] legalHeap = {94, 67, 55, 44, 42, 18, 12, 6 };
        assertTrue(HeapUtils.checkHeap(Arrays.stream(legalHeap).boxed().toArray(Integer[]::new)));
    }

    @Test
    public void testHeapify() {

    }

}
