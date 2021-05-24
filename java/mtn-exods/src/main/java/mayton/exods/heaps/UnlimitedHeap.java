package mayton.exods.heaps;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class UnlimitedHeap<T extends Comparable<T>> implements Heap<T> {

    static Logger logger = LoggerFactory.getLogger(UnlimitedHeap.class);

    private Comparator<T> comparator;

    int getParentIndex(int index) {
        if (index == 0) {
            return -1;
        } else {
            return (index - 1) / 2;
        }
    }

    int getLeftChildIndex(int index) {
        return (index + 1) * 2 - 1;
    }

    int getRightChildIndex(int index) {
        return (index + 1) * 2;
    }

    boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    Comparable leftChild(int index) {
        return objects.get(getLeftChildIndex(index));
    }

    // Physical objects
    private List<T> objects;

    // Logical size of objects
    private int size;

    public UnlimitedHeap(Comparator<T> comparator) {
        this.objects = new ArrayList<>();
        this.comparator = comparator;
    }

    public UnlimitedHeap() {
        this.objects = new ArrayList<>();
        this.comparator = null;
    }

    @Override
    public boolean insert(@NotNull T item) {
        objects.add(item);
        size++;
        internalHeapify(); // TODO: Remove!!!!!!!!!!!!!!!!!!!!!
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @NotNull
    @Override
    public T peekTopItem() {
        if (size == 0) return null;
        return objects.get(0);
    }

    public void compressLast() {
        int currentIndex = size - 1;
    }

    @Nullable
    @Override
    public T pollTopItem() {
        if (size == 0) return null;
        T top = objects.get(0);
        size--;
        compressLast();
        return top;
    }

    @Override
    public void replaceTopItem(@NotNull T item) {

    }

    @NotNull
    @Override
    public Iterator<T> items() {
        // TODO: wrap
        return objects.iterator();
    }

    @Override
    public String toString() {
        return "UnlimitedHeap{" +
                "objects=" + objects +
                ", size=" + size +
                '}';
    }

    @Override
    public void merge(@Nonnull Iterator<T> comparables) {
        // TODO: This is not perfect way to merge 2 heaps.
        comparables.forEachRemaining(x -> objects.add(x));
        size = objects.size();
        internalHeapify();
    }

    void internalHeapifyChain(int i) {
        boolean swapped = false;
        do {

        } while(swapped);
    }

    void internalHeapify() {
        if (size <= 1) return;
        boolean swapped = false;
        int phase = 0;
        do {
            logger.info("Phase {}", phase);
            phase++;
            int current = size - 1;
            while (current >= 0) {
                logger.info(" current = {}", current);
                T currentObject = objects.get(current);
                if (hasParent(current)) {
                    int parentIdx = getParentIndex(current);
                    T parentObject = objects.get(parentIdx);
                    if (currentObject.compareTo(parentObject) > 0) {
                        T temp = parentObject;
                        objects.set(parentIdx, currentObject);
                        objects.set(current, temp);
                        swapped = true;
                        logger.info("  swap({},{})", current, parentIdx);
                    }
                } else {
                    break;
                }
                current--;
            }
        } while(swapped);
    }

}
