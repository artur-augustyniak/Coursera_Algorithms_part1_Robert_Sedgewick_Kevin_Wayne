package pl.aaugustyniak.algorithms_datastructs.convexhull;

import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author artur
 */
public class PolarAngleShellSorterTest {

    /**
     * Test of sort method, of class PolarAngleShellSorter.
     */
    @Test
    public void testSort() {
         SimplePoint2D[] points = {
            new SimplePoint2D(-4.0, 0.0),
            new SimplePoint2D(-1.5, 3.0),
            new SimplePoint2D(0.0, 1.0),
            new SimplePoint2D(-1.5, -2.0),
            new SimplePoint2D(2.0, 0.1),
            new SimplePoint2D(2.0, -3.0),
            new SimplePoint2D(4.0, 2.0),
        };
        CoordYShellSorter s = new CoordYShellSorter();
        points = s.sort(points);
        GrahamScanShellSorter sa = new GrahamScanShellSorter(GrahamScanShellSorter.BY_POLAR_ANGLE, points[0]); 
        //PolarAngleShellSorter sa = new PolarAngleShellSorter(points[0]);
        
        System.out.println(Arrays.toString(s.sort(points)));
    }

}
