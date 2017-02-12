/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;

/**
 *
 * @author artur
 */
public class SimpleQueuesTest {

    @Test
    public void testResizingArrayQueue() {
        SimpleQueue<String> q = new QueueOfStringsResizingArray();
        this.makeTest(q);

    }

    @Test
    public void testArrayQueue() {
        SimpleQueue<String> q = new QueueOfStringsArray(10);
        this.makeTest(q);

    }

    @Test
    public void testLinkedListQueue() {
        SimpleQueue<String> q = new QueueOfStringsLinkedList();
        this.makeTest(q);

    }

    private void makeTest(SimpleQueue<String> q) {
        String[] expectedArr = {"to", "be", "not", "that", "or", "be"};

        for (String string : expectedArr) {
            q.enqueue(string);

        }
        for (int i = 0; i < expectedArr.length; i++) {
            System.out.println(q.dequeue());
        }
        System.out.println("END############");
        System.out.println("###############");

    }

}
