/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class StackOfStringsArrayResizing implements SimpleStack<String> {

    private int N = 0;
    private String[] s;

    public StackOfStringsArrayResizing() {
        s = new String[1];
    }

    @Override
    public void push(String item) {
        if (N == s.length) {
            String[] grow = new String[2 * s.length];
            for (int i = 0; i < N; i++) {
                grow[i] = s[i];
            }
            s = grow;

        }
        s[N++] = item;
    }

    @Override
    public String pop() {

        String ret = s[--N];
        s[N] = null;
        if (N != 0 && N == s.length / 4) {

            String[] shrink = new String[s.length / 2];
            for (int i = 0; i < N; i++) {
                shrink[i] = s[i];
            }
            s = shrink;

        }

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
