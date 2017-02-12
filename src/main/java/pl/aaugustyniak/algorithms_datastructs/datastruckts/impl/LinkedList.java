/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleList;

/**
 *
 * @author aaugustyniak
 */
public class LinkedList implements SimpleList {

    private final LinkedNode head;
    private int seq = 0;

    public LinkedList(String v) {
        head = new LinkedNode(seq++, v);
    }

    @Override
    public void insertAfter(int k, String v) {
        LinkedNode insertionPoint = find(k);
        LinkedNode n = new LinkedNode(seq++, v);
        if (null != insertionPoint) {
            n.next = insertionPoint.next;
            insertionPoint.next = n;

        } else {
            LinkedNode node = head;
            while (true) {
                if (node.next == null) {
                    node.next = n;
                    break;
                }
                node = node.next;
            }
        }
    }

    @Override
    public LinkedNode find(int k) {
        LinkedNode node = head;
        while (node != null) {
            if (node.key == k) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void display() {
        displayFrom(head);
    }

    public void displayFrom(LinkedNode n) {
        if (n != null) {
            LinkedNode node = n;
            while (node != null) {
                System.out.println(node);
                node = node.next;
            }
        }
    }

    public static void main(String[] args) {
        LinkedList l = new LinkedList("HEAD");
//        l.display();
//        l.displayFrom(l.find(0));
//        l.displayFrom(l.find(10));
        l.insertAfter(0, "First");
        l.insertAfter(0, "Boom");
        l.insertAfter(435, "Last");
        l.displayFrom(l.find(2));
        System.out.println("################3");
        l.display();
    }

}
