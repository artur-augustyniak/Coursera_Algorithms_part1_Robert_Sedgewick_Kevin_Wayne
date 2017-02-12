package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class ClassicMergeSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    private T[] arr;
    private T[] auxArr;

    @Override
    public T[] sort(T[] arr) {
        this.arr = arr;
        //Powinna byc pusta nowa tablica
        auxArr = Arrays.copyOf(arr, arr.length);
        mergeSort(0, arr.length - 1);
        return arr;
    }

    private void mergeSort(int lo, int hi) {

        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid);
        mergeSort(mid + 1, hi);
        fakeInPlaceMerge(lo, mid, hi);
    }

    public void fakeInPlaceMerge(int lo, int mid, int hi) {
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid + 1, hi);
        for (int i = lo; i <= hi; i++) {
            auxArr[i] = arr[i];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                arr[k] = auxArr[j++];
            } else if (j > hi) {
                arr[k] = auxArr[i++];

            } else if (less(auxArr[j], auxArr[i])) {
                arr[k] = auxArr[j++];
            } else {
                arr[k] = auxArr[i++];
            }
        }
    }

    private boolean isSorted(T[] halvesArr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (less(halvesArr[i], halvesArr[i - 1])) {
                return false;
            }
        }
        return true;
    }
}
