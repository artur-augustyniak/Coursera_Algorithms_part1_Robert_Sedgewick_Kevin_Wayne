/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.selectionproblem;

import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaugustyniak
 */
public class HoareQuickSelectorTest {

    /**
     * Test of select method, of class HoareQuickSelector.
     */
    @Test
    public void testSelect() {
        Random r = new Random(42);
        int n = 10000000;

        HoareQuickSelector<Integer> s = new HoareQuickSelector<>();
        Integer[] input = new Integer[n];
        for (int i = 0; i < n; i++) {
            input[i] = i;
        }
        int expectedK = 1000;
        //Collections.shuffle(Arrays.asList(input), r);
        //System.out.println(Arrays.toString(input));
        long startTime = System.currentTimeMillis();
        int actualK = s.select(input, expectedK);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        //System.out.println(Arrays.toString(input));
        System.out.println("Quickselector time: " + time);
        assertEquals(actualK, expectedK);
    }

}
