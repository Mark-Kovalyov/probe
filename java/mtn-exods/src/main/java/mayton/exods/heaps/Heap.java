package mayton.exods.heaps;

import javax.annotation.Nonnull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public interface Heap {

    boolean insert(@Nonnull Comparable item);

    int size();

    @Nonnull Comparable topItem();

    @Nonnull Iterator<Comparable> rankedItems();

    default @Nonnull Iterator<Comparable> rankedNitems(int n) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rankedItems(), Spliterator.ORDERED), false).limit(n).iterator();
    }

    default void merge(@Nonnull Heap argHeap) {
        argHeap.rankedItems().forEachRemaining(item -> insert(item));
    }

    default void merge(@Nonnull Iterator<Comparable> comparables) {
        comparables.forEachRemaining(item -> insert(item));
    }

}
