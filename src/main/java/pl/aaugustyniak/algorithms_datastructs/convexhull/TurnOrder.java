package pl.aaugustyniak.algorithms_datastructs.convexhull;

/**
 *
 * @author artur
 */
public enum TurnOrder {

    CLOCKWISE(-1),
    COUNTER_CLOCKWISE(1),
    COLLINEAR(0);

    private int signedArea;

    private TurnOrder(int value) {
        this.signedArea = value;
    }

}
