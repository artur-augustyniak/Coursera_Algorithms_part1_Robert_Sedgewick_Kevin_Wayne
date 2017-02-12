package pl.aaugustyniak.algorithms_datastructs.symboltables;

import java.util.Collection;

/**
 *
 * @author aaugustyniak
 * @param <K>
 */
public interface OrderedSet<K extends Comparable<K>> extends Iterable<K> {

    /**
     * Insert key
     *
     * @param k
     */
    public void add(K k);

    /**
     *
     * @param k
     * @return
     */
    public boolean contains(K k);

    /**
     *
     * @return
     */
    public int size();

    /**
     *
     * @return
     */
    public boolean isEmpty();

    /**
     *
     * @param k
     */
    public void delete(K k);

    /**
     *
     * @param that
     * @return
     */
    public OrderedSet<K> union(OrderedSet<K> that);

    /**
     *
     * @param that
     * @return
     */
    public boolean setEquals(OrderedSet<K> that);

    /**
     *
     * @param that
     * @return
     */
    public OrderedSet<K> intersects(OrderedSet<K> that);

    /**
     * Return smallest key
     *
     * @return
     */
    public K min();

    /**
     * Return biggest key
     *
     * @return
     */
    public K max();

    /**
     * Return largest key <= k
     *
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
     *
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
     *
     * @param from
     * @param to
     * @return
     */
    public int sizeInRange(K from, K to);

    /**
     * 1d range keys (border keys can not be in table)
     *
     * @param from
     * @param to
     * @return
     */
    public Collection<K> keysInRange(K from, K to);

    /**
     * Keys
     *
     * @return
     */
    public Collection<K> keys();

    /**
     * empty set
     */
    public void clear();

}
