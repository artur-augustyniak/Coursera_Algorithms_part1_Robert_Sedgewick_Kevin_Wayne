package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class SortedArrayPrioQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private T[] arr;
    private int numberOfElems;

    public SortedArrayPrioQueue(int capacity) {
        arr = (T[]) new Comparable[capacity];
        this.numberOfElems = 0;
    }

    @Override
    public T dequeueMax() {
        return arr[--numberOfElems];
    }

    @Override
    public T dequeueMin() {
        T ret = arr[0];
        for (int i = 1; i < numberOfElems; i++) {
            arr[i - 1] = arr[i];
        }
        numberOfElems--;
        return ret;
    }

    @Override
    public void enqueue(T item) {
        int i = numberOfElems - 1;
        while (i >= 0 && less(item, arr[i])) {
            arr[i + 1] = arr[i];
            i--;
        }
        arr[i + 1] = item;
        numberOfElems++;
    }

    @Override
    public T dequeue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty() {
        return numberOfElems == 0;
    }

    @Override
    public int size() {
        return numberOfElems;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int itPointer = 0;

            @Override
            public boolean hasNext() {
                return itPointer < numberOfElems;
            }

            @Override
            public T next() {
                return arr[itPointer++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

    private boolean less(T v, T w) {
        return (v.compareTo(w) < 0);
    }

    private void exch(int i, int j) {
        T swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

}
