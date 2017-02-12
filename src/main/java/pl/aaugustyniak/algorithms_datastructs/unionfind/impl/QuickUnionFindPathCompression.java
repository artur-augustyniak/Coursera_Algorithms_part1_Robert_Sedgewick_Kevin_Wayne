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
public class QuickUnionFindPathCompression<T> implements UnionFind<T>, ArrayAccessCostModel {

    List<T> objects = new ArrayList<>();
    List<Integer> id = new ArrayList<>();
    private Integer cost = 0;
    private int componentsCount = 0;

    @Override
    public void union(T p, T q) {
        int i = findRoot(p);
        int j = findRoot(q);
        if (i == j) {
            return;
        }
        cost++;
        componentsCount--;
        this.id.set(i, j);
    }

    @Override
    public boolean areConnected(T p, T q) {
        return this.findRoot(p) == this.findRoot(q);
    }

    private int findRoot(T p) {
        int rootCond = this.objects.indexOf(p);
        int tmp = this.objects.indexOf(p);

        //idx is not self root
        while (rootCond != this.id.get(rootCond)) {
            cost++;
            rootCond = this.id.get(rootCond);
        }

        this.id.set(tmp, rootCond);

        return rootCond;
    }

    @Override
    public Integer componentsCount() {
        return componentsCount;
    }

    @Override
    public void addElement(T p) {
        componentsCount++;
        this.objects.add(p);
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
