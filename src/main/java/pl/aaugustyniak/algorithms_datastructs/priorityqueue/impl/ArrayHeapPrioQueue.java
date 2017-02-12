package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 */
public class ArrayHeapPrioQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private abstract class Invariant {

        public abstract boolean notMeetFor(T pred, T post);

    }

    private class MaxInv extends Invariant {

        @Override
        public boolean notMeetFor(T pred, T post) {
            return pred.compareTo(post) < 0;
        }
    }

    private class MinInv extends Invariant {

        @Override
        public boolean notMeetFor(T pred, T post) {
            return pred.compareTo(post) > 0;
        }
    }

    private final List<T> heap;

    public ArrayHeapPrioQueue() {
        this.heap = new ArrayList<>();
        this.heap.add(null);
    }

    private void applyHeapInvariant(Invariant inv) {

        for (int i = 2; i < this.heap.size(); i++) {
            int currPos = i;
            int currPredcessor = currPos / 2;
            T tmpVal = this.heap.get(i);

            while (currPredcessor > 0 && inv.notMeetFor(this.heap.get(currPredcessor), tmpVal)) {
                this.heap.set(currPos, this.heap.get(currPredcessor));
                currPos = currPredcessor;
                currPredcessor = currPos / 2;
            }
            this.heap.set(currPos, tmpVal);

        }

    }

    @Override
    public T dequeueMax() {
        return this.dequeue();
    }

    @Override
    public T dequeueMin() {
        this.applyHeapInvariant(new MinInv());
        return this.heap.remove(1);
    }

    @Override
    public void enqueue(T item) {
        this.heap.add(item);
    }

    @Override
    public T dequeue() {
        this.applyHeapInvariant(new MaxInv());
        return this.heap.remove(1);

    }

    @Override
    public boolean isEmpty() {
        return this.heap.size() == 1;
    }

    @Override
    public int size() {
        return this.heap.size() - 1;
    }

    @Override
    public Iterator<T> iterator() {
        return heap.subList(1, this.heap.size()).iterator();
    }

}
