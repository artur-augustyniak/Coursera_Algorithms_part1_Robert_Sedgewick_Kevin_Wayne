package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author aaugustyniak
 */
public class AVLNodeFactory<K extends Comparable<K>, V> implements NodeFactory<K, V> {

    @Override
    public BtreeNode<K, V> createNode(K key, V value) {
        return new AvlNode<>(key, value);
    }
}
