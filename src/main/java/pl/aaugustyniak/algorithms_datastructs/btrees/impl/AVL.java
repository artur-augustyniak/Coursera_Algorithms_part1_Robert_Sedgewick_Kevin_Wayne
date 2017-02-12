package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 *
 * @author artur
 * @param <K>
 * @param <V>
 */
public class AVL<K extends Comparable<K>, V> extends BST<K, V> implements BTree<K, V> {

    public AVL() {
        this.nf = new AVLNodeFactory<>();
    }

    protected BtreeNode<K, V> insert(BtreeNode<K, V> after, K k, V v) {
        BtreeNode<K, V> inserted = super.insert(after, k, v);
        recursiveBalance((AvlNode<K, V>) inserted);
        return inserted;

    }

    private AvlNode<K, V> getLL(BtreeNode<K, V> from) {
        if (from.getLeft() != null) {
            return (AvlNode<K, V>) from.getLeft().getLeft();
        }
        return null;
    }

    private AvlNode<K, V> getRL(BtreeNode<K, V> from) {
        if (from.getRight() != null) {
            return (AvlNode<K, V>) from.getRight().getLeft();
        }
        return null;
    }

    private AvlNode<K, V> getRR(BtreeNode<K, V> from) {
        if (from.getRight() != null) {
            return (AvlNode<K, V>) from.getRight().getRight();
        }
        return null;
    }

    public int height() {
        return height(root);
    }

    private int height(BtreeNode<K, V> x) {
        if (x == null) {
           return -1;
        }
        return 1 + Math.max(height(x.getLeft()), height(x.getRight()));
    }
    //http://edu.i-lo.tarnow.pl/inf/alg/001_search/0119.php
    private void recursiveBalance(AvlNode<K, V> cur) {
        throw new RuntimeException("unimplemented");
//        // we do not use the balance in this class, but the store it anyway
//        setBalance(cur);
//        int balance = cur.balance;
//
//        if (balance == -2) {
//            if (height(getLL(cur)) >= height(getRL(cur))) {
//                cur = rotateRight(cur);
//            } else {
//                cur = doubleRotateLeftRight(cur);
//            }
//        } else if (balance == 2) {
//            if (height(getRR(cur)) >= height(getRL(cur))) {
//                cur = rotateLeft(cur);
//            } else {
//                cur = doubleRotateRightLeft(cur);
//            }
//        }
//
//        // we did not reach the root yet
//        if (cur.parent != null) {
//            recursiveBalance(cur.parent);
//        } else {
//            this.root = cur;
//            System.out.println("------------ Balancing finished ----------------");
//        }
    }

}
