package pl.aaugustyniak.algorithms_datastructs.selectionproblem;

import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.QuickSorter;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class HoareQuickSelector<T extends Comparable<T>> extends QuickSorter<T> {

    public T select(T[] from, int nthInSize) {
        this.arr = from;
        shuffle(arr);
        int lo = 0;
        int hi = arr.length - 1;
        int median = medianOf3(lo, lo + (hi - lo) / 2, hi);
        if (median != lo) {
            swap(arr, lo, median);
        }
        while (hi > lo) {

            int j = arrPartition(lo, hi);
            if (j < nthInSize) {
                lo = j + 1;
            } else if (j > nthInSize) {
                hi = j - 1;
            } else {
                return this.arr[nthInSize];
            }
        }

        return this.arr[nthInSize];
    }

    public T median(T[] from) {
        return select(from, medianIdx(from.length));
    }

    private int medianIdx(int arrLen) {
        return arrLen / 2;
    }

}
