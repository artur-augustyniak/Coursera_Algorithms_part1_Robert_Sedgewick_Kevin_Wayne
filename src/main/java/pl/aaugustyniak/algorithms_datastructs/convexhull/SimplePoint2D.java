package pl.aaugustyniak.algorithms_datastructs.convexhull;

/**
 * Wsp biegunowe ?
 *
 * @author artur
 */
public class SimplePoint2D implements Comparable<SimplePoint2D> {

    private double x;
    private double y;

    public SimplePoint2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int compareTo(SimplePoint2D o) {
        if (o == null) {
            return 1;
        }
        if (this.x > o.x) {
            return 1;
        }

        if (this.x < o.x) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "x: " + x + " y: " + y + "\n";
    }

}
