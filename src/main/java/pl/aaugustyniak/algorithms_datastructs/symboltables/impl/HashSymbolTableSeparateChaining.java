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
public class HashSymbolTableSeparateChaining<K, V> implements SymbolTable<K, V> {

    private static final int INIT_CAPACITY = 4;

    // largest prime <= 2^i for i = 3 to 31
    // not currently used for doubling and shrinking
    // private static final int[] PRIMES = {
    //    7, 13, 31, 61, 127, 251, 509, 1021, 2039, 4093, 8191, 16381,
    //    32749, 65521, 131071, 262139, 524287, 1048573, 2097143, 4194301,
    //    8388593, 16777213, 33554393, 67108859, 134217689, 268435399,
    //    536870909, 1073741789, 2147483647
    // };
    private int M;
    private Node[] st;
    private int elemsCount = 0;

    public HashSymbolTableSeparateChaining() {
        this(INIT_CAPACITY);
    }

    public HashSymbolTableSeparateChaining(int capacity) {
        M = capacity;
        st = new Node[M];

    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        HashSymbolTableSeparateChaining<K, V> rehashingTmp = new HashSymbolTableSeparateChaining<>(capacity);
        for (K k : keys()) {
            rehashingTmp.put(k, get(k));
        }
        M = rehashingTmp.M;
        elemsCount = rehashingTmp.elemsCount;
        st = rehashingTmp.st;
    }

    private static class Node {

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }

        private Object key;
        private Object val;
        private Node next;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M; //take 31 LSB bits
    }

    @Override
    public void put(K k, V v) {

        if (v == null) {
            delete(k);
            return;
        }

        // double table size if average length of list >= 10
        if (elemsCount >= 10 * M) {
            resize(2 * M);
        }

        int i = hash(k);
        for (Node x = st[i]; x != null; x = x.next) {
            if (k.equals(x.key)) {
                x.val = v;
                return;
            }
        }
        elemsCount++;
        st[i] = new Node(k, v, st[i]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(K k) {
        int i = hash(k);
        for (Node x = st[i]; x != null; x = x.next) {
            if (k.equals(x.key)) {
                return (V) x.val;
            }
        }
        return null;
    }

    @Override
    public void delete(K k) {
        int i = hash(k);
        st[i] = delete(st[i], k);
        // halve table size if average length of list <= 2
        if (M > INIT_CAPACITY && elemsCount <= 2 * M) {
            resize(M / 2);
        }
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            elemsCount--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    @Override
    public boolean contains(K k) {
        return get(k) != null;
    }

    @Override
    public boolean isEmpty() {
        return elemsCount > 0;
    }

    @Override
    public int size() {
        return elemsCount;
    }

    @Override
    public Iterable<K> keys() {
        List<K> keys = new ArrayList<>();
        for (Node n : st) {
            for (Node x = n; x != null; x = x.next) {
                keys.add((K) x.key);
            }

        }
        return keys;
    }

}
