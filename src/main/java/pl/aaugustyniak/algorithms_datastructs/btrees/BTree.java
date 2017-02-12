package pl.aaugustyniak.algorithms_datastructs.btrees;

import pl.aaugustyniak.algorithms_datastructs.btrees.impl.NodeFactory;

/**
 * BST basic interface
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface BTree<K extends Comparable<K>, V> {

    public void setNodeFactory(NodeFactory<K, V> nodeFactory);
    
    public void insert(K k, V v);

    public void remove(K k);

    public V search(K k);

    public K maxKey();

    public K minKey();

    public K floor(K k);

    public K ceil(K k);

    public int size();

    public int rank(K fromKey);

    public K select(int rank);

    public boolean isEmpty();

    /**
     * przejście wzdłużne
     *
     * @param v
     * @return
     */
    public NodeVisitor<K, V> preOrder(NodeVisitor<K, V> v);

    public NodeVisitor<K, V> preOrder(NodeVisitor<K, V> v, K fromKey);

    /**
     * przejście poprzeczne
     *
     * @param v
     * @return
     */
    public NodeVisitor<K, V> inOrder(NodeVisitor<K, V> v);

    public NodeVisitor<K, V> inOrder(NodeVisitor<K, V> v, K fromKey);

    /**
     * wsteczne
     *
     * @param v
     * @return
     */
    public NodeVisitor<K, V> postOrder(NodeVisitor<K, V> v);

    public NodeVisitor<K, V> postOrder(NodeVisitor<K, V> v, K fromKey);

    /**
     * Breadth first recursive walk
     *
     * @param v
     * @return
     */
    public NodeVisitor<K, V> walkBF(NodeVisitor<K, V> v);

    public NodeVisitor<K, V> walkBF(NodeVisitor<K, V> v, K fromKey);

}
