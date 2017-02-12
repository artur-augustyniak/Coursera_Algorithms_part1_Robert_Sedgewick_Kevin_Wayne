package pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author artur
 */
public class BottomUPMergeSorterTest {

    @Test
    public void testMerge() {
        Integer[] halveSorted = {3, 5, 7, 9, 1, 2, 3, 4};
        Integer[] expected = {1, 2, 3, 3, 4, 5, 7, 9};
        BottomUPMergeSorter<Integer> s = new BottomUPMergeSorter<>();
        s.sort(halveSorted);
        s.fakeInPlaceMerge(0, 3, 7);
        System.out.println(Arrays.toString(halveSorted));
        assertArrayEquals(expected, halveSorted);
    }

    @Test
    public void testSort() {
//        Integer[] unsorted = {3, 5, 7, 9, 1, 2, 3, 4};
//        Integer[] expected = {1, 2, 3, 3, 4, 5, 7, 9};

        Integer[] unsorted = {3, 7, 3, 3, 54, 456, -1, 2};
        Integer[] expected = {-1, 2, 3, 3, 3, 7, 54, 456};
        BottomUPMergeSorter<Integer> s = new BottomUPMergeSorter<>();
        s.sort(unsorted);
        System.out.println(Arrays.toString(unsorted));
        assertArrayEquals(expected, unsorted);
    }

}
