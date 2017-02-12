package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class UnsortedArrayResizingPrioQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private T[] arr;
    private int numberOfElements;

    public UnsortedArrayResizingPrioQueue() {
        arr = (T[]) new Comparable[1];
        this.numberOfElements = 0;
    }

    private void resize(int max) {
      //  assert max >= numberOfElements;
        T[] temp = (T[]) new Comparable[max];
        for (int i = 0; i < numberOfElements; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    @Override
    public T dequeueMax() {
        checkHalving();
        int max = 0;
        for (int i = 1; i < numberOfElements; i++) {
            if (less(max, i)) {
                max = i;
            }
        }
        exch(max, numberOfElements - 1);
        return arr[--numberOfElements];
    }

    private void checkHalving() {
        if (numberOfElements > 0 && numberOfElements == arr.length / 4) {
            resize(arr.length / 2);
        }
    }

    @Override
    public T dequeueMin() {
        checkHalving();
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
        if (numberOfElements == arr.length) {
            resize(2 * arr.length);
        }
        arr[numberOfElements++] = item;

    }

    @Override
    public T dequeue() {
        checkHalving();
        T ret = arr[0];
        for (int i = 1; i < numberOfElements; i++) {
            arr[i - 1] = arr[i];
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
