package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;

/**
 *
 * @author artur
 */
public class QueueOfStringsResizingArray implements SimpleQueue<String> {

    private int headPointer = 0;
    private int tailPointer = 0;
    private int elemCount = 0;
    private String[] s;

    public QueueOfStringsResizingArray() {
        s = new String[1];
    }

    private void resize(int max) {
        assert max >= elemCount;
        String[] temp = new String[max];
        for (int i = 0; i < elemCount; i++) {
            //zawijanie początku tablicy
            temp[i] = s[(headPointer + i) % s.length];
        }
        s = temp;
        headPointer = 0;
        tailPointer = elemCount;
    }

    @Override
    public void enqueue(String item) {
        if (elemCount == s.length) {
            resize(2 * s.length);
        }
        s[tailPointer++] = item;
        if (tailPointer == s.length) {
            tailPointer = 0;
        }
        elemCount++;
    }

    @Override
    public String dequeue() {
        String ret = s[headPointer];
        s[headPointer++] = null;
        elemCount--;
        if (headPointer == s.length) {
            headPointer = 0;
        }
        if (elemCount > 0 && elemCount == s.length / 4) {
            resize(s.length / 2);
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
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
