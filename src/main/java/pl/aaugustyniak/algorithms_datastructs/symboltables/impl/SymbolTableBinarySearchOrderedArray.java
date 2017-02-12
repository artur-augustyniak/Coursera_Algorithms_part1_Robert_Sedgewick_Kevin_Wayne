package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import pl.aaugustyniak.algorithms_datastructs.symboltables.OrderedSymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class SymbolTableBinarySearchOrderedArray<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {

    private static final int INIT_CAPACITY = 2;
    private K[] keys;
    private V[] vals;
    private int N = 0;

    public SymbolTableBinarySearchOrderedArray() {
        this(INIT_CAPACITY);
    }

    public SymbolTableBinarySearchOrderedArray(int capacity) {
        keys = (K[]) new Comparable[capacity];
        vals = (V[]) new Comparable[capacity];
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        //assert capacity >= N;
        K[] tempk = (K[]) new Comparable[capacity];
        V[] tempv = (V[]) new Comparable[capacity];
        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;
        keys = tempk;
    }

    private boolean check() {
        return isSorted() && rankCheck();
    }

    // are the items in the array in ascending order?
    private boolean isSorted() {
        for (int i = 1; i < size(); i++) {
            if (keys[i].compareTo(keys[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    // check that rank(select(i)) = i
    private boolean rankCheck() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (int i = 0; i < size(); i++) {
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void put(K k, V v) {
        if (v == null) {
            delete(k);
            return;
        }

        int i = rank(k);

        // key is already in table
        if (i < N && keys[i].compareTo(k) == 0) {
            vals[i] = v;
            return;
        }

        // insert new key-value pair
        if (N == keys.length) {
            resize(2 * keys.length);
        }

        for (int j = N; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = k;
        vals[i] = v;
        N++;

        //assert check();
    }

    @Override
    public V get(K k) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(k);
        if (i < N && keys[i].compareTo(k) == 0) {
            return vals[i];
        }
        return null;
    }

    @Override
    public void delete(K k) {
        if (isEmpty()) {
            return;
        }

        // compute rank
        int i = rank(k);

        // key not in table
        if (i == N || keys[i].compareTo(k) != 0) {
            return;
        }

        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }

        N--;
        keys[N] = null;  // to avoid loitering
        vals[N] = null;

        // resize if 1/4 full
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }

       // assert check();
    }

    @Override
    public boolean contains(K k) {
        return get(k) != null;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        return keysInRange(min(), max());
    }

    @Override
    public K min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public K max() {
        if (isEmpty()) {
            return null;
        }
        return keys[N - 1];
    }

    @Override
    public K floor(K k) {
        int i = rank(k);
        if (i < N && k.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

    @Override
    public K ceil(K k) {
        int i = rank(k);
        if (i == N) {
            return null;
        } else {
            return keys[i];
        }
    }

    @Override
    public int rank(K k) {
        int lo = 0, hi = N - 1;
        while (lo <= hi) {
            int m = lo + (hi - lo) / 2;
            int cmp = k.compareTo(keys[m]);
            if (cmp < 0) {
                hi = m - 1;
            } else if (cmp > 0) {
                lo = m + 1;
            } else {
                return m;
            }
        }
        return lo;
    }

    @Override
    public K select(int k) {
        if (k < 0 || k >= N) {
            return null;
        }
        return keys[k];
    }

    @Override
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(min());
    }

    @Override
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(max());
    }

    @Override
    public int sizeInRange(K from, K to) {
        if (from.compareTo(to) > 0) {
            return 0;
        }
        if (contains(to)) {
            return rank(to) - rank(from) + 1;
        } else {
            return rank(to) - rank(from);
        }
    }

    @Override
    public Iterable<K> keysInRange(K from, K to) {
        List<K> tableKeys = new ArrayList<>();
        if (from == null && to == null) {
            return tableKeys;
        }
        if (from == null) {
            throw new NullPointerException("from is null in keys()");
        }
        if (to == null) {
            throw new NullPointerException("to is null in keys()");
        }
        if (from.compareTo(to) > 0) {
            return tableKeys;
        }
        for (int i = rank(from); i < rank(to); i++) {
            tableKeys.add(keys[i]);
        }
        if (contains(to)) {
            tableKeys.add(keys[rank(to)]);
        }
        return tableKeys;
    }

}
