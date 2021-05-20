package mayton.exods.heaps;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UnlimitedHeap implements Heap {

    private List<Comparable> objects;

    public UnlimitedHeap() {
        this.objects = new ArrayList<>();
    }

    @Override
    public boolean insert(@NotNull Comparable item) {
        objects.add(item);
        HeapUtils.heapify(objects);
        return true;
    }

    @Override
    public int size() {
        return 0;
    }

    @NotNull
    @Override
    public Comparable peekTopItem() {
        return null;
    }

    @Nullable
    @Override
    public Comparable pollTopItem() {
        return null;
    }

    @NotNull
    @Override
    public Iterator<Comparable> rankedItems() {
        return null;
    }
}
