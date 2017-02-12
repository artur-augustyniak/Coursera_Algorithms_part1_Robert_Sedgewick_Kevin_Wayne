package pl.aaugustyniak.algorithms_datastructs.sorting.impl;

import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class ShellSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    @Override
    public T[] sort(T[] arr) {
        long startTime = System.currentTimeMillis();
        int h = 1;
        while (h < arr.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >=h; j-=h) {
                    arrayCost++;
                    if (less(arr[j], arr[j - 1])) {
                        swap(arr, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
        long stopTime = System.currentTimeMillis();
        this.microtime = stopTime - startTime;
        return arr;
    }

}
