/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.shuffling;

import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

/**
 *
 * @author artur
 */
public class ShufflerTest {

    @Test
    public void testShuffler() {
        int n = 4;

        Shuffler<Double> s = new Shuffler<>();

        Double[] input = new Double[n];
        
        Random r = new Random(42);
        for (int i = 0; i < n; i++) {
            input[i] =  r.nextGaussian();
        }
        
        System.out.println(Arrays.toString(s.sort(input)));
        System.out.println(Arrays.toString(s.shuffler(input)));

    }

}
