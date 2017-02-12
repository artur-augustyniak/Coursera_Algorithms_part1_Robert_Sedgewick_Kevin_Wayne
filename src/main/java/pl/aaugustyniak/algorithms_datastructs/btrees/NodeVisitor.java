package pl.aaugustyniak.algorithms_datastructs.btrees;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public interface NodeVisitor<K extends Comparable<K>, V> {

    /**
     *
     * @param node
     */
    public void visit(BtreeNode<K, V> node);    
}
