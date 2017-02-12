package pl.aaugustyniak.algorithms_datastructs.sorting;

import java.io.File;
import java.util.Arrays;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BubbleSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.HeapSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.InsertionSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.SelectionSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.ShellSorter;
import pl.aaugustyniak.algorithms_datastructs.comparables.SimpleDate;

/**
 *
 * @author artur
 */
public class SorterTest {

    @Test
    public void testBubbleSorter() {
        System.out.println("BubbleSorter test");
        testSortIntegers(new BubbleSorter<Integer>());
        testSortDoubles(new BubbleSorter<Double>());
        testSortStrings(new BubbleSorter<String>());
        testSortFiles(new BubbleSorter<File>());
        testSortCustom(new BubbleSorter<SimpleDate>());
    }

    @Test
    public void testSelectionSorter() {
        System.out.println("SelectionSorter test");
        testSortIntegers(new SelectionSorter<Integer>());
        testSortDoubles(new SelectionSorter<Double>());
        testSortStrings(new SelectionSorter<String>());
        testSortFiles(new SelectionSorter<File>());
        testSortCustom(new SelectionSorter<SimpleDate>());
    }

    @Test
    public void testInsertionSorter() {
        System.out.println("InsertionSorter test");
        testSortIntegers(new InsertionSorter<Integer>());
        testSortDoubles(new InsertionSorter<Double>());
        testSortStrings(new InsertionSorter<String>());
        testSortFiles(new InsertionSorter<File>());
        testSortCustom(new InsertionSorter<SimpleDate>());
    }

    @Test
    public void testShellSorter() {
        System.out.println("ShellSorter test");
        testSortIntegers(new ShellSorter<Integer>());
        testSortDoubles(new ShellSorter<Double>());
        testSortStrings(new ShellSorter<String>());
        testSortFiles(new ShellSorter<File>());
        testSortCustom(new ShellSorter<SimpleDate>());
    }

    @Test
    public void testHeapSorter() {
        System.out.println("HeapSorter test");
        testSortIntegers(new HeapSorter<Integer>());
        testSortDoubles(new HeapSorter<Double>());
        testSortStrings(new HeapSorter<String>());
        testSortFiles(new HeapSorter<File>());
        testSortCustom(new HeapSorter<SimpleDate>());
    }

    private void testSortIntegers(VerboseSorter<Integer> sorter) {
        Integer[] arr = {5, 6, 3, 2, 4, 1, 9};
//        System.out.println(sorter.isSorted(arr));
        System.out.println(Arrays.toString(sorter.sort(arr)));
//        System.out.println(sorter + " time ms " + sorter.getMicrotime());
        System.out.println(sorter + " comparisions  " + sorter.getCost());
//        System.out.println(sorter.isSorted(arr));
    }

    private void testSortDoubles(VerboseSorter<Double> sorter) {
        Double[] arr = {5.5, 6.1, 3.1, 0.00001, 4.1, 4.099, 9.1};
//        System.out.println(sorter.isSorted(arr));
        System.out.println(Arrays.toString(sorter.sort(arr)));
//        System.out.println(sorter + " time ms " + sorter.getMicrotime());
        System.out.println(sorter + " comparisions  " + sorter.getCost());
//        System.out.println(sorter.isSorted(arr));
    }

    private void testSortStrings(VerboseSorter<String> sorter) {
        String[] arr = {"Anna", "Antena", "Beduin", "nitka", "ńitka", "Ątek", "test"};
//        System.out.println(sorter.isSorted(arr));
        System.out.println(Arrays.toString(sorter.sort(arr)));
//        System.out.println(sorter + " time ms " + sorter.getMicrotime());
        System.out.println(sorter + " comparisions  " + sorter.getCost());
//        System.out.println(sorter.isSorted(arr));
    }

    private void testSortFiles(VerboseSorter<File> sorter) {
        File dir = new File(".");
        File[] arr = dir.listFiles();
//        System.out.println(sorter.isSorted(arr));
        System.out.println(Arrays.toString(sorter.sort(arr)));
//        System.out.println(sorter + " time ms " + sorter.getMicrotime());
        System.out.println(sorter + " comparisions  " + sorter.getCost());
//        System.out.println(sorter.isSorted(arr));
    }

    private void testSortCustom(VerboseSorter<SimpleDate> sorter) {

        SimpleDate[] arr = {new SimpleDate(2013, 23, 2), new SimpleDate(2013, 12, 1)};
//        System.out.println(sorter.isSorted(arr));
        System.out.println(Arrays.toString(sorter.sort(arr)));
//        System.out.println(sorter + " time ms " + sorter.getMicrotime());
        System.out.println(sorter + " comparisions  " + sorter.getCost());
//        System.out.println(sorter.isSorted(arr));
    }

}
