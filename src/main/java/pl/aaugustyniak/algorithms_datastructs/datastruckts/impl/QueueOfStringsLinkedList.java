package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;

/**
 *
 * @author artur
 */
public class QueueOfStringsLinkedList implements SimpleQueue<String> {

    private class Node {

        String item;
        Node next;
    }

    private Node head = null;
    private Node tail = null;

    @Override
    public void enqueue(String item) {

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
    }

    @Override
    public String dequeue() {
        String ret = null;
        ret = head.item;
        head = head.next;
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<String> iterator() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
