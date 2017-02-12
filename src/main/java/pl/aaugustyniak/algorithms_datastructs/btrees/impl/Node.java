package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;
import pl.aaugustyniak.algorithms_datastructs.btrees.NodeVisitor;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class Node<K extends Comparable<K>, V> implements BtreeNode<K, V> {

    protected K key;
    protected V value;
    protected BtreeNode<K, V> left;
    protected BtreeNode<K, V> right;
    protected int subtreeSize = 1;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public void setValue(V v) {
        value = v;
    }

    @Override
    public BtreeNode<K, V> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BtreeNode<K, V> node) {
        left = node;
    }

    @Override
    public BtreeNode<K, V> getRight() {
        return right;
    }

    @Override
    public void setRight(BtreeNode<K, V> node) {
        right = node;
    }

    @Override
    public void accept(NodeVisitor v) {
        v.visit(this);
    }

    @Override
    public int subTreeSize() {
        return subtreeSize;
    }

    @Override
    public void setSubTreeSize(int size) {
        subtreeSize = size;
    }

}
