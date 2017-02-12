package pl.aaugustyniak.algorithms_datastructs.btrees.impl;

import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;
import pl.aaugustyniak.algorithms_datastructs.btrees.NodeVisitor;

/**
 * Spaly tree
 * @author aaugustyniak
 */
public class SplayBST<Key extends Comparable<Key>, Value> implements BTree<Key, Value> {

    private Node root;   // root of the BST
    private int nodeCount = 0;

    @Override
    public void setNodeFactory(NodeFactory<Key, Value> nodeFactory) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Key maxKey() {
        return getMaxNode(root).getKey();
    }

    @Override
    public Key minKey() {
        return getMinNode(root).getKey();
    }

    protected BtreeNode<Key, Value> getMinNode(BtreeNode<Key, Value> fromNode) {
        if (fromNode.getLeft() == null) {
            return fromNode;
        } else {
            return getMinNode(fromNode.getLeft());
        }
    }

    protected BtreeNode<Key, Value> getMaxNode(BtreeNode<Key, Value> fromNode) {
        if (fromNode.getRight() == null) {
            return fromNode;
        } else {
            return getMaxNode(fromNode.getRight());
        }
    }

    @Override
    public Key floor(Key k) {
        BtreeNode<Key, Value> x = floor(k, root);
        if (x == null) {
            return null;
        }
        return x.getKey();
    }

    private BtreeNode<Key, Value> floor(Key k, BtreeNode<Key, Value> fromNode) {
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
        BtreeNode<Key, Value> t = floor(k, fromNode.getRight());
        if (t != null) {
            return t;
        } else {
            return fromNode;
        }
    }

    @Override
    public Key ceil(Key k) {
        BtreeNode<Key, Value> x = ceil(k, root);
        if (x == null) {
            return null;
        }
        return x.getKey();
    }

    private BtreeNode<Key, Value> ceil(Key k, BtreeNode<Key, Value> fromNode) {
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
        BtreeNode<Key, Value> t = ceil(k, fromNode.getLeft());
        if (t != null) {
            return t;
        } else {
            return fromNode;
        }
    }

    @Override
    public int rank(Key fromKey) {
        return rank(fromKey, root);
    }

    public int rank(Key fromKey, BtreeNode<Key, Value> fromNode) {
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
    public Key select(int rank) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        return nodeCount > 0;
    }

    // BST helper node data type
    private class Node implements BtreeNode<Key, Value> {

        private Key key;            // key
        private Value value;        // associated data
        private Node left, right;   // left and right subtrees

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Key getKey() {
            return key;
        }

        @Override
        public Value getValue() {
            return value;
        }

        @Override
        public void setValue(Value v) {
            value = v;
        }

        @Override
        public BtreeNode<Key, Value> getLeft() {
            return left;
        }

        @Override
        public void setLeft(BtreeNode<Key, Value> node) {
            left = (Node) node;
        }

        @Override
        public BtreeNode<Key, Value> getRight() {
            return right;
        }

        @Override
        public void setRight(BtreeNode<Key, Value> node) {
            right = (Node) node;
        }

        @Override
        public void accept(NodeVisitor v) {
            v.visit(this);
        }

        @Override
        public int subTreeSize() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setSubTreeSize(int size) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    public NodeVisitor<Key, Value> preOrder(NodeVisitor<Key, Value> v) {
        return preOrder(v, root);
    }

    @Override
    public NodeVisitor<Key, Value> preOrder(NodeVisitor<Key, Value> v, Key fromKey) {
        BtreeNode<Key, Value> x = searchNode(fromKey);
        return preOrder(v, x);
    }

    private NodeVisitor<Key, Value> preOrder(NodeVisitor<Key, Value> v, BtreeNode<Key, Value> fromNode) {
        if (fromNode != null) {
            fromNode.accept(v);
            preOrder(v, fromNode.getLeft());
            preOrder(v, fromNode.getRight());
        }
        return v;
    }

    @Override
    public NodeVisitor<Key, Value> inOrder(NodeVisitor<Key, Value> v) {
        return inOrder(v, root);
    }

    @Override
    public NodeVisitor<Key, Value> inOrder(NodeVisitor<Key, Value> v, Key fromKey) {
        BtreeNode<Key, Value> x = searchNode(fromKey);
        return inOrder(v, x);
    }

    private NodeVisitor<Key, Value> inOrder(NodeVisitor<Key, Value> v, BtreeNode<Key, Value> fromNode) {
        if (fromNode != null) {
            inOrder(v, fromNode.getLeft());
            fromNode.accept(v);
            inOrder(v, fromNode.getRight());
        }
        return v;
    }

    @Override
    public NodeVisitor<Key, Value> postOrder(NodeVisitor<Key, Value> v) {
        return postOrder(v, root);
    }

    @Override
    public NodeVisitor<Key, Value> postOrder(NodeVisitor<Key, Value> v, Key fromKey) {
        BtreeNode<Key, Value> x = searchNode(fromKey);
        return postOrder(v, x);
    }

