package pl.aaugustyniak.algorithms_datastructs.convexhull;

import static pl.aaugustyniak.algorithms_datastructs.convexhull.TurnOrder.*;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class GrahamScan {

    SimpleStack<SimplePoint2D> hull = new LinkedStack<>();
    SimplePoint2D[] points;

    public GrahamScan(SimplePoint2D[] points) {
        this.points = points;
        sortPointsByYCoord();
    }

    private void sortPointsByYCoord() {

        GrahamScanShellSorter sorter = new GrahamScanShellSorter(GrahamScanShellSorter.BY_YCOORD);
        points = sorter.sort(points);
        sortPointsByPolarAngleWithRespectToLowestYPoint();
    }

    private void sortPointsByPolarAngleWithRespectToLowestYPoint() {
        GrahamScanShellSorter sorter = new GrahamScanShellSorter(GrahamScanShellSorter.BY_POLAR_ANGLE, points[0]);
        points = sorter.sort(points);
        pushFirstTwoPoints();
    }

    private void pushFirstTwoPoints() {
        hull.push(points[0]);
        hull.push(points[1]);
    }

    public SimpleStack<SimplePoint2D> getConvexHull() {
        for (int i = 2; i < points.length; i++) {
            SimplePoint2D top = hull.pop();
            while (onConvexHull(hull.peek(), top, points[i])) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points[i]);
        }
        return hull;
    }

    private boolean onConvexHull(SimplePoint2D a, SimplePoint2D b, SimplePoint2D c) {
        TurnOrder order = getTurnOrder(a, b, c);
        return order == CLOCKWISE || order == COLLINEAR;

    }

    /**
     * 2 xSigned Area of planar triangle. signed area > 0 a->b->c
     * COUNTER_CLOCKWISE signed area < 0 a->b->c CLOCKWISE signed area = 0
     * a->b->c COLLINEAR
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public TurnOrder getTurnOrder(SimplePoint2D a, SimplePoint2D b, SimplePoint2D c) {
        Double area2 = (b.getX() - a.getX())
                * (c.getY() - a.getY())
                - (b.getY() - a.getY())
                * (c.getX() - a.getX());
        if (area2 < 0) {
            return CLOCKWISE;
        }
        if (area2 > 0) {
            return COUNTER_CLOCKWISE;
        }
        return COLLINEAR;
    }
}
