package mayton.exods.heaps;

public class HeapUtils {

    static void sift(int l, int r, Comparable[] heapCandidate) {
        int i = l;
        int j = 2 * l;

    }

    // Deikstra's algorithm
    public static Comparable[] heapify(Comparable[] orig) {

        return orig;
    }

    // Correct heap: {94, 67, 55, 44, 42, 18, 12, 6 };
    //
    //        94
    //      /   \
    //     67   55
    //    / \   / \
    //   44 42 18 12
    //  /
    // 6
    public static boolean checkHeap(Comparable[] orig) {
        return lookupIllegalElementPosition(orig) == -1;
    }


    public static int lookupIllegalElementPosition(Comparable[] orig) {
        // check formula
        int n = 0;
        int off = 1;
        while(n < orig.length) {
            if (n * 2 < orig.length && orig[n].compareTo(orig[n * 2]) < 0) {
                return n * 2;
            }
            if (n * 2 + 1 < orig.length && orig[n].compareTo(orig[n * 2 + 1]) < 0){
                return n * 2 + 1;
            }
            n += off;
            off *= 2;
        }
        return -1;
    }

}
