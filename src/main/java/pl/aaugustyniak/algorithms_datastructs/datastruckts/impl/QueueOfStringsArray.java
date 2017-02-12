package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;

/**
 *
 * @author artur
 */
public class QueueOfStringsArray implements SimpleQueue<String> {

    private int headN = 0;
    private int tailN = 0;
    private String[] s;

    public QueueOfStringsArray(int capacity) {
        s = new String[capacity];
    }

    @Override
    public void enqueue(String item) {
        s[tailN++] = item;
    }

    @Override
    public String dequeue() {
        String ret = s[headN];
        s[headN++] = null;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return headN == tailN;
    }

    @Override
    public int size() {
        return tailN - headN;
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
