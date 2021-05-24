package mayton.exods.heaps;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Iterator;

public interface Heap<T extends Comparable> {

    boolean insert(@Nonnull T item);

    int size();

    @Nonnull T peekTopItem();

    @Nullable T pollTopItem();

    void replaceTopItem(@Nonnull T item);

    @Nonnull Iterator<T> items();

    default void merge(@Nonnull Heap<T> argHeap) {
        merge(argHeap.items());
    }

    default void merge(@Nonnull Iterator<T> comparables) {
        comparables.forEachRemaining(item -> insert(item));
    }

}
