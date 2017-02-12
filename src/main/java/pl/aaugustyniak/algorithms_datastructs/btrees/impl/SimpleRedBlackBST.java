package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;

/**
 * Hibbard deletion . tree is not balaced after deletion
 * @author artur
 * @param <K>
 * @param <V>
 */
public class SimpleRedBlackBST<K extends Comparable<K>, V> extends BST<K, V> implements BTree<K, V> {

    protected RBNodeFactory<K, V> nf = new RBNodeFactory<>();

    @Override
    public void insert(K k, V v) {
        if (v == null) {
            remove(k);
            return;
        }
        root = insert((RbNode<K, V>) root, k, v);
        RbNode<K, V> rBroot = (RbNode<K, V>) root;
        rBroot.setBlack();
        assert isBST();
        assert isBalanced();
    }

    private RbNode<K, V> insert(RbNode<K, V> h, K k, V v) {
        if (h == null) {
            RbNode<K, V> newNode = nf.createNode(k, v);
            newNode.setRed();
            return newNode;
        }
        int cmp = k.compareTo(h.getKey());
        if (cmp < 0) {
            h.setLeft(insert((RbNode<K, V>) h.getLeft(), k, v));
        } else if (cmp > 0) {
            h.setRight(insert((RbNode<K, V>) h.getRight(), k, v));
        } else {
            h.setValue(v);
        }
        h = (RbNode<K, V>) balance(h);
        balance(h);
        return h;
    }

    private boolean nullSafeIsRed(RbNode<K, V> n) {
        if (n == null) {
            return false;
        }
        return n.isRed();
    }

    /**
     * restore red-black tree invariant
     *
     * @param h
     * @return
     */
    private BtreeNode<K, V> balance(BtreeNode<K, V> h) {
        assert h != null;

        //assert (h != null);
        /**
         * rch - right child
         */
        RbNode<K, V> rch = (RbNode<K, V>) h.getRight();
        /**
         * lch - left child
         */
        RbNode<K, V> lch = (RbNode<K, V>) h.getLeft();
        /**
         * llgch - left left grand child
         */
        RbNode<K, V> llgch = (h.getLeft() != null) ? (RbNode<K, V>) h.getLeft().getLeft() : null;

        if (nullSafeIsRed(rch) && !nullSafeIsRed(lch)) {
            h = rotateLeft((RbNode<K, V>) h);
        }
        if (nullSafeIsRed(lch) && nullSafeIsRed(llgch)) {
            h = rotateRight((RbNode<K, V>) h);
        }
        if (nullSafeIsRed(lch) && nullSafeIsRed(rch)) {
            flipColors((RbNode<K, V>) h);
        }
        h.setSubTreeSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return h;
    }

    /**
     * maitanin left leaning
     *
     * @param h
     * @return
     */
    private RbNode<K, V> rotateLeft(RbNode<K, V> h) {
        RbNode<K, V> x = (RbNode<K, V>) h.getRight();
        assert nullSafeIsRed(x);
        h.setRight(x.getLeft());
        x.setLeft(h);
        x.setColor(h.getColor());
        h.setRed();
        x.setSubTreeSize(h.subtreeSize);
        h.setSubTreeSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return x;
    }

    /**
     * make left red link (temporarily) lean right
     *
     * @param h
     * @return
     */
    private RbNode<K, V> rotateRight(RbNode<K, V> h) {
        RbNode<K, V> x = (RbNode<K, V>) h.getLeft();
        assert nullSafeIsRed(x);
        h.setLeft(x.getRight());
        x.setRight(h);
        x.setColor(h.getColor());
        h.setRed();
        x.setSubTreeSize(h.subtreeSize);
        h.setSubTreeSize(size(h.getLeft()) + size(h.getRight()) + 1);
        return x;
    }

    /**
     * equiv o tmp 4 node in 2-3 tree
     *
     * @param fromNode
     */
    private void flipColors(RbNode<K, V> h) {
        assert !nullSafeIsRed(h);
        RbNode<K, V> hLeft = (RbNode<K, V>) h.getLeft();
        RbNode<K, V> hRight = (RbNode<K, V>) h.getRight();
        assert nullSafeIsRed(hLeft);
        assert nullSafeIsRed(hRight);
        h.setRed();
        if (hLeft != null) {
            hLeft.setBlack();
        }
        if (hRight != null) {
            hRight.setBlack();
        }
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        RbNode<K, V> x = (RbNode<K, V>) root;
        while (x != null) {
            if (!nullSafeIsRed(x)) {
                black++;
            }
            x = (RbNode<K, V>) x.getLeft();
        }
        return isBalanced((RbNode<K, V>) root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(RbNode<K, V> x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!nullSafeIsRed(x)) {
            black--;
        }
        return isBalanced((RbNode<K, V>) x.getLeft(), black) && isBalanced((RbNode<K, V>) x.getRight(), black);
    }

}
