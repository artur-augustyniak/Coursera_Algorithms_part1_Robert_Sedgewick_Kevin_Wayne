package pl.aaugustyniak.algorithms_datastructs.btrees.intervalsearch;

/**
 * No two intervals have the same left endpoint
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface IntervalSt<K extends Comparable<K>, V> {

    void put(K lo, K hi, V v);

    V get(K lo, K hi);

    void delete(K lo, K hi);

    Iterable<V> intersects(K lo, K hi);

}
