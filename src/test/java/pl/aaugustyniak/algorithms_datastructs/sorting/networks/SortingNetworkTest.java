/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.sorting.networks;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.MergeSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BubbleSorter;

/**
 *
 * @author artur
 */
public class SortingNetworkTest {

    @Test
    public void testMultipleComparator() {
        int n = 10000;
        SortingNetwork<Integer> sn = new SortingNetwork<>(n);
        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];
        Random r = new Random(42);
        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        //System.out.println(Arrays.toString(input));
        Integer[] actual = sn.sort(input);
        //System.out.println(Arrays.toString(actual));
        //System.out.println(Arrays.toString(expected));
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMergeSorter() {
        int n = 10000;

        MergeSorter<Integer> s = new MergeSorter<>();

        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];
        Random r = new Random(42);
        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        //System.out.println(Arrays.toString(input));
        Integer[] actual = s.sort(input);
        //System.out.println(Arrays.toString(actual));
        //System.out.println(Arrays.toString(expected));
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testBubbleSorter() {
        int n = 10000;

        BubbleSorter<Integer> s = new BubbleSorter<>();

        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];
        Random r = new Random(42);
        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        //System.out.println(Arrays.toString(input));
        Integer[] actual = s.sort(input);
        //System.out.println(Arrays.toString(actual));
        //System.out.println(Arrays.toString(expected));
        assertArrayEquals(expected, actual);
    }

}
