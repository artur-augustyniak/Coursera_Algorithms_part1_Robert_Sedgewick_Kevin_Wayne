package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import java.util.ArrayList;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;
import pl.aaugustyniak.algorithms_datastructs.btrees.NodeVisitor;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class BST<K extends Comparable<K>, V> implements BTree<K, V> {

    private class KeysCollector<K extends Comparable<K>, V> implements NodeVisitor<K, V> {

        private final List<K> collectedKeys = new ArrayList<>();

        public List<K> getCollectedKeys() {
            return collectedKeys;
        }

        @Override
        public void visit(BtreeNode<K, V> node) {
            collectedKeys.add(node.getKey());
        }
    }

    protected BtreeNode<K, V> root;
    protected int nodeCount = 0;
    protected NodeFactory<K, V> nf = new BSTNodeFactory<>();

    @Override
    public void setNodeFactory(NodeFactory<K, V> nodeFactory) {
        nf = nodeFactory;
    }

    protected boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(BtreeNode<K, V> x, K min, K max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.getKey().compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.getKey().compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.getLeft(), min, x.getKey()) && isBST(x.getRight(), x.getKey(), max);
    }

    @Override
    public void insert(K k, V v) {
        if (v == null) {
            remove(k);
            return;
        }
        root = insert(root, k, v);
        assert isBST();
    }

    protected BtreeNode<K, V> insert(BtreeNode<K, V> after, K k, V v) {
        if (after == null) {
            return nf.createNode(k, v);
        }
        int cmp = k.compareTo(after.getKey());
        if (cmp < 0) {
            after.setLeft(insert(after.getLeft(), k, v));
        } else if (cmp > 0) {
            after.setRight(insert(after.getRight(), k, v));
        } else {
            after.setValue(v);
        }
        after.setSubTreeSize(after.subTreeSize() + 1);
        return after;

    }

    @Override
    public int rank(K fromKey) {
        return rank(fromKey, root);
    }

    public int rank(K fromKey, BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return 0;
        }
        int cmp = fromKey.compareTo(fromNode.getKey());
        if (cmp < 0) {
            return rank(fromKey, fromNode.getLeft());
        } else if (cmp > 0) {
            return 1 + size(fromNode.getLeft()) + rank(fromKey, fromNode.getRight());
        } else {
            return size(fromNode.getLeft());
        }
    }

    @Override
    public K select(int rank) {
        KeysCollector<K, V> collector = new KeysCollector<>();
        inOrder(collector);
        K selected = collector.getCollectedKeys().get(rank);
        return selected;
    }

    @Override
    public int size() {
        return size(root);
    }

    protected int size(BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return 0;
        }
        return fromNode.subTreeSize();
    }

    @Override
    public void remove(K k) {
        root = remove(root, k);
        assert isBST();
    }

    /**
     * Hibbard deletion
     *
     * @param fromNode
     * @param withKey
     * @return
     */
    protected BtreeNode<K, V> remove(BtreeNode<K, V> fromNode, K withKey) {
        if (fromNode == null) {
            return null;
        }
        int cmp = withKey.compareTo(fromNode.getKey());
        if (cmp < 0) {
            fromNode.setLeft(remove(fromNode.getLeft(), withKey));
        } else if (cmp > 0) {
            fromNode.setRight(remove(fromNode.getRight(), withKey));
        } else {
            if (fromNode.getRight() == null) {
                return fromNode.getLeft();
            }
            if (fromNode.getLeft() == null) {
                return fromNode.getRight();
            }

            BtreeNode<K, V> tmp = fromNode;
            fromNode = getMinNode(tmp.getRight());
            fromNode.setRight(deleteMinNode(tmp.getRight()));
            fromNode.setLeft(tmp.getLeft());
        }
        fromNode.setSubTreeSize(size(fromNode.getLeft()) + size(fromNode.getRight()) + 1);
        return fromNode;
    }

    protected BtreeNode<K, V> deleteMinNode(BtreeNode<K, V> fromNode) {
        if (fromNode.getLeft() == null) {
            return fromNode.getRight();
        }
        fromNode.setLeft(deleteMinNode(fromNode.getLeft()));
        fromNode.setSubTreeSize(size(fromNode.getLeft()) + size(fromNode.getRight()) + 1);
        return fromNode;
    }

    protected BtreeNode<K, V> getMinNode(BtreeNode<K, V> fromNode) {
        if (fromNode.getLeft() == null) {
            return fromNode;
        } else {
            return getMinNode(fromNode.getLeft());
        }
    }

    protected BtreeNode<K, V> getMaxNode(BtreeNode<K, V> fromNode) {
        if (fromNode.getRight() == null) {
            return fromNode;
        } else {
            return getMaxNode(fromNode.getRight());
        }
    }

    @Override
    public V search(K k) {
        return search(k, root);
    }

    protected V search(K key, BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return null;
        }
        int cmp = key.compareTo(fromNode.getKey());
        if (cmp < 0) {
            return search(key, fromNode.getLeft());
        } else if (cmp > 0) {
            return search(key, fromNode.getRight());
        } else {
            return fromNode.getValue();
        }
    }

    protected BtreeNode<K, V> searchNode(K key, BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return null;
        }
        int cmp = key.compareTo(fromNode.getKey());
        if (cmp < 0) {
            return searchNode(key, fromNode.getLeft());
        } else if (cmp > 0) {
            return searchNode(key, fromNode.getRight());
        } else {
            return fromNode;
        }
    }

    @Override
    public K maxKey() {
        if (isEmpty()) {
            return null;
        }
        return getMaxNode(root).getKey();
    }

    @Override
    public K minKey() {
        if (isEmpty()) {
            return null;
        }
        return getMinNode(root).getKey();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public NodeVisitor<K, V> preOrder(NodeVisitor<K, V> v) {
        return preOrder(v, root);
    }

    @Override
    public NodeVisitor<K, V> preOrder(NodeVisitor<K, V> v, K fromKey) {
        BtreeNode<K, V> x = searchNode(fromKey, root);
        return preOrder(v, x);
    }

    private NodeVisitor<K, V> preOrder(NodeVisitor<K, V> v, BtreeNode<K, V> fromNode) {
        if (fromNode != null) {
            fromNode.accept(v);
            preOrder(v, fromNode.getLeft());
            preOrder(v, fromNode.getRight());
        }
        return v;
    }

    @Override
    public NodeVisitor<K, V> inOrder(NodeVisitor<K, V> v) {
        return inOrder(v, root);
    }

    @Override
    public NodeVisitor<K, V> inOrder(NodeVisitor<K, V> v, K fromKey) {
        BtreeNode<K, V> x = searchNode(fromKey, root);
        return inOrder(v, x);
    }

    private NodeVisitor<K, V> inOrder(NodeVisitor<K, V> v, BtreeNode<K, V> fromNode) {
        if (fromNode != null) {
            inOrder(v, fromNode.getLeft());
            fromNode.accept(v);
            inOrder(v, fromNode.getRight());
        }
        return v;
    }

    @Override
    public NodeVisitor<K, V> postOrder(NodeVisitor<K, V> v) {
        return postOrder(v, root);
    }

    @Override
    public NodeVisitor<K, V> postOrder(NodeVisitor<K, V> v, K fromKey) {
        BtreeNode<K, V> x = searchNode(fromKey, root);
        return postOrder(v, x);
    }

    private NodeVisitor<K, V> postOrder(NodeVisitor<K, V> v, BtreeNode<K, V> fromNode) {
        if (fromNode != null) {
            postOrder(v, fromNode.getLeft());
            postOrder(v, fromNode.getRight());
            fromNode.accept(v);
        }
        return v;
    }

    @Override
    public NodeVisitor<K, V> walkBF(NodeVisitor<K, V> v) {
        return walkBF(v, root);
    }

    @Override
    public NodeVisitor<K, V> walkBF(NodeVisitor<K, V> v, K fromKey) {
        BtreeNode<K, V> x = searchNode(fromKey, root);
        return walkBF(v, x);
    }

    private NodeVisitor<K, V> walkBF(NodeVisitor<K, V> v, BtreeNode<K, V> fromNode) {
        if (fromNode != null) {
            fromNode.accept(v);
            if (fromNode.getLeft() != null) {
                fromNode.getLeft().accept(v);
            }
            if (fromNode.getRight() != null) {
                fromNode.getRight().accept(v);
            }
            walkBF(v, fromNode.getLeft());
            walkBF(v, fromNode.getRight());

        }
        return v;
    }

    @Override
    public K floor(K k) {
        BtreeNode<K, V> x = floor(k, root);
        if (x == null) {
            return null;
        }
        return x.getKey();
    }

    private BtreeNode<K, V> floor(K k, BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return null;
        }
        int cmp = k.compareTo(fromNode.getKey());
        if (cmp == 0) {
            return fromNode;
        }
        if (cmp < 0) {
            return floor(k, fromNode.getLeft());
        }
        BtreeNode<K, V> t = floor(k, fromNode.getRight());
        if (t != null) {
            return t;
        } else {
            return fromNode;
        }
    }

    @Override
    public K ceil(K k) {
        BtreeNode<K, V> x = ceil(k, root);
        if (x == null) {
            return null;
        }
        return x.getKey();
    }

    private BtreeNode<K, V> ceil(K k, BtreeNode<K, V> fromNode) {
        if (fromNode == null) {
            return null;
        }
        int cmp = k.compareTo(fromNode.getKey());
        if (cmp == 0) {
            return fromNode;
        }
        if (cmp > 0) {
            return ceil(k, fromNode.getRight());
        }
        BtreeNode<K, V> t = ceil(k, fromNode.getLeft());
        if (t != null) {
            return t;
        } else {
            return fromNode;
        }
    }
}
