package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class DijkstraEntropyQuickSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    private T[] arr;

    @Override
    public T[] sort(T[] arr) {
        this.arr = arr;
        shuffle(arr);
        quickSort3Way(0, arr.length - 1);
        return arr;
    }

    private void quickSort3Way(int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int lt = lo;
        int gt = hi;
        int i = lo;
        T v = arr[lo];
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) {
                swap(arr, lt++, i++);
            } else if (cmp > 0) {
                swap(arr, i, gt--);
            } else {
                i++;
            }

        }
        quickSort3Way(lo, lt - 1);
        quickSort3Way(gt + 1, hi);
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

    private boolean isSorted(T[] halvesArr, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (less(halvesArr[i], halvesArr[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private int medianOf3(int i, int j, int k) {
        return (less(arr[i], arr[j])
                ? (less(arr[j], arr[k]) ? j : less(arr[i], arr[k]) ? k : i)
                : (less(arr[k], arr[j]) ? j : less(arr[k], arr[i]) ? k : i));
    }
}
