package mayton.exods.heaps;

import net.jqwik.api.*;

import java.util.Comparator;
import java.util.Random;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

@PropertyDefaults(tries = 300, afterFailure = AfterFailureMode.PREVIOUS_SEED)
public class HeapProperty {

    @Property
    @Report(Reporting.GENERATED)
    boolean everyHeapContainsMaxElementOnTheTop(@ForAll("RandomHeapsOfIntegers") Heap<Integer> heap) {
        int max = StreamSupport.stream(Spliterators.spliteratorUnknownSize(heap.items(), Spliterator.ORDERED), false)
                .max(Integer::compareTo)
                .get();

        return max == heap.peekTopItem();
    }

    @Provide("RandomHeapsOfIntegers")
    Arbitrary<Heap<Integer>> randomHeap() {
        return Arbitraries.create(() -> {
            Heap heap = new UnlimitedHeap((a,b) -> 0);
            Random random = new Random();
            for(int i = 0; i < 10; i++) {
                heap.insert(random.nextInt(1000));
            }
            return heap;
        });
    }

}
