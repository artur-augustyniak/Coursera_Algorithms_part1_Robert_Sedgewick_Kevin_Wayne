package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public class PrioLinkedListQueue<T extends Comparable<T>> implements PrioSimpleQueue<T> {

    private class Node {

        T item;
        Node next;

    }

    private Node head = null;
    private Node tail = null;
    private int numElem = 0;

    private boolean lessOrEq(T v, T w) {
        return (v.compareTo(w) < 0);
    }

    @Override
    public T dequeueMax() {
        Node maxNode = head;
        Node it = head;
        while (it != null) {
            if (lessOrEq(maxNode.item, it.item)) {
                maxNode = it;
            }
            it = it.next;
        }
        swapNodesValues(head, maxNode);
        return dequeue();
    }

    private void swapNodesValues(Node a, Node b) {
        T tmp = a.item;
        a.item = b.item;
        b.item = tmp;
    }

    @Override
    public T dequeueMin() {
        Node minNode = head;
        Node it = head;
        while (it != null) {
            if (lessOrEq(it.item, minNode.item)) {
                minNode = it;
            }
            it = it.next;
        }
        swapNodesValues(head, minNode);
        return dequeue();
    }

    @Override
    public void enqueue(T item) {
        if (head == null) {
            head = new Node();
            head.item = item;
            tail = head;
        } else {
            Node oldLast = tail;
            tail = new Node();
            tail.item = item;
            tail.next = null;
            oldLast.next = tail;
        }
        ++numElem;
    }

    @Override
    public T dequeue() {
        T ret = null;
        ret = head.item;
        head = head.next;
        numElem--;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return numElem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node itPointer = head;

            @Override
            public boolean hasNext() {
                return itPointer != null;
            }

            @Override
            public T next() {
                T ret = itPointer.item;
                itPointer = itPointer.next;
                return ret;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported.");
            }
        };
    }

}
