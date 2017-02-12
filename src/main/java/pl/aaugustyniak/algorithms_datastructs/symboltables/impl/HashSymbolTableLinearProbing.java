package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.ArrayList;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.symboltables.SymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class HashSymbolTableLinearProbing<K, V> implements SymbolTable<K, V> {

    private static final int INIT_CAPACITY = 4;

    private int M;
    private int elemsCount = 0;
    @SuppressWarnings("unchecked")
    private K[] keys;
    @SuppressWarnings("unchecked")
    private V[] vals;

    public HashSymbolTableLinearProbing() {
        this(INIT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashSymbolTableLinearProbing(int capacity) {
        M = capacity;
        keys = (K[]) new Object[M];
        vals = (V[]) new Object[M];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity > elemsCount;
        HashSymbolTableLinearProbing<K, V> rehashingTmp = new HashSymbolTableLinearProbing<>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                rehashingTmp.put(keys[i], vals[i]);
            }
        }
        keys = rehashingTmp.keys;
        vals = rehashingTmp.vals;
        M = rehashingTmp.M;
    }

    /**
     * returns value between 0 and M-1
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M; //take 31 LSB bits
    }

    @Override
    public void put(K k, V v) {
        if (v == null) {
            delete(k);
        }
        if (elemsCount >= M / 2) {
            resize(2 * M);
        }

        int i;
        for (i = hash(k); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(k)) {
                break;
            }
        }
        elemsCount++;
        keys[i] = k;
        vals[i] = v;
    }

    @Override
    public V get(K k) {
        for (int i = hash(k); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(k)) {
                return vals[i];
            }
        }
        return null;
    }

    @Override
    public void delete(K k) {
        if (!contains(k)) {
            return;
        }

        // find position i of key
        int i = hash(k);
        while (!k.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // delete key and associated value
        keys[i] = null;
        vals[i] = null;

        // rehash all keys in same cluster
        i = (i + 1) % M;
        while (keys[i] != null) {
            // delete keys[i] an vals[i] and reinsert
            K keyToRehash = keys[i];
            V valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            elemsCount--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        elemsCount--;

        // halves size of array if it's 12.5% full or less
        if (elemsCount > 0 && elemsCount <= M / 8) {
            resize(M / 2);
        }
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
        return elemsCount;
    }

    @Override
    public Iterable<K> keys() {
        List<K> nnkeys = new ArrayList<>();
        for (K k : keys) {
            if (k != null) {
                nnkeys.add(k);
            }
        }
        return nnkeys;
    }

}
