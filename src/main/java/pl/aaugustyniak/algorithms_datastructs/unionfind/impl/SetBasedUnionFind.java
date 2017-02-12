package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import pl.aaugustyniak.algorithms_datastructs.ArrayAccessCostModel;
import pl.aaugustyniak.algorithms_datastructs.unionfind.UnionFind;

/**
 * Java Set UF implementation
 *
 * @author artur
 * @param <T>
 */
public class SetBasedUnionFind<T> implements UnionFind<T> , ArrayAccessCostModel{

    private final Set<Set<T>> datastruct;
    private Integer cost = 0;

    public SetBasedUnionFind() {
        datastruct = new LinkedHashSet<>();
    }

    public SetBasedUnionFind(Set<Set<T>> innerDatastruct) {
        this.datastruct = innerDatastruct;
    }

    @Override
    public void union(T p, T q) {
        Set<T> newConnectedComponent = new HashSet<>();
        Iterator it = datastruct.iterator();
        while (it.hasNext()) {
            cost++;
            Set<T> component = (Set<T>) it.next();
            if (component.contains(p) || component.contains(q)) {
                for (T element : component) {
                    cost++;
                    newConnectedComponent.add(element);
                }
                it.remove();
            }
        }
        datastruct.add(newConnectedComponent);
    }

    @Override
    public boolean areConnected(T p, T q) {
        if (p.equals(q)) {
            return true;
        } else {
            for (Set<T> component : datastruct) {
                cost++;
                if (component.contains(q) && component.contains(p)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Integer componentsCount() {
        return this.datastruct.size();
    }

    @Override
    public void addElement(T p) {
        Set<T> distinctComponent = new HashSet<>();
        distinctComponent.add(p);
        this.datastruct.add(distinctComponent);
    }

    @Override
    public void removeElement(T p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCost() {
        return this.cost;
    }

}
