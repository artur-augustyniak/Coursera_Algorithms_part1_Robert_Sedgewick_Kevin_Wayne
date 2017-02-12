package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class SortedArrayResizingPrioQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private T[] arr;
    private int numberOfElems;

    public SortedArrayResizingPrioQueue() {
        arr = (T[]) new Comparable[1];
        this.numberOfElems = 0;
    }

    private void resize(int max) {
        //  assert max >= numberOfElements;
        T[] temp = (T[]) new Comparable[max];
        for (int i = 0; i < numberOfElems; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    private void checkHalving() {
        if (numberOfElems > 0 && numberOfElems == arr.length / 4) {
            resize(arr.length / 2);
        }
    }

    @Override
    public T dequeueMax() {
        checkHalving();
        return arr[--numberOfElems];
    }

    @Override
    public T dequeueMin() {
        checkHalving();
        T ret = arr[0];
        for (int i = 1; i < numberOfElems; i++) {
            arr[i - 1] = arr[i];
        }
        numberOfElems--;
        return ret;
    }

    @Override
    public void enqueue(T item) {
        if (numberOfElems == arr.length) {
            resize(2 * arr.length);
        }
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
