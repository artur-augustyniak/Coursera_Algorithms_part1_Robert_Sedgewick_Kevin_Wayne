package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.applications;

import java.util.ArrayList;
import java.util.Random;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.PrioArrayHeapQueue;
import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.QuickSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author aaugustyniak
 */
public class MergeSortedStreams {

    private static final Random r = new Random(42);
    private static final int streamsCount = 23;
    private static final int maxStreamLen = 2023;
    private static final int maxStreamElementVal = Integer.MAX_VALUE;
    private static final BaseSorter<Integer> sorter = new QuickSorter<>();
    private static final PrioSimpleQueue<Integer> pq = new PrioArrayHeapQueue<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Integer[]> streams = generateStreams();
        ArrayList<Integer> mergedStreams = merge(streams);
        //System.out.println(mergedStreams);
        boolean isSorted = sorter.isSorted(mergedStreams.toArray(new Integer[mergedStreams.size()]));
        System.out.println(isSorted);
        
    }

    private static ArrayList<Integer> merge(ArrayList<Integer[]> streams) {
        sortStrams(streams);
        ArrayList<Integer> mergedStreams = new ArrayList<>((streamsCount * maxStreamLen) / 2);
        for (Integer[] stream : streams) {
            for (Integer integer : stream) {
                pq.enqueue(integer);
            }
        }
        while (!pq.isEmpty()) {
            mergedStreams.add(pq.dequeueMin());
        }
        return mergedStreams;
    }

    private static void sortStrams(ArrayList<Integer[]> streams) {
        for (Integer[] stream : streams) {
            sorter.sort(stream);
            
        }
    }

    private static ArrayList<Integer[]> generateStreams() {
        ArrayList<Integer[]> streams = new ArrayList<>();
        for (int i = 0; i < streamsCount; i++) {
            streams.add(generateStream());
        }
        return streams;
    }

    private static Integer[] generateStream() {
        int sLength = r.nextInt(maxStreamLen);
        Integer[] stream = new Integer[sLength];
        for (int i = 0; i < sLength; i++) {
            stream[i] = r.nextInt(maxStreamElementVal);
        }
        return stream;
    }

}
