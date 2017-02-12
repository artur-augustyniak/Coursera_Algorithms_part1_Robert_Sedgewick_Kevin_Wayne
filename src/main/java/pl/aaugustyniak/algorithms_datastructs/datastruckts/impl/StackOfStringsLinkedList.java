package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class StackOfStringsLinkedList implements SimpleStack<String> {

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
                String ret = current.item;
                current = current.next;
                return ret;
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

    private class Node {

        String item;
        Node next;
    }

    private Node head = null;

    @Override
    public void push(String item) {
        Node tmp = head;
        head = new Node();
        head.item = item;
        head.next = tmp;

    }

    @Override
    public String pop() {
        String ret = head.item;
        head = head.next;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
