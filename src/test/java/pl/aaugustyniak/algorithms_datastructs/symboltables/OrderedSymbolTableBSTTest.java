package pl.aaugustyniak.algorithms_datastructs.symboltables;

import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.RedBlackBST;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.PrioResizingArrayQueue;
import pl.aaugustyniak.algorithms_datastructs.convexhull.SimplePoint2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.OrderedSymbolTableBST;
import static org.junit.Assert.*;

/**
 *
 * @author aaugustyniak
 */
public class OrderedSymbolTableBSTTest {

    OrderedSymbolTableBST<Integer, String> st;
    Random r;
    int nodesCount = 40;
    Map<Integer, String> values;

    @Before
    public void setUp() {
        st = new OrderedSymbolTableBST<>();
        values = new HashMap<>();
        r = new Random(42);
        for (int i = 0; i < nodesCount; i++) {
            int key = r.nextInt(100);
            String val = new Integer(key).toString();
            values.put(key, val);
            st.put(key, val);
        }
    }

    @Test
    public void testMin() {
        Integer expMinKey = Collections.min(values.keySet());
        Integer actMinKey = st.min();
        assertEquals(expMinKey, actMinKey);

    }

    @Test
    public void testMax() {
        Integer expMaxKey = Collections.max(values.keySet());
        Integer actMaxKey = st.max();
        assertEquals(expMaxKey, actMaxKey);
    }

    @Test
    public void testFloor() {
        Integer expFloorKey = 0;
        Integer actFloorKey = st.floor(1);
        assertEquals(expFloorKey, actFloorKey);
    }

    @Test
    public void testCeil() {
        Integer expCeilKey = 2;
        Integer actCeilKey = st.ceil(1);
        assertEquals(expCeilKey, actCeilKey);
    }

    @Test
    public void testRank() {
        Integer expRank = 3;
        Integer actRank = st.rank(9);
        assertEquals(expRank, actRank);
    }

    @Test
    public void testSelect() {
        assertEquals(new Integer(9), st.select(3));
        assertEquals(new Integer(0), st.select(0));
        assertEquals(new Integer(2), st.select(1));
    }

    @Test
    public void testDeleteMin() {
        Integer minKey = Collections.min(values.keySet());
        st.deleteMin();
        assertNull(st.get(minKey));
    }

    @Test
    public void testDeleteMax() {
        Integer maxKey = Collections.max(values.keySet());
        st.deleteMax();
        assertNull(st.get(maxKey));
    }

    @Test
    public void testSizeInRange() {
        assertEquals(1, st.sizeInRange(0, 1));
        assertEquals(2, st.sizeInRange(0, 2));
        assertEquals(2, st.sizeInRange(0, 3));
        assertEquals(5, st.sizeInRange(0, 12));
    }

    @Test
    public void testOneDimmensionalRangeSearch() {
        st.put(3, "B");
        st.put(4, "D");
        st.put(1, "A");
        st.put(9, "I");
        st.put(8, "H");
        st.put(6, "F");
        st.put(16, "P");
        assertEquals(2, st.sizeInRange(7, 11));
        List<Integer> keys = (List<Integer>) st.keysInRange(7, 11);
        List<Integer> expectedKeysInRange = new ArrayList<>();
        expectedKeysInRange.add(8);
        expectedKeysInRange.add(9);
        assertEquals(expectedKeysInRange, keys);
    }

    @Test
    public void testOrthogonalLineSweepScan() {
        List<SimplePoint2D> lines = new ArrayList<>();
        lines.add(new SimplePoint2D(0.0, 1.0));
        lines.add(new SimplePoint2D(9.0, 1.0));
        lines.add(new SimplePoint2D(2.0, 2.0));
        lines.add(new SimplePoint2D(5.0, 2.0));
        lines.add(new SimplePoint2D(3.0, 3.0));
        lines.add(new SimplePoint2D(5.0, 3.0));
        lines.add(new SimplePoint2D(8.0, 0.0));
        lines.add(new SimplePoint2D(8.0, 6.0));

        PrioResizingArrayQueue<SimplePoint2D> xSortedPoints = new PrioResizingArrayQueue<>();
        BTree<Double, Double> tree = new RedBlackBST<>();
        OrderedSymbolTable<Double, Double> yCoords = new OrderedSymbolTableBST<>(tree);

        for (SimplePoint2D p : lines) {
            xSortedPoints.enqueue(p);
        }

        SimplePoint2D last = xSortedPoints.dequeue();

        List<Double> actualIntersectingY = new ArrayList<>();
        List<Double> expectedIntersectingY = new ArrayList<>();
        expectedIntersectingY.add(2.0);
        expectedIntersectingY.add(3.0);
        while (!xSortedPoints.isEmpty()) {
            SimplePoint2D p = xSortedPoints.dequeue();
            if (p.getX() == last.getX()) {
                Iterable<Double> kr;
                if (p.getY() < last.getY()) {
                    kr = yCoords.keysInRange(p.getY(), last.getY());
                } else {
                    kr = yCoords.keysInRange(last.getY(), p.getY());
                }
                for (Double intersectX : kr) {
                    actualIntersectingY.add(intersectX);
                }
            } else {
                insertOrRemoveYFor(last, yCoords);
            }
            last = p;
        }
        assertEquals(expectedIntersectingY, actualIntersectingY);
    }

    private void insertOrRemoveYFor(SimplePoint2D p, OrderedSymbolTable<Double, Double> t) {

        Double xForY = t.get(p.getY());
        if (xForY == null) {
            t.put(p.getY(), p.getX());
        } else {
            t.delete(p.getY());
        }
    }
}
