package pl.aaugustyniak.algorithms_datastructs.symboltables;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {

    /**
     * Return smallest key
     * @return 
     */
    public K min();

    /**
     * Return biggest key
     * @return 
     */
    public K max();

    /**
     * Return largest key <= k
     * @param k
     * @return
     */
    public K floor(K k);

    /**
     * Return smallest key >= k
     *
     * @param k
     * @return
     */
    public K ceil(K k);

    /**
     * Return number of keys less then k
     * @param k
     * @return
     */
    public int rank(K k);

    /**
     * Return key of rank r
     *
     * @param k
     * @return
     */
    public K select(int r);

    /**
     * Delete node with smallest key
     */
    public void deleteMin();

    /**
     * Delete node with biggest key
     */
    public void deleteMax();

    /**
     * 
     * 1d range search count (border keys can not be in table)
     * @param from
     * @param to
     * @return 
     */
    public int sizeInRange(K from, K to);

    /**
     * 1d range keys  (border keys can not be in table)
     * @param from
     * @param to
     * @return 
     */
    public Iterable<K> keysInRange(K from, K to);
}