    private NodeVisitor<Key, Value> postOrder(NodeVisitor<Key, Value> v, BtreeNode<Key, Value> fromNode) {
        if (fromNode != null) {
            postOrder(v, fromNode.getLeft());
            postOrder(v, fromNode.getRight());
            fromNode.accept(v);
        }
        return v;
    }

    @Override
    public NodeVisitor<Key, Value> walkBF(NodeVisitor<Key, Value> v) {
        return walkBF(v, root);
    }

    @Override
    public NodeVisitor<Key, Value> walkBF(NodeVisitor<Key, Value> v, Key fromKey) {
        BtreeNode<Key, Value> x = searchNode(fromKey);
        return walkBF(v, x);
    }

    private NodeVisitor<Key, Value> walkBF(NodeVisitor<Key, Value> v, BtreeNode<Key, Value> fromNode) {
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

    public boolean contains(Key key) {
        return (search(key) != null);
    }

    // return value associated with the given key
    // if no such value, return null
    @Override
    public Value search(Key key) {
        root = splay(root, key);
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root.value;
        } else {
            return null;
        }
    }

    public Node searchNode(Key key) {
        root = splay(root, key);
        int cmp = key.compareTo(root.key);
        if (cmp == 0) {
            return root;
        } else {
            return null;
        }
    }

    /*************************************************************************
     *  splay insertion
     *************************************************************************/
    @Override
    public void insert(Key key, Value value) {
        // splay key to root
        if (root == null) {
            root = new Node(key, value);
            nodeCount++;
            return;
        }

        root = splay(root, key);

        int cmp = key.compareTo(root.key);

        // Insert new node at root
        if (cmp < 0) {
            Node n = new Node(key, value);
            n.left = root.left;
            n.right = root;
            root.left = null;
            root = n;
        } // Insert new node at root
        else if (cmp > 0) {
            Node n = new Node(key, value);
            n.right = root.right;
            n.left = root;
            root.right = null;
            root = n;
        } // It was a duplicate key. Simply replace the value
        else if (cmp == 0) {
            root.value = value;
        }

        nodeCount++;

    }

    /*************************************************************************
     *  splay deletion
     *************************************************************************/
    /* This splays the key, then does a slightly modified Hibbard deletion on
     * the root (if it is the node to be deleted; if it is not, the key was 
     * not in the tree). The modification is that rather than swapping the
     * root (call it node A) with its successor, it's successor (call it Node B)
     * is moved to the root position by splaying for the deletion key in A's 
     * right subtree. Finally, A's right child is made the new root's right 
     * child.
     */
    @Override
    public void remove(Key key) {
        if (root == null) {
            nodeCount--;
            return; // empty tree
        }
        root = splay(root, key);

        int cmp = key.compareTo(root.key);

        if (cmp == 0) {
            if (root.left == null) {
                root = root.right;
            } else {
                Node x = root.right;
                root = root.left;
                splay(root, key);
                root.right = x;
            }
        }
        nodeCount--;
        // else: it wasn't in the tree to remove
    }

    /************************************************************************
     * splay function
     * **********************************************************************/
    // splay key in the tree rooted at Node h. If a node with that key exists,
    //   it is splayed to the root of the tree. If it does not, the last node
    //   along the search path for the key is splayed to the root.
    private Node splay(Node h, Key key) {
        if (h == null) {
            return null;
        }

        int cmp1 = key.compareTo(h.key);

        if (cmp1 < 0) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }
            int cmp2 = key.compareTo(h.left.key);
            if (cmp2 < 0) {
                h.left.left = splay(h.left.left, key);
                h = rotateRight(h);
            } else if (cmp2 > 0) {
                h.left.right = splay(h.left.right, key);
                if (h.left.right != null) {
                    h.left = rotateLeft(h.left);
                }
            }

            if (h.left == null) {
                return h;
            } else {
                return rotateRight(h);
            }
        } else if (cmp1 > 0) {
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }

            int cmp2 = key.compareTo(h.right.key);
            if (cmp2 < 0) {
                h.right.left = splay(h.right.left, key);
                if (h.right.left != null) {
                    h.right = rotateRight(h.right);
                }
            } else if (cmp2 > 0) {
                h.right.right = splay(h.right.right, key);
                h = rotateLeft(h);
            }

            if (h.right == null) {
                return h;
            } else {
                return rotateLeft(h);
            }
        } else {
            return h;
        }
    }

    /*************************************************************************
     *  helper functions
     *************************************************************************/
    // height of tree (1-node tree has height 0)
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    public int size() {
        return nodeCount;
        //return size(root);
    }

    private int size(BtreeNode<Key, Value> x) {
        if (x == null) {
            return 0;
        } else {
            return (1 + size(x.getLeft()) + size(x.getRight()));
        }
    }

    // right rotate
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }

    // left rotate
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
}
