package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class BottomUPMergeSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    private T[] arr;
    private T[] auxArr;

    @Override
    public T[] sort(T[] arr) {
        this.arr = arr;
        //Powinna byc pusta nowa tablica
        auxArr = Arrays.copyOf(arr, arr.length);
        for (int sz = 1; sz < arr.length; sz += sz) {
            for (int lo = 0; lo < arr.length - sz; lo += sz + sz) {
                fakeInPlaceMerge(lo, lo + sz - 1, Math.min(lo + sz + sz - 1, arr.length - 1));
            }
        }
        return arr;
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
