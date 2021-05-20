package mayton.exods.heaps;

import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Iterator;

public class LimitedHeap implements Heap {

    ////////////////////// Helpers ////////////////////////

    int getParentIndex(int index) {
        return 0;
    }

    int getLeftChildIndex(int index) {
        return 0;
    }

    int getRightChildIndex(int index) {
        return 0;
    }

    boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    boolean hasParent(int index) {
        return getLeftChildIndex(index) >= 0;
    }

    /// ....

    Comparable leftChild(int index) {
        return objects[getLeftChildIndex(index)];
    }

    ////////////////////////////////////////////////

    private int size;
    private Comparable[] objects;
    private int cnt;

    public LimitedHeap(int size) {
        this.objects = new Comparable[size];
        this.size = size;
        this.cnt = 0;
    }


    @Override
    public boolean insert(@Nonnull Comparable item) {
        if (cnt < objects.length) {
            cnt++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return cnt;
    }

    @Nonnull
    @Override
    public Comparable peekTopItem() {
        return objects[0];
    }

    @Nullable
    @Override
    public Comparable pollTopItem() {
        cnt--;
        return null;
    }

    @Nonnull
    @Override
    public Iterator<Comparable> rankedItems() {
        return null;
    }
}
