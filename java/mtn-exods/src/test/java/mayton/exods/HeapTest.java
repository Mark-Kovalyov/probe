package mayton.exods;


import mayton.exods.heaps.Heap;
import mayton.exods.heaps.LimitedHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapTest {

    Random random = new Random();

    @BeforeEach
    public void beforeEach() {

    }

    @Order(1)
    @Test
    void trivialSizeChecks() {
        Heap heap = new LimitedHeap(2);
        assertTrue(heap.insert(1));
        assertEquals(1, heap.size());
        heap.pollTopItem();
        assertEquals(0, heap.size());
        assertTrue(heap.insert(1));
        assertTrue(heap.insert(2));
        assertEquals(2, heap.size());
        assertFalse(heap.insert(3));
    }

    @Order(2)
    @Disabled
    @Test
    void random_100_inserts_and_get_max() {
        Heap heap = new LimitedHeap(100);
        Integer[] arr = new Integer[100];
        IntStream.range(0, 100).forEach((i) -> {
            Integer value = random.nextInt();
            arr[i] = value;
            heap.insert(value);
        });
        //sort(arr);
        //sort(arr, Comparable::compareTo);
        sort(arr, Comparator.reverseOrder());
        assertTrue(arr[0].compareTo((Integer) heap.peekTopItem()) == 0);
    }


    @Order(3)
    @Disabled
    @Test
    void top_10_from100_random_elements() {
        Heap heap = new LimitedHeap(10);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomInt = random.nextInt(1000);
            heap.insert(Integer.valueOf(randomInt));
            list.add(randomInt);
        }
        // top 10 elements
        Set<Integer> top10 = list.stream().sorted().collect(Collectors.toSet());
        for (int i = 0; i < 10; i++) {
            assertTrue(top10.contains(heap.pollTopItem()));
        }
    }


}
