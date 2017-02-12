package pl.aaugustyniak.algorithms_datastructs.symboltables;

/**
 *
 * @author aaugustyniak
 * @param <K> immutables
 * @param <V>
 */
public interface SymbolTable<K, V> {

    /**
     * Insert key-value pair 
     * remove k if v == null
     * override old value of v
     * @param k 
     * @param v 
     */
    public void put(K k, V v);

    /**
     * Return null if there is no key
     * @param k
     * @return 
     */
    public V get(K k);

    /**
     * Lazy delete - efective put(k, null) 
     * @param k 
     */
    public void delete(K k);

    /**
     * Return get(k) !=null
     * @param k
     * @return 
     */
    public boolean contains(K k);

    /**
     * 
     * @return 
     */
    public boolean isEmpty();

    /*
     * Nodes count
     */
    public int size();

    /**
     * Return all keys
     * @return 
     */
    public Iterable<K> keys();
}
