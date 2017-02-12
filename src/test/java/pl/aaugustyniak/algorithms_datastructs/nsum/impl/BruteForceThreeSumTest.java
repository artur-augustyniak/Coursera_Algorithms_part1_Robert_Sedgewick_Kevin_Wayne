/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.nsum.impl;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author artur
 */
public class BruteForceThreeSumTest {

    public BruteForceThreeSumTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of run method, of class BruteForceThreeSum.
     */
    @Test
    public void testClient() {
        BruteForceThreeSum s;
        Integer starN = 1000;
        Integer doublingNth = 1;
        for (int i = 0; i < doublingNth; i++) {
            s = new BruteForceThreeSum(starN);
            s.run();
            System.out.println(starN + " " + s.getMicrotime() / 1000.0);
            //System.out.println("arrayacess " + s.getCost());
            starN *= 2;

        }
    }

}
