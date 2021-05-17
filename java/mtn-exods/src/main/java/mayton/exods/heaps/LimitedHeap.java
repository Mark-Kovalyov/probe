package mayton.exods.heaps;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class LimitedHeap implements Heap {

    private Comparable[] objects;
    private int cnt;
    private int hwm;


    @Override
    public boolean insert(@Nonnull Comparable item) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Nonnull
    @Override
    public Comparable topItem() {
        return objects[0];
    }

    @Nonnull
    @Override
    public Iterator<Comparable> rankedItems() {
        return null;
    }
}
