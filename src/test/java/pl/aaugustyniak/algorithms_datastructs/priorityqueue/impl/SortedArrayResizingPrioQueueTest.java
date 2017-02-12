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
public class SortedArrayResizingPrioQueueTest {

    private PrioSimpleQueue<Integer> q;
    Integer[] input;
    int n = 1000;

    @Before
    @SuppressWarnings("empty-statement")
    public void setUp() {
        q = new SortedArrayResizingPrioQueue<>();
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
        for (int i = 0; i < n; i++) {
            Assert.assertEquals(input[i], q.dequeueMin());
        }
    }

    @Test
    public void ordQueueTest() {

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
        assertEquals((Integer) 3, (Integer) q.dequeueMax());
        assertEquals((Integer) 2, (Integer) q.dequeueMax());
        assertEquals((Integer) 1, (Integer) q.dequeueMax());
    }

    @Test
    public void segdwicClientTestMax() {
        int nTop = 5;
        q = new SortedArrayResizingPrioQueue<>();
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
        Arrays.sort(lastNTop);
        Assert.assertArrayEquals(expectedlastNTop, lastNTop);
    }



}
