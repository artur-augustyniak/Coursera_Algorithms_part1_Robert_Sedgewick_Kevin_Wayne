package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class IterativeBST<K extends Comparable<K>, V> extends BST<K, V> {

    @Override
    public void insert(K k, V v) {
        BtreeNode<K, V> z = new Node<>(k, v);
        if (root == null) {
            root = z;
            nodeCount++;
            return;
        }

        BtreeNode<K, V> parent = null;
        BtreeNode<K, V> x = root;
        while (x != null) {
            parent = x;
            int cmp = k.compareTo(x.getKey());
            if (cmp < 0) {
                x = x.getLeft();
            } else if (cmp > 0) {
                x = x.getRight();
            } else {
                x.setValue(v);
                return;
            }
        }
        int cmp = k.compareTo(parent.getKey());
        if (cmp < 0) {
            parent.setLeft(z);

        } else {
            parent.setRight(z);
        }
        nodeCount++;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    @Override
    public void remove(K k) {
        super.remove(k);
        nodeCount--;
    }

    @Override
    public V search(K k) {
        BtreeNode<K, V> x = root;
        while (x != null) {
            int cmp = k.compareTo(x.getKey());
            if (cmp < 0) {
                x = x.getLeft();
            } else if (cmp > 0) {
                x = x.getRight();
            } else {
                return x.getValue();
            }
        }
        return null;
    }

}
