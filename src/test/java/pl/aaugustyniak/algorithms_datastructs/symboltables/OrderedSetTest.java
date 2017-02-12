package pl.aaugustyniak.algorithms_datastructs.symboltables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.OrderedBSTSet;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.OrderedTreeSet;

/**
 *
 * @author aaugustyniak
 */
public class OrderedSetTest {

    OrderedSet<String> instance;
    OrderedSet<String> thatSet;
    OrderedSet<String> outcomeSet;
    OrderedSet<Integer> intInstance;
    List<String> inputStrings;

    @Test
    public void testTreeSet() {
        instance = new OrderedTreeSet<>();
        thatSet = new OrderedTreeSet<>();
        outcomeSet = new OrderedTreeSet<>();
        intInstance = new OrderedTreeSet<>();
        testsCall();
    }

    @Test
    public void testBSTSet() {
        instance = new OrderedBSTSet<>();
        thatSet = new OrderedBSTSet<>();
        outcomeSet = new OrderedBSTSet<>();
        intInstance = new OrderedBSTSet<>();
        testsCall();
    }

    private void testsCall() {
        inputStrings = new ArrayList<>();
        inputStrings.add("www.cs.princeton.edu");
        inputStrings.add("www.cs.princeton.edu");    // overwrite old value
        inputStrings.add("www.princeton.edu");
        inputStrings.add("www.math.princeton.edu");
        inputStrings.add("www.yale.edu");
        inputStrings.add("www.amazon.com");
        inputStrings.add("www.simpsons.com");
        inputStrings.add("www.stanford.edu");
        inputStrings.add("www.google.com");
        inputStrings.add("alien");
        inputStrings.add("www.ibm.com");
        inputStrings.add("www.apple.com");
        inputStrings.add("www.slashdot.com");
        inputStrings.add("www.whitehouse.gov");
        inputStrings.add("www.espn.com");
        inputStrings.add("www.snopes.com");
        inputStrings.add("www.movies.com");
        inputStrings.add("www.cnn.com");
        inputStrings.add("www.iitb.ac.in");
        Collections.shuffle(inputStrings);
        for (String string : inputStrings) {
            instance.add(string);
        }
        basiClient();
        testRank();
        testSelect();
        testSizeInRange();
        testOneDimmensionalRangeSearch();
        testUnion();
        testIntersect();
        testEqual();
    }

    private void basiClient() {

        instance.delete("alien");
        assertFalse(instance.contains("alien"));
        assertTrue(instance.contains("www.cs.princeton.edu"));
        assertFalse(instance.contains("www.harvardsucks.com"));
        assertTrue(instance.contains("www.simpsons.com"));

        assertEquals("www.simpsons.com", instance.ceil("www.simpsonr.com"));
        assertEquals("www.simpsons.com", instance.ceil("www.simpsons.com"));
        assertEquals("www.slashdot.com", instance.ceil("www.simpsont.com"));

        assertEquals("www.princeton.edu", instance.floor("www.simpsonr.com"));
        assertEquals("www.simpsons.com", instance.floor("www.simpsons.com"));
        assertEquals("www.simpsons.com", instance.floor("www.simpsont.com"));

        List<String> expectedStrings = new ArrayList<>(new HashSet(inputStrings));
        Collections.sort(expectedStrings, String.CASE_INSENSITIVE_ORDER);

        List<String> actualStrings = new ArrayList<>();
        // lexicographic order
        for (String s : instance) {
            actualStrings.add(s);
        }
        expectedStrings.remove("alien");
        assertEquals(expectedStrings.size(), instance.size());
        assertEquals(expectedStrings, actualStrings);
        assertEquals(expectedStrings.get(0), instance.min());
        assertEquals(expectedStrings.get(expectedStrings.size() - 1), instance.max());
        instance.deleteMin();
        assertEquals(expectedStrings.get(1), instance.min());
        instance.deleteMax();
        assertEquals(expectedStrings.get(expectedStrings.size() - 2), instance.max());
    }

    public void testRank() {
        intInstance.add(4);
        intInstance.add(8);
        intInstance.add(2);
        intInstance.add(6);
        intInstance.add(1);
        intInstance.add(9);

        Integer expRank = 3;
        Integer actRank = intInstance.rank(5);
        assertEquals(expRank, actRank);
    }

    public void testSelect() {
        assertEquals(new Integer(6), intInstance.select(3));
        assertEquals(new Integer(1), intInstance.select(0));
        assertEquals(new Integer(2), intInstance.select(1));
    }

    public void testSizeInRange() {
        assertEquals(1, intInstance.sizeInRange(0, 1));
        assertEquals(2, intInstance.sizeInRange(0, 2));
        assertEquals(2, intInstance.sizeInRange(0, 3));
        assertEquals(6, intInstance.sizeInRange(0, 12));
    }

    public void testOneDimmensionalRangeSearch() {

        instance.clear();
        instance.add("B");
        instance.add("D");
        instance.add("A");
        instance.add("I");
        instance.add("H");
        instance.add("F");
        instance.add("P");
        assertEquals(2, instance.sizeInRange("G", "K"));
        List<String> keys = new ArrayList<>(instance.keysInRange("G", "K"));
        List<String> expectedKeysInRange = new ArrayList<>();
        expectedKeysInRange.add("H");
        expectedKeysInRange.add("I");
        assertEquals(expectedKeysInRange, keys);
    }

    public void testUnion() {
        thatSet.clear();
        thatSet.add("Z");
        thatSet.add("D");
        thatSet.add("A");
        thatSet.add("I");
        outcomeSet.clear();
        outcomeSet.add("B");
        outcomeSet.add("D");
        outcomeSet.add("A");
        outcomeSet.add("I");
        outcomeSet.add("H");
        outcomeSet.add("F");
        outcomeSet.add("P");
        outcomeSet.add("Z");
        List<String> expected = new ArrayList<>(outcomeSet.keys());
        List<String> actual = new ArrayList<>(instance.union(thatSet).keys());
        assertEquals(expected, actual);
    }

    private void testIntersect() {
        thatSet.clear();
        thatSet.add("Z");
        thatSet.add("D");
        thatSet.add("A");
        thatSet.add("I");
        outcomeSet.clear();
        outcomeSet.add("D");
        outcomeSet.add("A");
        outcomeSet.add("I");
        List<String> expected = new ArrayList<>(outcomeSet.keys());
        List<String> actual = new ArrayList<>(instance.intersects(thatSet).keys());
        assertEquals(expected, actual);
    }

    private void testEqual() {
        assertTrue(instance.setEquals(instance));
        assertFalse(instance.setEquals(outcomeSet));
        thatSet.clear();
        for (String string : instance) {
            thatSet.add(string);
        }
        assertTrue(instance.setEquals(thatSet));
    }
}
