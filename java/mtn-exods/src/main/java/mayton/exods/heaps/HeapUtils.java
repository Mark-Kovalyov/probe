package mayton.exods.heaps;

import java.util.List;

public class HeapUtils {


    ////////////////////////////////////////////////

    static void sift(int l, int r, Comparable[] heapCandidate) {
        int i = l;
        int j = 2 * l;

    }

    // Correct heap: {94, 67, 55, 44, 42, 18, 12, 6 };
    //
    //        94               0
    //      /   \            /   \
    //     67   55          1     2
    //    / \   / \        / \   / \
    //   44 42 18 12      3   4 5   6
    //  /                /
    // 6                7
    public static boolean checkHeap(Comparable[] orig) {
        return lookupIllegalElementPosition(orig) == -1;
    }

    public static int lookupIllegalElementPosition(Comparable[] orig) {
        // check formula
        int n = 0;
        int step = 1;
        while(n < orig.length) {
            int offset = n * 2;
            if (offset < orig.length && orig[n].compareTo(orig[offset]) < 0) {
                return offset;
            }
            if (offset + 1 < orig.length && orig[n].compareTo(orig[offset + 1]) < 0){
                return offset + 1;
            }
            n += step;
            step *= 2;
        }
        return -1;
    }



}
