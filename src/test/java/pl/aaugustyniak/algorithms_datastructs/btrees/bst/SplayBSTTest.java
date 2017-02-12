package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.SplayBST;

/**
 *
 * @author aaugustyniak
 */
public class SplayBSTTest extends BSTTest {

    @Before
    @Override
    public void setUp() {
        tree = new SplayBST<>();
        initValues();
    }

    @Test
    public void testRemoveAndSize() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }

        int removeCounter = values.size();
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            tree.remove(integer);
            removeCounter--;
            Assert.assertNull(tree.search(integer));
            Assert.assertEquals(removeCounter, tree.size());
        }

    }

    @Test
    public void testPreOrderTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();

        expectedOrder.add(9);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(11);
        expectedOrder.add(12);
        expectedOrder.add(15);
        TestVisitor v = new TestVisitor();
        tree.preOrder(v);
        System.out.println("END FULL PRE-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        System.out.println("Partial PRE-ORDER unpredictable");
    }

    @Test
    public void testInOrderTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(8);
        expectedOrder.add(9);
        expectedOrder.add(10);
        expectedOrder.add(11);
        expectedOrder.add(12);
        expectedOrder.add(15);
        TestVisitor v = new TestVisitor();
        tree.inOrder(v);
        System.out.println("END FULL IN-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        System.out.println("Partial IN-ORDER unpredictable");
    }

    @Test
    public void testPostOrderTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(8);
        expectedOrder.add(15);
        expectedOrder.add(12);
        expectedOrder.add(11);
        expectedOrder.add(10);
        expectedOrder.add(9);
        TestVisitor v = new TestVisitor();
        tree.postOrder(v);
        System.out.println("END FULL POST-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        System.out.println("Partial POST-ORDER unpredictable");
    }

    @Test
    public void testWalkBreadthTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(9);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(11);
        expectedOrder.add(11);
        expectedOrder.add(12);
        expectedOrder.add(12);
        expectedOrder.add(15);
        expectedOrder.add(15);
        TestVisitor v = new TestVisitor();
        tree.walkBF(v);
        System.out.println("END FULL BREADTH WALK");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        System.out.println("Partial BREADTH WALK unpredictable");
    }
}
