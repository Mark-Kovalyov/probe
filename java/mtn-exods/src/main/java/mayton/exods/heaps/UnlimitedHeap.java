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
        return 2 * index + 1;
    }

    int getRightChildIndex(int index) {
        return 2 * index + 2;
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
    public int size() {
        return size;
    }

    @NotNull
    @Override
    public T peekTopItem() {
        if (size == 0) return null;
        return objects.get(0);
    }

    @Nullable
    @Override
    public T pollTopItem() {
        if (size == 0) return null;
        T top = objects.get(0);
        size--;
        return top;
    }

    @Override
    public void replaceTopItem(@NotNull T item) {
        // TODO: Improove perfomance
        pollTopItem();
        insert(item);
    }

    @NotNull
    @Override
    public Iterator<T> items() {
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
        heapifyAll();
    }

    @Override
    public boolean insert(@NotNull T item) {
        objects.add(item);
        size++;
        int i = size - 1;
        int parent = (i - 1) / 2;
        while (i > 0 && objects.get(parent).compareTo(objects.get(i)) < 0) {
            T temp = objects.get(i);
            objects.set(i, objects.get(parent));
            objects.set(parent, temp);
            i = parent;
            parent = (i - 1) / 2;
        }
        return true;
    }

    //                    9
    //                 /    \
    //                8      5
    //              /  \    / \
    //             6    7  1   4
    //            / \   /
    //           0   3 2

    //
    void heapifyAll() {
        for (int i = size / 2; i >= 0; i--) {
            heapify(i);
        }
    }

    void heapify(int i) {
        int leftChild;
        int rightChild;
        int largestChild;

        while(true) {
            leftChild  = getLeftChildIndex(i);
            rightChild = getRightChildIndex(i);
            largestChild = i;

            if (leftChild < size && objects.get(leftChild).compareTo(objects.get(largestChild)) > 0) {
                largestChild = leftChild;
            }

            if (rightChild < size && objects.get(rightChild).compareTo(objects.get(largestChild)) > 0) {
                largestChild = rightChild;
            }

            if (largestChild == i) {
                break;
            }

            // Swap i and largestChild
            T temp = objects.get(i);
            objects.set(i, objects.get(largestChild));
            objects.set(largestChild, temp);
            i = largestChild;
        }
    }

}
