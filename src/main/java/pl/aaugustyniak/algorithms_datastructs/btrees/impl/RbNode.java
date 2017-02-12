package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class RbNode<K extends Comparable<K>, V> extends Node<K, V> implements BtreeNode<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    protected boolean color;

    public void setRed() {
        this.color = RED;
    }

    public void setBlack() {
        this.color = BLACK;
    }

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public RbNode(K key, V value) {
        super(key, value);
    }

    public boolean isRed() {
        return color == RED;
    }

    public void setKey(K k) {
        this.key = k;
    }

}
