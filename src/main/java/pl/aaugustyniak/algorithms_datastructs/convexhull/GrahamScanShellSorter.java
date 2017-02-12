package pl.aaugustyniak.algorithms_datastructs.convexhull;

import java.util.Comparator;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 */
public class GrahamScanShellSorter extends BaseSorter<SimplePoint2D> {

    private static class ByYCoord implements Comparator<SimplePoint2D> {

        @Override
        public int compare(SimplePoint2D v, SimplePoint2D w) {
            if (v.getY() < w.getY()) {
                return -1;
            } else if (v.getY() == w.getY()) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private static class ByPolarAngle implements Comparator<SimplePoint2D> {

        private SimplePoint2D respectTo;

        public void setRespectTo(SimplePoint2D respectTo) {
            this.respectTo = respectTo;
        }

        @Override
        public int compare(SimplePoint2D v, SimplePoint2D w) {
            Double vAngle = polarAngle(v, respectTo);
            Double wAngle = polarAngle(w, respectTo);
            if (vAngle < wAngle) {
                return -1;
            } else if (vAngle == wAngle) {
                return 0;
            } else {
                return 1;
            }
        }

        private Double polarAngle(SimplePoint2D v, SimplePoint2D w) {
            double deltaY = v.getY() - w.getY();
            double deltaX = v.getX() - w.getX();
            double angleInDeg = Math.atan2(deltaY, deltaX) / 180 * Math.PI;
            return angleInDeg;

        }
    }

    public static final ByYCoord BY_YCOORD = new ByYCoord();
    public static final ByPolarAngle BY_POLAR_ANGLE = new ByPolarAngle();
    private final Comparator cmp;

    public GrahamScanShellSorter(ByYCoord ycmp) {
        cmp = ycmp;
    }

    public GrahamScanShellSorter(ByPolarAngle acmp, SimplePoint2D respectTo) {
        acmp.setRespectTo(respectTo);
        cmp = acmp;
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
                    if (lessByComparator(arr[j], arr[j - 1])) {
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

    private boolean lessByComparator(SimplePoint2D v, SimplePoint2D w) {
        return cmp.compare(v, w) < 0;
    }
}
