package pl.aaugustyniak.algorithms_datastructs.sorting;

/**
 *
 * @author artur
 * @param <T>
 */
public interface VerboseSorter<T extends Comparable<T>> extends Sorter<T>{
    
    public boolean less(T v, T w);
    
    public void swap(T[] a, Object idx, Object withIdx);
    
    public boolean isSorted(T[] arr);
    
    public long getMicrotime();
    
    public Integer getCost();
}
