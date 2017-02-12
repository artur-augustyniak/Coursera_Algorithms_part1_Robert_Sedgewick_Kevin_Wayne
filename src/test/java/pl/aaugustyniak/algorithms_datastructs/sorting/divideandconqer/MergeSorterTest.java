package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author artur
 */
public class MergeSorterTest {

    @Test
    public void testMerge() {
        Integer[] halveSorted = {3, 5, 7, 9, 1, 2, 3, 4};
        Integer[] expected = {1, 2, 3, 3, 4, 5, 7, 9};
        MergeSorter<Integer> s = new MergeSorter<>();
        s.sort(halveSorted);
        s.fakeInPlaceMerge(0, 3, 7, 0);
        assertArrayEquals(expected, halveSorted);
    }

    @Test
    public void testMergeSorter() {
        Random r = new Random(42);
        int n = 1000000;
                //Math.abs(r.nextInt(120));

        MergeSorter<Integer> s = new MergeSorter<>();
        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];

        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        long startTime = System.currentTimeMillis();
        Integer[] actual = s.sort(input);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Mergesorter time: " + time);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testClassicMergeSorter() {
        Random r = new Random(42);
        int n = 1000000;
                //Math.abs(r.nextInt(120));

        ClassicMergeSorter<Integer> s = new ClassicMergeSorter<>();
        Integer[] input = new Integer[n];
        Integer[] expected = new Integer[n];

        for (int i = 0; i < n; i++) {
            input[i] = expected[i] = r.nextInt(n);
        }
        Arrays.sort(expected);
        long startTime = System.currentTimeMillis();
        Integer[] actual = s.sort(input);
        long stopTime = System.currentTimeMillis();
        long time = (stopTime - startTime) / 1000;
        System.out.println("Classic Mergesorter time: " + time);
        assertArrayEquals(expected, actual);
    }

}
