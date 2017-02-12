package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Arrays;
import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class PrioResizingArrayQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private int headPointer = 0;
    private int iterator = 0;
    private int tailPointer = 0;
    private int elemCount = 0;
    private T[] s;
    private BaseSorter<T> customSorter;

    public void setCustomSorter(BaseSorter<T> customSorter) {
        this.customSorter = customSorter;
    }

    @SuppressWarnings(value="unchecked")
    public PrioResizingArrayQueue() {
        s = (T[]) new Comparable[1];
    }

    private void resort() {
        if (customSorter != null) {
            customSorter.sort(s);
        } else {
            int tail = (tailPointer < headPointer) ? headPointer : tailPointer;
            Arrays.sort(s, headPointer, tail);
        }

    }

    private void resize(int max) {
        assert max >= elemCount;
        T[] temp = (T[]) new Comparable[max];
        for (int i = 0; i < elemCount; i++) {
            //zawijanie początku tablicy
            temp[i] = s[(headPointer + i) % s.length];
        }
        s = temp;
        headPointer = 0;
        tailPointer = elemCount;
    }

    @Override
    public void enqueue(T item) {
        if (elemCount == s.length) {
            resize(2 * s.length);
        }
        s[tailPointer++] = item;
        if (tailPointer == s.length) {
            tailPointer = 0;
        }
        elemCount++;
        resort();
    }

    @Override
    public T dequeue() {
        T ret = s[headPointer];
        s[headPointer++] = null;
        elemCount--;
        if (headPointer == s.length) {
            headPointer = 0;
        }
        if (elemCount > 0 && elemCount == s.length / 4) {
            resize(s.length / 2);
        }
        iterator = headPointer;

        return ret;
    }

    private T dequeueRev() {
        T ret = null;
        if (tailPointer >= 1 && elemCount > 1) {
            ret = s[tailPointer - 1];
            s[tailPointer--] = null;
            elemCount--;
            if (elemCount > 0 && elemCount == s.length / 4) {
                resize(s.length / 2);
            }
        } else {
            ret = dequeue();
        }
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return elemCount == 0;
    }

    @Override
    public int size() {
        return elemCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return tailPointer - iterator > 0;
            }

            @Override
            public T next() {
                return s[iterator++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
    }

    @Override
    public T dequeueMax() {

        return dequeueRev();
    }

    @Override
    public T dequeueMin() {
        return dequeue();
    }

}
