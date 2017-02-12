package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface NodeFactory<K extends Comparable<K>, V> {
    
    /**
     *
     * @param key
     * @param value
     * @return BtreeNode<K, V>
     */
    public BtreeNode<K, V> createNode(K key, V value);
    
}
