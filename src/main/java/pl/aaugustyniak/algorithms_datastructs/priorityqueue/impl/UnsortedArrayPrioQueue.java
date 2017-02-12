package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class UnsortedArrayPrioQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private final T[] arr;
    private int numberOfElements;

    public UnsortedArrayPrioQueue(int capacity) {
        arr = (T[]) new Comparable[capacity];
        this.numberOfElements = 0;
    }

    @Override
    public T dequeueMax() {
        int max = 0;
        for (int i = 1; i < numberOfElements; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, numberOfElements - 1);
        return arr[--numberOfElements];
    }

    @Override
    public T dequeueMin() {
        int min = 0;
        for (int i = 1; i < numberOfElements; i++) {
            if (less(i, min)) {
                min = i;
            }
        }
        exch(min, numberOfElements - 1);
        return arr[--numberOfElements];
    }

    @Override
    public void enqueue(T item) {
        arr[numberOfElements++] = item;
    }

    @Override
    public T dequeue() {
        
        T ret = arr[0];
        for (int i = 1; i < numberOfElements; i++) {
                arr[i-1] = arr[i];
        }
        
        
        numberOfElements--;
        return ret;

    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public int size() {
        return numberOfElements;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int itPointer = 0;

            @Override
            public boolean hasNext() {
                return itPointer < numberOfElements;
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

    private boolean less(int i, int j) {
        return (arr[i].compareTo(arr[j]) < 0);
    }

    private void exch(int i, int j) {
        T swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

}
