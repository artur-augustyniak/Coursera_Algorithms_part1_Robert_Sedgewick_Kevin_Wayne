package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.RedBlackBST;

/**
 *
 * @author artur
 */
public class RedBlackBSTTest extends BSTTest {

    @Before
    @Override
    public void setUp() {
        tree = new RedBlackBST<>();
        initValues();
    }

    @Test
    public void testRedLeafDeletion() {
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(11, "root");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        tree.remove(12);

        Assert.assertNull(tree.search(12));
        Assert.assertEquals(5, tree.size());
    }

    @Test
    public void testBlackLeafDeletion() {
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(11, "root");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        tree.remove(8);
        tree.remove(10);

        Assert.assertNull(tree.search(8));
        Assert.assertNull(tree.search(10));
        Assert.assertEquals(4, tree.size());
    }

    @Test
    public void testBlackInnerDeletion() {
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(11, "root");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        tree.remove(15);

        Assert.assertNull(tree.search(15));
        Assert.assertEquals(5, tree.size());
    }

    @Test
    public void testRedInnerDeletion() {
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(11, "root");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        tree.remove(9);

        Assert.assertNull(tree.search(9));
        Assert.assertEquals(5, tree.size());
    }

    @Test
    @Override
    public void testInOrderTraversal() {
        tree.insert(10, "10");
        tree.insert(15, "15");
        tree.insert(11, "root");
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
        TestVisitor v = new BSTTest.TestVisitor();
        tree.inOrder(v);
        System.out.println("END FULL IN-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new BSTTest.TestVisitor();
        tree.inOrder(v2, 15);
        System.out.println("END PARTIAL IN-ORDER");
        Integer[] expPartial = {12, 15};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
    }

    @Test
    @Override
    public void testPreOrderTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(11);
        expectedOrder.add(9);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(15);
        expectedOrder.add(12);
        TestVisitor v = new TestVisitor();
        tree.preOrder(v);
        System.out.println("END FULL PRE-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.preOrder(v2, 15);
        System.out.println("END PARTIAL PRE-ORDER");
        Assert.assertEquals(expectedOrder.subList(4, 6), v2.getCollectedKeys());
    }

    @Test
    @Override
    public void testPostOrderTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(9);
        expectedOrder.add(12);
        expectedOrder.add(15);
        expectedOrder.add(11);
        TestVisitor v = new TestVisitor();
        tree.postOrder(v);
        System.out.println("END FULL POST-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.postOrder(v2, 15);
        System.out.println("END PARTIAL POST-ORDER");
        Integer[] expPartial = {12, 15};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
    }

    @Test
    @Override
    public void testWalkBreadthTraversal() {
        tree.insert(10, "root");
        tree.insert(15, "15");
        tree.insert(11, "11");
        tree.insert(12, "12");
        tree.insert(8, "8");
        tree.insert(9, "9");
        List<Integer> expectedOrder = new ArrayList<>();
        expectedOrder.add(11);
        expectedOrder.add(9);
        expectedOrder.add(15);
        expectedOrder.add(9);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(8);
        expectedOrder.add(10);
        expectedOrder.add(15);
        expectedOrder.add(12);
        expectedOrder.add(12);

        TestVisitor v = new TestVisitor();
        tree.walkBF(v);
        System.out.println("END FULL BREADTH WALK");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.walkBF(v2, 15);
        System.out.println("END PARTIAL BREADTH WALK");
        Integer[] expPartial = {15, 12, 12};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
    }

}
