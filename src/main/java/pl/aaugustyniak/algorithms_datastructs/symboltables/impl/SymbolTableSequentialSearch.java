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
public class SymbolTableSequentialSearch<K, V> implements SymbolTable<K, V> {

    private int N;           // number of key-value pairs
    private Node first;      // the linked list of key-value pairs

    private class Node {

        private final K key;
        private V val;
        private Node next;

        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    @Override
    public void put(K k, V v) {
        if (v == null) {
            delete(k);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (k.equals(x.key)) {
                x.val = v;
                return;
            }
        }
        first = new Node(k, v, first);
        N++;
    }

    @Override
    public V get(K k) {
        for (Node x = first; x != null; x = x.next) {
            if (k.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    @Override
    public void delete(K k) {
        first = delete(first, k);
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            N--;
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
        return size() != 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterable<K> keys() {
        List<K> list = new ArrayList<>();
        for (Node x = first; x != null; x = x.next) {
            list.add(x.key);
        }
        return list;
    }

}
