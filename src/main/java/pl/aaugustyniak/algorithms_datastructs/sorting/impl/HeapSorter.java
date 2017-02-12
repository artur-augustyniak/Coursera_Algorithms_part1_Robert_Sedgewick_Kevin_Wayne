package pl.aaugustyniak.algorithms_datastructs.sorting.impl;

import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class HeapSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    @Override
    public T[] sort(T[] arr) {
        int N = arr.length;
        for (int k = N / 2; k >= 1; k--) {
            heapInvariantDownFrom(arr, k, N);
        }
        while (N > 1) {
            swap(arr, 1-1, N-1);
            N--;
            heapInvariantDownFrom(arr, 1, N);
        }
        return arr;
    }

    /**
     * a.k.a. Sink
     *
     * @param heapIdx
     */
    private void heapInvariantDownFrom(T[] arr, int heapIdx, int count) {
        /**
         * iterate over left child
         */
        while (2 * heapIdx <= count) {
            int currentChild = 2 * heapIdx;
            if (currentChild < count && less(arr[currentChild - 1], arr[currentChild + 1 - 1])) {
                currentChild++;
            }
            if (!less(arr[heapIdx - 1], arr[currentChild - 1])) {
                break;
            }
            swap(arr, heapIdx-1, currentChild-1);
            heapIdx = currentChild;
        }
    }

}
