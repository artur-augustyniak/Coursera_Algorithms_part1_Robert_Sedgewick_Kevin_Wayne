package pl.aaugustyniak.algorithms_datastructs.btrees.bst;

import pl.aaugustyniak.algorithms_datastructs.btrees.impl.BST;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.BtreeNode;
import pl.aaugustyniak.algorithms_datastructs.btrees.NodeVisitor;

/**
 *
 * @author aaugustyniak
 */
public class BSTTest {

    protected class TestVisitor implements NodeVisitor<Integer, String> {

        protected final List<Integer> collectedKeys = new ArrayList<>();

        @Override
        public void visit(BtreeNode<Integer, String> node) {
            System.out.printf("Node key - %s: value %s\n", node.getKey(), node.getValue());
            collectedKeys.add(node.getKey());
        }

        public List<Integer> getCollectedKeys() {
            return collectedKeys;
        }
    }
    BTree<Integer, String> tree;
    Random r;
    int nodesCount = 40;
    Map<Integer, String> values;

    @Before
    public void setUp() {
        tree = new BST<>();
        initValues();

    }

    protected void initValues() {
        values = new HashMap<>();
        r = new Random(42);
        for (int i = 0; i < nodesCount; i++) {
            int val = r.nextInt(100);
            values.put(val, new Integer(val).toString());
        }
    }

    @Test
    public void testFloor() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }
        List sortedKeys = new ArrayList(values.keySet());
        Collections.sort(sortedKeys);
        Assert.assertEquals(null, tree.floor(-1));
        Assert.assertEquals(new Integer(0), tree.floor(0));
        Assert.assertEquals(new Integer(0), tree.floor(1));
        Assert.assertEquals(new Integer(2), tree.floor(2));
        Assert.assertEquals(new Integer(5), tree.floor(5));
        Assert.assertEquals(new Integer(9), tree.floor(11));
        Assert.assertEquals(new Integer(93), tree.floor(93));
        Assert.assertEquals(new Integer(93), tree.floor(Integer.MAX_VALUE));
    }

    @Test
    public void testCeil() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }
        List sortedKeys = new ArrayList(values.keySet());
        Collections.sort(sortedKeys);
        Assert.assertEquals(null, tree.ceil(94));
        Assert.assertEquals(new Integer(0), tree.ceil(0));
        Assert.assertEquals(new Integer(2), tree.ceil(1));
        Assert.assertEquals(new Integer(2), tree.ceil(2));
        Assert.assertEquals(new Integer(5), tree.ceil(5));
        Assert.assertEquals(new Integer(12), tree.ceil(11));
        Assert.assertEquals(new Integer(93), tree.ceil(93));
        Assert.assertEquals(null, tree.ceil(Integer.MAX_VALUE));
    }

    @Test
    public void testRank() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }
        Assert.assertEquals(3, tree.rank(9));
        Assert.assertEquals(1, tree.rank(2));
        Assert.assertEquals(4, tree.rank(12));
    }

    @Test
    public void testSearch() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            Assert.assertEquals(string, tree.search(integer));
        }

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
    public void testGetMinMaxKey() {
        for (Map.Entry<Integer, String> entry : values.entrySet()) {
            Integer integer = entry.getKey();
            String string = entry.getValue();
            tree.insert(integer, string);
        }
        Integer minKey = Collections.min(values.keySet());
        Integer maxKey = Collections.max(values.keySet());
        Assert.assertEquals(minKey, tree.minKey());
        Assert.assertEquals(maxKey, tree.maxKey());
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
        expectedOrder.add(10);
        expectedOrder.add(8);
        expectedOrder.add(9);
        expectedOrder.add(15);
        expectedOrder.add(11);
        expectedOrder.add(12);
        TestVisitor v = new TestVisitor();
        tree.preOrder(v);
        System.out.println("END FULL PRE-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.preOrder(v2, 15);
        System.out.println("END PARTIAL PRE-ORDER");
        Assert.assertEquals(expectedOrder.subList(3, 6), v2.getCollectedKeys());
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
        TestVisitor v2 = new TestVisitor();
        tree.inOrder(v2, 15);
        System.out.println("END PARTIAL IN-ORDER");
        Integer[] expPartial = {11, 12, 15};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
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
        expectedOrder.add(9);
        expectedOrder.add(8);
        expectedOrder.add(12);
        expectedOrder.add(11);
        expectedOrder.add(15);
        expectedOrder.add(10);
        TestVisitor v = new TestVisitor();
        tree.postOrder(v);
        System.out.println("END FULL POST-ORDER");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.postOrder(v2, 15);
        System.out.println("END PARTIAL POST-ORDER");
        Integer[] expPartial = {12, 11, 15};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
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
        expectedOrder.add(10);
        expectedOrder.add(8);
        expectedOrder.add(15);
        expectedOrder.add(8);
        expectedOrder.add(9);
        expectedOrder.add(9);
        expectedOrder.add(15);
        expectedOrder.add(11);
        expectedOrder.add(11);
        expectedOrder.add(12);
        expectedOrder.add(12);

        TestVisitor v = new TestVisitor();
        tree.walkBF(v);
        System.out.println("END FULL BREADTH WALK");
        Assert.assertEquals(expectedOrder, v.getCollectedKeys());
        TestVisitor v2 = new TestVisitor();
        tree.walkBF(v2, 15);
        System.out.println("END PARTIAL BREADTH WALK");
        Integer[] expPartial = {15, 11, 11, 12, 12};
        Assert.assertEquals(Arrays.asList(expPartial), v2.getCollectedKeys());
    }
}
