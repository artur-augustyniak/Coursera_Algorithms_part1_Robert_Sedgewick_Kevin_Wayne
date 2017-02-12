package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.ArrayAccessCostModel;
import pl.aaugustyniak.algorithms_datastructs.unionfind.UnionFind;

/**
 *
 * @author artur
 * @param <T>
 */
public class WeightedQuickUnionUnionFind<T> implements UnionFind<T>, ArrayAccessCostModel {

    List<T> objects = new ArrayList<>();
    List<Integer> id = new ArrayList<>();
    List<Integer> subtreeWeight = new ArrayList<>();
    private Integer cost = 0;
    private int componentsCount = 0;

    @Override
    public void union(T p, T q) {
        int i = findRoot(p);
        int j = findRoot(q);
        if (i == j) {
            return;
        }

        if (this.subtreeWeight.get(i) < this.subtreeWeight.get(j)) {
            this.id.set(i, j);
            int newTreeWeight = subtreeWeight.get(i) + subtreeWeight.get(j);
            this.subtreeWeight.set(j, newTreeWeight);
        } else {
            this.id.set(j, i);
            int newTreeWeight = subtreeWeight.get(j) + subtreeWeight.get(i);
            this.subtreeWeight.set(i, newTreeWeight);
        }

        cost++;
        componentsCount--;

    }

    @Override
    public boolean areConnected(T p, T q) {
        return this.findRoot(p) == this.findRoot(q);
    }

    private int findRoot(T p) {
        int abstractP = this.objects.indexOf(p);
        while (abstractP != this.id.get(abstractP)) {
            cost++;
            abstractP = this.id.get(abstractP);
        }
        return abstractP;
    }

    @Override
    public Integer componentsCount() {
        return componentsCount;
    }

    @Override
    public void addElement(T p) {
        componentsCount++;
        this.objects.add(p);
        this.subtreeWeight.add(1);
        if (this.id.size() > 0) {
            Integer newComponentId = Collections.max(this.id) + 1;
            this.id.add(newComponentId);
        } else {
            this.id.add(0);
        }
    }

    @Override
    public void removeElement(T p) {

    }

    @Override
    public Integer getCost() {
        return cost;
    }
}
