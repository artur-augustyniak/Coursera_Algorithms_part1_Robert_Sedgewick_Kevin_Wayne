package pl.aaugustyniak.algorithms_datastructs.btrees;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface BtreeNode<K extends Comparable<K>, V> {

    public K getKey();

    public V getValue();

    public void setValue(V v);

    /**
     * Subtree with keys are smaller tkan this.getKey()
     *
     * @return
     */
    public BtreeNode<K, V> getLeft();

    public void setLeft(BtreeNode<K, V> node);

    /**
     * Subtree with keys are larger tkan this.getKey()
     *
     * @return
     */
    public BtreeNode<K, V> getRight();

    public void setRight(BtreeNode<K, V> node);

    public void accept(NodeVisitor v);

    public int subTreeSize();
    
    public void setSubTreeSize(int size);

}
