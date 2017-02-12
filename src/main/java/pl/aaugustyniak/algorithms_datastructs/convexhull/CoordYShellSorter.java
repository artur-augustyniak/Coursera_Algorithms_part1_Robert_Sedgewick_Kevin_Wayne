package pl.aaugustyniak.algorithms_datastructs.convexhull;

import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author artur
 */
public class CoordYShellSorter extends BaseSorter<SimplePoint2D> {

    @Override
    public SimplePoint2D[] sort(SimplePoint2D[] arr) {
        int h = 1;
        while (h < arr.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (lessByYcoord(arr[j], arr[j - 1])) {
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

    private boolean lessByYcoord(SimplePoint2D v, SimplePoint2D w) {
        return v.getY() <= w.getY();
    }
}
