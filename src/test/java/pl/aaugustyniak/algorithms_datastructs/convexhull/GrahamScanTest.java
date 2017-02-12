/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.convexhull;

import org.junit.Test;

/**
 *
 * @author artur
 */
public class GrahamScanTest {
    
    private final SimplePoint2D[] pointsSome = {new SimplePoint2D(0.0, 0.0), new SimplePoint2D(0.0, 1.0), new SimplePoint2D(-1.0, 0.5)};
    private final SimplePoint2D[] pointsCcw = {new SimplePoint2D(0.0, 0.0), new SimplePoint2D(0.0, 1.0), new SimplePoint2D(-1.0, 0.5)};
    private final SimplePoint2D[] pointsCw = {new SimplePoint2D(0.0, 0.0), new SimplePoint2D(0.0, 1.0), new SimplePoint2D(1.0, 0.5)};
    private final SimplePoint2D[] pointsColinear = {new SimplePoint2D(0.0, 0.0), new SimplePoint2D(0.0, 1.0), new SimplePoint2D(0.0, 2.5)};
//    private final GrahamScan scan = new GrahamScan(pointsSome);
//
//    @Test
//    public void testGetTurnOrder() {
//        
//        assertEquals(TurnOrder.COUNTER_CLOCKWISE, scan.getTurnOrder(pointsCcw[0], pointsCcw[1], pointsCcw[2]));
//        assertEquals(scan.getTurnOrder(pointsCw[0], pointsCw[1], pointsCw[2]), TurnOrder.CLOCKWISE);
//        assertEquals(scan.getTurnOrder(pointsColinear[0], pointsColinear[1], pointsColinear[2]), TurnOrder.COLLINEAR);
//    }

    @Test
    public void proceedScanTest() {
        SimplePoint2D[] points = {
            new SimplePoint2D(-4.0, 0.0),
            new SimplePoint2D(-1.5, 3.0),
            new SimplePoint2D(0.0, 1.0),
            new SimplePoint2D(-1.5, -2.0),
            new SimplePoint2D(2.0, 0.1),
            new SimplePoint2D(2.0, -3.0),
            new SimplePoint2D(4.0, 2.0),
        };
        GrahamScan scanner = new GrahamScan(points);
//        scanner.getConvexHull();
        for (SimplePoint2D point : scanner.getConvexHull()) {
            System.out.println(point);
        }
        

    }

}
