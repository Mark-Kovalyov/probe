package mayton.exods.heaps;

import org.jetbrains.annotations.Range;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

public interface Heap {


    boolean insert(@Nonnull Comparable item);

    int size();

    @Nonnull Comparable peekTopItem();

    @Nullable Comparable pollTopItem();

    @Nonnull Iterator<Comparable> rankedItems();

    default @Nonnull Iterator<Comparable> rankedNitems(@Range(from = 0, to = Integer.MAX_VALUE) int n) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(rankedItems(), Spliterator.ORDERED), false).limit(n).iterator();
    }

    default void merge(@Nonnull Heap argHeap) {
        argHeap.rankedItems().forEachRemaining(item -> insert(item));
    }

    default void merge(@Nonnull Iterator<Comparable> comparables) {
        comparables.forEachRemaining(item -> insert(item));
    }

}
