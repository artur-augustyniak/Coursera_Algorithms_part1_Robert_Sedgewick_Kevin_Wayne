package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class AvlNode<K extends Comparable<K>, V> extends Node<K, V> {

    public int balance;

    public AvlNode(K key, V value) {
        super(key, value);
        balance = 0;
    }

}
