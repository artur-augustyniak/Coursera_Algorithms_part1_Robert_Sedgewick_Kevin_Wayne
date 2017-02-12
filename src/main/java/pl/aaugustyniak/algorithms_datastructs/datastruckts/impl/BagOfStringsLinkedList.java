package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleBag;

/**
 *
 * @author artur
 */
public class BagOfStringsLinkedList implements SimpleBag<String> {

    private class Node {

        String item;
        Node next;
    }

    private Node head = null;
    private int N = 0;

    @Override
    public void add(String item) {
        Node tmp = head;
        head = new Node();
        head.item = item;
        head.next = tmp;
        N++;
    }

    @Override
    public boolean isEmpty() {
        return N == 0;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public String next() {
                String s = current.item;
                current = current.next;
                return s;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not Safe.");
            }
        };
    }

}
