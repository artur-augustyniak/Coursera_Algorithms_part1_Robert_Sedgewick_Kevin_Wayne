/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.convexhull;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author artur
 */
public class CoordYShellSorterTest {

    public CoordYShellSorterTest() {
    }

    @Before
    public void setUp() {
    }

    /**
     * Test of sort method, of class CoordYShellSorter.
     */
    @Test
    public void testSort() {
        GrahamScanShellSorter s = new GrahamScanShellSorter(GrahamScanShellSorter.BY_YCOORD);
        SimplePoint2D[] pointsCcw = {new SimplePoint2D(0.0, 0.0), new SimplePoint2D(0.0, 1.0), new SimplePoint2D(-1.0, 0.5)};
        System.out.println(Arrays.toString(s.sort(pointsCcw)));
    }

}
