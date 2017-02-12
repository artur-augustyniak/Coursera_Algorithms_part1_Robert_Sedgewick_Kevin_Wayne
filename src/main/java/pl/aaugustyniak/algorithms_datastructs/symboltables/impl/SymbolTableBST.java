package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.ArrayList;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;
import pl.aaugustyniak.algorithms_datastructs.btrees.NodeVisitor;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.BST;
import pl.aaugustyniak.algorithms_datastructs.symboltables.SymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class SymbolTableBST<K extends Comparable<K>, V> implements SymbolTable<K, V> {

    private final BTree<K, V> tree;

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

    public SymbolTableBST() {
        tree = new BST<>();
    }

    public SymbolTableBST(BTree<K, V> tree) {
        this.tree = tree;
    }

    protected BTree<K, V> getTree() {
        return tree;
    }

    @Override
    public void put(K k, V v) {
        tree.insert(k, v);
    }

    @Override
    public V get(K k) {
        return tree.search(k);
    }

    @Override
    public void delete(K k) {
        tree.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return tree.search(k) != null;
    }

    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public Iterable<K> keys() {
        KeysCollector<K, V> collector = new KeysCollector<>();
        tree.inOrder(collector);
        return collector.getCollectedKeys();

    }
}
