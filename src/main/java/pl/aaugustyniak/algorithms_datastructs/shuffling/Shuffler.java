package pl.aaugustyniak.algorithms_datastructs.shuffling;

import java.util.Random;
import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.MergeSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class Shuffler<T extends Comparable<T>> extends MergeSorter<T> {

    public T[] shuffler(T[] arr) {
        Random r = new Random();
        for (int i = 1; i < arr.length; i++) {
            int rIdx = r.nextInt(i);
            this.swap(arr, i, rIdx);
        }
        
        return arr;
    }

}
