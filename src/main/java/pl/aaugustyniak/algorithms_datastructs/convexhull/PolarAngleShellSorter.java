package pl.aaugustyniak.algorithms_datastructs.convexhull;

import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 */
public class PolarAngleShellSorter extends BaseSorter<SimplePoint2D> {

    private SimplePoint2D respectTo;

    public PolarAngleShellSorter(SimplePoint2D respectTo) {
        this.respectTo = respectTo;
    }

    @Override
    public SimplePoint2D[] sort(SimplePoint2D[] arr) {
        int h = 1;
        while (h < arr.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (lessByAngle(arr[j], arr[j - 1])) {
                        swap(arr, j, j - 1);
                    } else {
                        break;
                    }
                }
            }
            h = h / 3;
        }
        return arr;
    }

    private boolean lessByAngle(SimplePoint2D v, SimplePoint2D w) {
        return polarAngle(v, respectTo) < polarAngle(w, respectTo);
    }

    private Double polarAngle(SimplePoint2D v, SimplePoint2D w) {
        Double deltaY = v.getY() - w.getY();
        Double deltaX = v.getX() - w.getX();
        Double angleInDeg = Math.atan2(deltaY, deltaX) / 180 * Math.PI;
        return angleInDeg;

    }

    private Double vectorAngle(SimplePoint2D v, SimplePoint2D w) {
        Double crossProduct = (v.getX() * w.getX()) + (v.getY() * w.getY());
        Double vVectLen = Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
        Double wVectLen = Math.sqrt(Math.pow(w.getX(), 2) + Math.pow(w.getY(), 2));
        Double angleCosine = crossProduct / (vVectLen * wVectLen);
        return Math.acos(angleCosine);
    }

}
