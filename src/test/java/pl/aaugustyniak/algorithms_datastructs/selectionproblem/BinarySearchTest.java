/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.selectionproblem;

import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author aaugustyniak
 */
public class BinarySearchTest {

    public BinarySearchTest() {
    }

    /**
     * Test of getIndex method, of class BinarySearch.
     */
    @Test
    public void testGetIndex() {
        Integer[] input = {3, 4, 1, 5, 6, 7, 3, 2, 4, 5, 12, 23};

        Arrays.sort(input);
        System.out.println(Arrays.toString(input));
        BinarySearch<Integer> s = new BinarySearch<>();
        System.out.println("getIndex " + s.getIndex(input, 3));
    }

}
