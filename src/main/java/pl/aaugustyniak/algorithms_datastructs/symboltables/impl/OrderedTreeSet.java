package pl.aaugustyniak.algorithms_datastructs.symboltables.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import pl.aaugustyniak.algorithms_datastructs.symboltables.OrderedSet;

/**
 *
 * @author aaugustyniak
 * @param <K>
 */
public class OrderedTreeSet<K extends Comparable<K>> implements OrderedSet<K> {

    private final TreeSet<K> set;

    public OrderedTreeSet() {
        set = new TreeSet<>();
    }

    @Override
    public void add(K k) {
        if (k == null) {
            throw new NullPointerException("called add() with a null key");
        }
        set.add(k);
    }

    @Override
    public boolean contains(K k) {
        if (k == null) {
            throw new NullPointerException("called contains() with a null key");
        }
        return set.contains(k);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public void delete(K k) {
        if (k == null) {
            throw new NullPointerException("called delete() with a null key");
        }
        set.remove(k);
    }

    @Override
    public OrderedSet<K> union(OrderedSet<K> that) {
        if (that == null) {
            throw new NullPointerException("called union() with a null argument");
        }
        OrderedSet<K> c = new OrderedTreeSet<>();
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
        OrderedSet<K> c = new OrderedTreeSet<>();
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
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty set");
        }
        return set.first();
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty set");
        }
        return set.last();
    }

    @Override
    public K floor(K k) {
        if (k == null) {
            throw new NullPointerException("called floor() with a null key");
        }
        K key = set.floor(k);
        if (key == null) {
            throw new NoSuchElementException("all keys are greater than " + k);
        }
        return key;
    }

    @Override
    public K ceil(K k) {
        if (k == null) {
            throw new NullPointerException("called ceiling() with a null key");
        }
        K key = set.ceiling(k);
        if (key == null) {
            throw new NoSuchElementException("all keys are less than " + k);
        }
        return key;
    }

    @Override
    public int rank(K k) {
        return set.subSet(min(), k).size();

    }

    @Override
    public K select(int r) {
        if (r < 0 || r >= size()) {
            return null;
        }
        Iterator<K> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Object object = it.next();
            if (i == r) {
                return (K) object;
            }
            i++;
        }
        return null;
    }

    @Override
    public void deleteMin() {
        set.remove(min());
    }

    @Override
    public void deleteMax() {
        set.remove(max());
    }

    @Override
    public int sizeInRange(K from, K to) {
        return set.subSet(from, true, to, true).size();
    }

    @Override
    public Collection<K> keysInRange(K from, K to) {
        return set.subSet(from, true, to, true);
    }

    @Override
    public Iterator<K> iterator() {
        return set.iterator();
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public Collection<K> keys() {
        return keysInRange(min(), max());
    }

}
