/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author aaugustyniak
 */
public class QuickSorterTest {

    @Ignore
    @Test
    public void testPartitioning() {
        Random r = new Random(444);
        Integer[] input = {2, 4, 8, 3};
        Collections.shuffle(Arrays.asList(input), r);
        System.out.println(Arrays.toString(input));
        int expectedIdx = 4;
        QuickSorter<Integer> s = new QuickSorter<>();
        s.sort(input);
        int actualIdx = s.arrPartition(0, 3);

        System.out.println(Arrays.toString(input));
        assertEquals(expectedIdx, actualIdx);

    }

   
    @Test
    public void testQuickSorter() {
        Random r = new Random(42);
        int n = 100;
        //Math.abs(r.nextInt(120));

        QuickSorter<Integer> s = new QuickSorter<>();
        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];

        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        Arrays.sort(input); //qsort worst case
        long startTime = System.currentTimeMillis();
        Integer[] actual = s.sort(input);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Quicksorter time: " + time);
        assertArrayEquals(expected, actual);
    }

    
    @Test
    public void testEntropyQuickSorter() {
        Random r = new Random(42);
        int n = 6;
        //Math.abs(r.nextInt(120));

        DijkstraEntropyQuickSorter<Integer> s = new DijkstraEntropyQuickSorter<>();
        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];

        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        Arrays.sort(input); //qsort worst case
        long startTime = System.currentTimeMillis();
        Integer[] actual = s.sort(input);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Entropy Quicksorter time: " + time);
        assertArrayEquals(expected, actual);
    }

}
