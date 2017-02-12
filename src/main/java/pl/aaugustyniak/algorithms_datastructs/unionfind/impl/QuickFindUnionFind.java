package pl.aaugustyniak.algorithms_datastructs.unionfind.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.ArrayAccessCostModel;
import pl.aaugustyniak.algorithms_datastructs.unionfind.UnionFind;

/**
 *
 * @author artur
 * @param <T>
 */
public class QuickFindUnionFind<T> implements UnionFind<T>, ArrayAccessCostModel {

    List<T> objects = new ArrayList<>();
    List<Integer> id = new ArrayList<>();
    private Integer cost = 0;

    @Override
    public void union(T p, T q) {
        if (!this.areConnected(p, q)) {
            cost++;
            int pid = this.find(p);
            for (T t : objects) {
                cost++;
                if (this.find(t) == pid) {
                    cost++;
                    this.id.set(this.objects.indexOf(t), this.find(q));

                }
            }
        }
    }

    @Override
    public boolean areConnected(T p, T q) {
        cost++;
        return this.find(p) == this.find(q);
    }

    private int find(T p) {
        return this.id.get(this.objects.indexOf(p));
    }

    @Override
    public Integer componentsCount() {
        return new HashSet<>(this.id).size();
    }

    @Override
    public void addElement(T p) {
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
