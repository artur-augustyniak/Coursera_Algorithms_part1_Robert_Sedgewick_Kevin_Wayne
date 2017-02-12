package pl.aaugustyniak.algorithms_datastructs.sorting;

/**
 *
 * @author artur
 * @param <T>
 */
public interface Sorter<T extends Comparable<T>> {
    
    //compareTo() callback on objects
    public T[] sort(T[] arr);

}
