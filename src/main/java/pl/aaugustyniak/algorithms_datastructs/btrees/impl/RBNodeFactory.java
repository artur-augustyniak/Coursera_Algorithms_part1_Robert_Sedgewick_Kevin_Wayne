package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class RBNodeFactory<K extends Comparable<K>, V> implements NodeFactory<K, V> {

    @Override
    public RbNode<K, V> createNode(K key, V value) {
        return new RbNode<>(key, value);
    }
}
