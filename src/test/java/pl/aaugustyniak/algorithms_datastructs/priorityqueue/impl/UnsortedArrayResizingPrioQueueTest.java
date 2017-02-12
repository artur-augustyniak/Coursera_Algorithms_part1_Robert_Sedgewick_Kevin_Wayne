/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Arrays;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 */
public class UnsortedArrayResizingPrioQueueTest {

    private PrioSimpleQueue<Integer> q;
    Integer[] input;
    int n = 1000;

    @Before
    @SuppressWarnings("empty-statement")
    public void setUp() {
        q = new UnsortedArrayResizingPrioQueue<>();
        input = new Integer[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            input[i] = r.nextInt();
        }

    }

    @Test
    public void dequeueMaxTest() {
        for (Integer i : input) {
            q.enqueue(i);
        }
        Arrays.sort(input);
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= n; i++) {
            Assert.assertEquals(input[input.length - i], q.dequeueMax());
        }
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Deque max resizing time: " + time);

    }

    @Test
    public void dequeueMinTest() {
        for (Integer i : input) {
            q.enqueue(i);
        }
        Arrays.sort(input);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Assert.assertEquals(input[i], q.dequeueMin());
        }
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Deque min resizing time: " + time);

    }

    @Test
    public void ordQueueTest() {
        long startTime = System.currentTimeMillis();
        q.enqueue(4);
        q.enqueue(2);
        q.enqueue(6);
        assertEquals((Integer) 2, (Integer) q.dequeueMin());
        assertEquals((Integer) 4, (Integer) q.dequeueMin());
        q.enqueue(5);
        assertEquals((Integer) 6, (Integer) q.dequeueMax());
        assertEquals((Integer) 5, (Integer) q.dequeueMax());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("MIxed resizing time: " + time);
        assertEquals((Integer) 3, (Integer) q.dequeueMax());
        assertEquals((Integer) 2, (Integer) q.dequeueMax());
        assertEquals((Integer) 1, (Integer) q.dequeueMax());

    }

    @Test
    public void segdwicClientTestMax() {
        int nTop = 5;
        q = new UnsortedArrayResizingPrioQueue<>();
        Integer[] lastNTop = new Integer[nTop];
        long startTime = System.currentTimeMillis();
        for (Integer integer : input) {
            q.enqueue(integer);
            if (q.size() > nTop) {
                Integer ev = q.dequeueMin();
                int i = 0;
                for (Integer qint : q) {
                    lastNTop[i] = qint;
                    i++;
                }
            }
        }
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Segdwik client time: " + time);
        Arrays.sort(input);
        Integer[] expectedlastNTop = new Integer[nTop];
        int j = nTop - 1;
        for (int k = input.length - 1; k > input.length - (nTop + 1); k--) {
            expectedlastNTop[j] = input[k];
            j--;
        }
        Arrays.sort(lastNTop);
        Assert.assertArrayEquals(expectedlastNTop, lastNTop);
    }

    @Test
    public void makeTestAsOrdinaryQueue() {
        SimpleQueue<String> qq = new UnsortedArrayResizingPrioQueue<>();
        String[] expectedArr = {"to", "be", "not", "that", "or", "be"};
        String[] actualArr = new String[6];
        for (String string : expectedArr) {
            qq.enqueue(string);

        }
        for (int i = 0; i < expectedArr.length; i++) {
            actualArr[i] = qq.dequeue();

        }
        Assert.assertArrayEquals(expectedArr, actualArr);

    }

}
