package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.Collection;
import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.RedBlackBST;
import pl.aaugustyniak.algorithms_datastructs.symboltables.OrderedSet;
import pl.aaugustyniak.algorithms_datastructs.symboltables.OrderedSymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 */
public class OrderedBSTSet<K extends Comparable<K>> implements OrderedSet<K> {

    BTree<K, K> tree;
    OrderedSymbolTable<K, K> st;

    public OrderedBSTSet() {
        tree = new RedBlackBST<>();
        st = new OrderedSymbolTableBST<>(tree);
    }

    @Override
    public void add(K k) {
        st.put(k, k);
    }

    @Override
    public boolean contains(K k) {
        return st.contains(k);
    }

    @Override
    public int size() {
        return st.size();
    }

    @Override
    public boolean isEmpty() {
        return st.isEmpty();
    }

    @Override
    public void delete(K k) {
        st.delete(k);
    }

    @Override
    public OrderedSet<K> union(OrderedSet<K> that) {
        if (that == null) {
            throw new NullPointerException("called union() with a null argument");
        }
        OrderedSet<K> c = new OrderedBSTSet<>();
        for (K x : this) {
            c.add(x);
        }
        for (K x : that) {
            c.add(x);
        }
        return c;
    }

    @Override
    public boolean setEquals(OrderedSet<K> that) {
        if (that == this) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (that.getClass() != this.getClass()) {
            return false;
        }

        if (this.size() != that.size()) {
            return false;
        }

        for (K k : this) {
            if (!that.contains(k)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public OrderedSet<K> intersects(OrderedSet<K> that) {
        if (that == null) {
            throw new NullPointerException("called intersects() with a null argument");
        }
        OrderedSet<K> c = new OrderedBSTSet<>();
        if (this.size() < that.size()) {
            for (K x : this) {
                if (that.contains(x)) {
                    c.add(x);
                }
            }
        } else {
            for (K x : that) {
                if (this.contains(x)) {
                    c.add(x);
                }
            }
        }
        return c;
    }

    @Override
    public K min() {
        return st.min();
    }

    @Override
    public K max() {
        return st.max();
    }

    @Override
    public K floor(K k) {
        return st.floor(k);
    }

    @Override
    public K ceil(K k) {
        return st.ceil(k);
    }

    @Override
    public int rank(K k) {
        return st.rank(k);
    }

    @Override
    public K select(int r) {
        return st.select(r);
    }

    @Override
    public void deleteMin() {
        st.deleteMin();
    }

    @Override
    public void deleteMax() {
        st.deleteMax();
    }

    @Override
    public int sizeInRange(K from, K to) {
        return st.sizeInRange(from, to);
    }

    @Override
    public Collection<K> keysInRange(K from, K to) {
        return (Collection<K>) st.keysInRange(from, to);
    }

    @Override
    public Collection<K> keys() {
        return (Collection<K>) st.keys();
    }

    @Override
    public void clear() {
        
        tree = null;
        tree = new RedBlackBST<>();
        st = null;
        st = new OrderedSymbolTableBST<>(tree);
    }

    @Override
    public Iterator<K> iterator() {
        return keys().iterator();
    }

}
