package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class MergeSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    private static final int INSERTION_CUTOFF = 10;
    private T[] arr;
    private T[] auxArr;

    @Override
    public T[] sort(T[] arr) {
        this.arr = arr;
        auxArr = Arrays.copyOf(arr, arr.length);
        mergeSort(0, arr.length - 1, 0);
        return arr;
    }

    private void insertionSortFor(int start, int stop, T[] a) {
        for (int i = start; i <= stop; i++) {
            for (int j = i; j > start; j--) {
                if (less(a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    private void mergeSort(int lo, int hi, int swapper) {
        T[] dstArr;
        T[] srcArr;
        if (swapper % 2 == 0) {
            dstArr = this.arr;
            srcArr = this.auxArr;
        } else {
            dstArr = this.auxArr;
            srcArr = this.arr;
        }
        if (hi <= lo + INSERTION_CUTOFF) {
            insertionSortFor(lo, hi, dstArr);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        mergeSort(lo, mid, swapper + 1);

        mergeSort(mid + 1, hi, swapper + 1);

        if (!less(srcArr[mid + 1], srcArr[mid])) {
            for (int i = lo; i <= hi; i++) {
                dstArr[i] = srcArr[i];
            }
            return;
        }
        fakeInPlaceMerge(lo, mid, hi, swapper);

    }

    public void fakeInPlaceMerge(int lo, int mid, int hi, int swapper) {

        T[] dstArr;
        T[] srcArr;
        if (swapper % 2 == 0) {
            dstArr = this.arr;
            srcArr = this.auxArr;
        } else {
            dstArr = this.auxArr;
            srcArr = this.arr;
        }
        assert isSorted(srcArr, lo, mid);
        assert isSorted(srcArr, mid + 1, hi);
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                dstArr[k] = srcArr[j++];
            } else if (j > hi) {
                dstArr[k] = srcArr[i++];

            } else if (less(srcArr[j], srcArr[i])) {
                dstArr[k] = srcArr[j++];
            } else {
                dstArr[k] = srcArr[i++];
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
