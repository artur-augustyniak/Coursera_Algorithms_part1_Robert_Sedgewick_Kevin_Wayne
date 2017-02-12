package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class StackOfStringsArray implements SimpleStack<String> {

    private int N = 0;
    private final String[] s;

    public StackOfStringsArray(int capacity) {
        s = new String[capacity];
    }

    @Override
    public void push(String item) {
        s[N++] = item;
    }

    @Override
    public String pop() {

        String ret = s[--N];
        s[N] = null;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            int current = N;

            @Override
            public boolean hasNext() {
                return current > 0;
            }

            @Override
            public String next() {
                return s[--current];

            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    @Override
    public String peek() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
