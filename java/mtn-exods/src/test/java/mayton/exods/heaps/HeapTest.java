package mayton.exods.heaps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapTest {

    Random random = new Random();

    static <T> Stream<T> toStream(Iterator<T> iterator) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
    }

    @BeforeEach
    public void beforeEach() {

    }

    @Order(1)
    @Test
    void trivialSizeChecks() {
        Heap heap = new UnlimitedHeap();
        assertTrue(heap.insert(1));
        assertEquals(1, heap.size());
        heap.pollTopItem();
        assertEquals(0, heap.size());
        assertTrue(heap.insert(1));
        assertTrue(heap.insert(2));
        assertEquals(2, heap.size());
    }

    //         0
    //       /  \
    //      1    2
    //     / \  / \
    //    3  4 5   6
    @Order(2)
    @Test
    void testLeftRightIndex() {
        UnlimitedHeap heap = new UnlimitedHeap();

        assertEquals(1, heap.getLeftChildIndex(0));
        assertEquals(2, heap.getRightChildIndex(0));

        assertEquals(3, heap.getLeftChildIndex(1));
        assertEquals(4, heap.getRightChildIndex(1));

        assertEquals(5, heap.getLeftChildIndex(2));
        assertEquals(6, heap.getRightChildIndex(2));
    }

    @Order(3)
    @Test
    void testParentIndex() {
        UnlimitedHeap heap = new UnlimitedHeap();
        assertEquals(1, heap.getParentIndex(3));
        assertEquals(1, heap.getParentIndex(4));

        assertEquals(2, heap.getParentIndex(5));
        assertEquals(2, heap.getParentIndex(6));

        assertEquals(0, heap.getParentIndex(1));
        assertEquals(0, heap.getParentIndex(2));
    }

    @Order(4)
    @Test
    void testPredicates() {
        UnlimitedHeap heap = new UnlimitedHeap();
        heap.insert(11);
        heap.insert(13);

        assertFalse(heap.hasParent(0));
        assertTrue(heap.hasLeftChild(0));
        assertFalse(heap.hasRightChild(0));
    }

    @Order(4)
    @Test
    void random_100_inserts_and_get_max() {
        Heap heap = new UnlimitedHeap();
        Integer[] arr = new Integer[100];
        IntStream.range(0, 100).forEach((i) -> {
            Integer value = random.nextInt();
            arr[i] = value;
            heap.insert(value);
        });
        Integer maxArr = Arrays.stream(arr).max(Integer::compareTo).get();
        assertEquals(maxArr, heap.peekTopItem());
    }

    @Order(3)
    @Test
    void top_10_from100_random_elements() {
        Heap heap = new UnlimitedHeap();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int randomInt = random.nextInt(1000);
            heap.insert(valueOf(randomInt));
            list.add(randomInt);
        }
        // top 10 elements
        Set<Integer> top10 = list.stream().collect(Collectors.toSet());
        for (int i = 0; i < 10; i++) {
            assertTrue(top10.contains(heap.pollTopItem()));
        }
    }

    @Order(4)
    @Test
    void test_heapify() {
        Heap<Integer> heap = new UnlimitedHeap();
        for (int i = 0; i < 10; i++) {
            heap.insert(valueOf(i));
        }
        List<Integer> arr = new ArrayList<>();
        heap.items().forEachRemaining(x -> arr.add(x));
        assertEquals(arr, List.of(9, 8, 5, 6, 7, 1, 4, 0, 3, 2));
        //                    9
        //                 /    \
        //                8      5
        //              /  \    / \
        //             6    7  1   4
        //            / \   /
        //           0   3 2
    }

    class FixComparator implements Comparator<FixMessage> {

        @Override
        public int compare(FixMessage m1, FixMessage m2) {
            return m1.getAmount().compareTo(m2.getAmount());
        }
    }

    @Test @Order(4)
    void test_heapify_rev() {
        Heap<FixMessage> heap = new UnlimitedHeap((new FixComparator()).reversed());
        for (int i = 15; i < 25; i++) {
            heap.insert(new FixMessage(BigDecimal.valueOf(i).divide(BigDecimal.TEN)));
        }
        List<FixMessage> arr = new ArrayList<>();
        heap.items().forEachRemaining(x -> arr.add(x));
        assertEquals(arr.get(0), new FixMessage(new BigDecimal("2.4")));
        assertEquals(arr.get(1), new FixMessage(new BigDecimal("2.3")));
    }

    @Test @Order(5)
    @Disabled
    void test_replace_top() {
        //                    9
        //                 /    \
        //                8      5
        //              /  \    / \
        //             6    7  1   4
        //            / \   /
        //           0   3 2
        Heap<Integer> heap = new UnlimitedHeap<>();
        for(int i = 0; i < 10; i++) heap.insert(i);
        heap.replaceTopItem(10);
        //                    8
        //                 /    \
        //                7      5
        //              /  \    / \
        //             6    2  1   4
        //            / \
        //           0   3
        assertEquals(List.of(1), toStream(heap.items()).collect(Collectors.toList()));
        //
    }


}
