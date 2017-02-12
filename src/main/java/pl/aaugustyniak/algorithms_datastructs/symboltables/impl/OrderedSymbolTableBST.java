package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.ArrayList;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.symboltables.OrderedSymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class OrderedSymbolTableBST<K extends Comparable<K>, V> extends SymbolTableBST<K, V> implements OrderedSymbolTable<K, V> {

    public OrderedSymbolTableBST() {
        super();
    }

    public OrderedSymbolTableBST(BTree<K, V> tree) {
        super(tree);
    }

    @Override
    public K min() {
        return getTree().minKey();
    }

    @Override
    public K max() {
        return getTree().maxKey();
    }

    @Override
    public K floor(K k) {
        return getTree().floor(k);
    }

    @Override
    public K ceil(K k) {
        return getTree().ceil(k);
    }

    @Override
    public int rank(K k) {
        return getTree().rank(k);
    }

    @Override
    public K select(int k) {
        return getTree().select(k);
    }

    @Override
    public void deleteMin() {
        K minKey = getTree().minKey();
        getTree().remove(minKey);
    }

    @Override
    public void deleteMax() {
        K maxKey = getTree().maxKey();
        getTree().remove(maxKey);
    }

    @Override
    public int sizeInRange(K from, K to) {
        int toRank = getTree().rank(to);
        int fromRank = getTree().rank(from);

        int size = toRank - fromRank;

        if (contains(to)) {
            return size + 1;
        }
        return size;
    }

    @Override
    public Iterable<K> keysInRange(K from, K to) {
        List<K> keysInRange = new ArrayList<>();
        for (K k : keys()) {
            int cmp1 = k.compareTo(from);
            int cmp2 = k.compareTo(to);
            if (cmp1 >= 0 && cmp2 <= 0) {
                keysInRange.add(k);
            }
        }
        return keysInRange;

    }
}
