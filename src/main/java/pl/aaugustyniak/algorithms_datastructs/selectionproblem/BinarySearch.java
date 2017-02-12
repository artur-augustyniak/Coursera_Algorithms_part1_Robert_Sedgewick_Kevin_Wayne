package pl.aaugustyniak.algorithms_datastructs.selectionproblem;

import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.QuickSorter;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class BinarySearch<T extends Comparable<T>> extends QuickSorter<T> {

    public int getIndex(T[] from, T sereched) {

        int lo = 0;
        int hi = from.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (less(sereched, from[mid])) {
                hi = mid - 1;
            } else if (less(from[mid], sereched)) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
