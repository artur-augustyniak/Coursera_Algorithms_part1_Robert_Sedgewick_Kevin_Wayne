package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.HashMap;
import java.util.Map;
import pl.aaugustyniak.algorithms_datastructs.symboltables.SymbolTable;

/**
 *
 * @author aaugustyniak
 * @param <K>
 * @param <V>
 */
public class SymbolTableHaskMap<K, V> implements SymbolTable<K, V> {

    private final Map<K, V> map;

    public SymbolTableHaskMap() {
        this.map = new HashMap<>();
    }

    @Override
    public void put(K k, V v) {
        map.put(k, v);
    }

    @Override
    public V get(K k) {
        return map.get(k);
    }

    @Override
    public void delete(K k) {
        map.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return map.containsKey(k);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterable<K> keys() {
        return map.keySet();
    }

}
