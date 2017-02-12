package pl.aaugustyniak.algorithms_datastructs.symboltables;

import org.junit.Assert;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.btrees.BTree;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.IterativeBST;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.RedBlackBST;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.SimpleRedBlackBST;
import pl.aaugustyniak.algorithms_datastructs.btrees.impl.SplayBST;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.HashSymbolTableLinearProbing;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.HashSymbolTableSeparateChaining;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.SymbolTableBST;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.SymbolTableBinarySearchOrderedArray;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.SymbolTableHaskMap;
import pl.aaugustyniak.algorithms_datastructs.symboltables.impl.SymbolTableSequentialSearch;

/**
 *
 * @author aaugustyniak
 */
public class SymbolTableTest {

    @Test
    public void testJavaHashMapSymbolTable() {
        SymbolTable<String, Integer> st = new SymbolTableHaskMap<>();
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testSequentialSymbolTable() {
        SymbolTable<String, Integer> st = new SymbolTableSequentialSearch<>();
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testBinarySearchOrderedArraySymbolTable() {
        SymbolTable<String, Integer> st = new SymbolTableBinarySearchOrderedArray<>(201);
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testBSTSymbolTable() {
        SymbolTable<String, Integer> st = new SymbolTableBST<>();
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testBSTIterativeSymbolTable() {
        BTree<String, Integer> tree = new IterativeBST<>();

        SymbolTable<String, Integer> st = new SymbolTableBST<>(tree);
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testSimpleRedBlackSymbolTable() {
        BTree<String, Integer> tree = new SimpleRedBlackBST<>();
        SymbolTable<String, Integer> st = new SymbolTableBST<>(tree);
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testRedBlackSymbolTable() {
        BTree<String, Integer> tree = new RedBlackBST<>();
        SymbolTable<String, Integer> st = new SymbolTableBST<>(tree);
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testSplayTreeSymbolTable() {
        BTree<String, Integer> tree = new SplayBST<>();
        SymbolTable<String, Integer> st = new SymbolTableBST<>(tree);
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testHashSeparateChainingSymbolTable() {
        SymbolTable<String, Integer> st = new HashSymbolTableSeparateChaining<>();
        proceedFrequencyCounterTest(st);
    }

    @Test
    public void testHashLinearProbingSymbolTable() {
        SymbolTable<String, Integer> st = new HashSymbolTableLinearProbing<>();
        proceedFrequencyCounterTest(st);
    }

    private void proceedFrequencyCounterTest(SymbolTable<String, Integer> st) {
        int wordsCount = 200;
        int startFrom = 90;
        int hiFrequent = 125;
        int hiFrequentCount = 14;
        boolean dupTrig = false;

        String[] input = new String[wordsCount];

        int j = hiFrequentCount;
        for (int i = startFrom; i < wordsCount + startFrom; i++) {
            int idx = i - startFrom;
            if (i == hiFrequent && j > 0 || dupTrig) {
                dupTrig = true;
                input[idx] = new Integer(hiFrequent).toString();
                j--;
                if (j == 0) {
                    dupTrig = false;
                }
                continue;
            }
            input[idx] = new Integer(i).toString();
        }

        int distinct = 0, words = 0;
        int minlen = 3;
        for (String key : input) {
            if (key.length() < minlen) {
                continue;
            }
            words++;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
                distinct++;
            }
        }
        // find a key with the highest frequency count
        String max = "";
        st.put(max, 0);
        for (String word : st.keys()) {
            if (st.get(word) > st.get(max)) {
                max = word;
            }
        }

        Assert.assertEquals("125", max);
        Assert.assertEquals(14, (int) st.get(max));
        Assert.assertEquals(177, distinct);
        Assert.assertEquals(190, words);
    }
}
