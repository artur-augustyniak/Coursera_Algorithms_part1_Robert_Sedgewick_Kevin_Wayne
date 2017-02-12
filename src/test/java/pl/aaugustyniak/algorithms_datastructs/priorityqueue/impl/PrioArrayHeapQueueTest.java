package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl;

import java.util.Arrays;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;

/**
 *
 * @author aaugustyniak
 */
public class PrioArrayHeapQueueTest {

    private PrioSimpleQueue<Integer> q;
    Integer[] input;
    int n = 1000;

    @Before
    @SuppressWarnings("empty-statement")
    public void setUp() {
        q = new PrioArrayHeapQueue<>();
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
        for (int i = 1; i <= n; i++) {
            Assert.assertEquals(input[input.length - i], q.dequeueMax());
        }
    }

    @Test
    public void dequeueMinTest() {
        for (Integer i : input) {
            q.enqueue(i);
        }
        Arrays.sort(input);
        Integer[] actual = new Integer[n];

        for (int i = 0; i < n; i++) {
            actual[i] = q.dequeueMin();
        }
//        System.err.println(Arrays.toString(input));
//        System.err.println(Arrays.toString(actual));
        Assert.assertArrayEquals(input, actual);
    }

    @Test
    public void testMaxHeapCondition() {

        for (Integer i : input) {
            q.enqueue(i);
        }
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            if (r.nextInt() % 2 == 0) {
                q.dequeueMin();
            } else {
                q.dequeueMax();
            }
        }

    }

    @Test
    public void ordQueueTest() {

        q.enqueue(4);
        q.enqueue(2);
        q.enqueue(6);
        q.enqueue(5);
        q.enqueue(1);
        q.enqueue(9);
        assertEquals((Integer) 1, (Integer) q.dequeueMin());
        assertEquals((Integer) 9, (Integer) q.dequeueMax());
        q.enqueue(5);
        assertEquals((Integer) 6, (Integer) q.dequeueMax());
        assertEquals((Integer) 5, (Integer) q.dequeueMax());
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals((Integer) 5, (Integer) q.dequeueMax());
        assertEquals((Integer) 4, (Integer) q.dequeueMax());
        assertEquals((Integer) 3, (Integer) q.dequeueMax());
    }

    @Test
    public void segdwicClientTestMax() {
        int nTop = 5;
        q = new PrioArrayHeapQueue<>();
        Integer[] lastNTop = new Integer[nTop];
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
        Arrays.sort(input);
        Integer[] expectedlastNTop = new Integer[nTop];
        int j = nTop - 1;
        for (int k = input.length - 1; k > input.length - (nTop + 1); k--) {
            expectedlastNTop[j] = input[k];
            j--;
        }
        System.err.println(Arrays.toString(lastNTop));
        Arrays.sort(lastNTop);
        Assert.assertArrayEquals(expectedlastNTop, lastNTop);
    }

}
