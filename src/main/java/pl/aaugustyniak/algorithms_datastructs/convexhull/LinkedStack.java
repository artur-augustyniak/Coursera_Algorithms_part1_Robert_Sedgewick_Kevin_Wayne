package pl.aaugustyniak.algorithms_datastructs.convexhull;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class LinkedStack<T> implements SimpleStack<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T ret = current.item;
                current = current.next;
                return ret;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    private class Node {

        T item;
        Node next;
    }

    private Node head = null;

    @Override
    public void push(T item) {
        Node tmp = head;
        head = new Node();
        head.item = item;
        head.next = tmp;

    }

    @Override
    public T pop() {
        T ret = head.item;
        head = head.next;
        return ret;
    }

    @Override
    public T peek() {
        return head.item;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

}
