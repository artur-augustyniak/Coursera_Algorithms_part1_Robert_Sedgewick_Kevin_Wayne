package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class BSTNodeFactory<K extends Comparable<K>, V> implements NodeFactory<K, V> {

    @Override
    public BtreeNode<K, V> createNode(K key, V value) {
        return new Node<>(key, value);
    }
    
}
