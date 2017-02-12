package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.applications.loadbalancing;

import java.util.Random;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.PrioSimpleQueue;
import pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.PrioArrayHeapQueue;
import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.QuickSorter;
import pl.aaugustyniak.algorithms_datastructs.sorting.impl.BaseSorter;

/**
 *
 * @author aaugustyniak
 */
public class BalancerLPT {

    /**
     * @see http://kkapd.f11.com.pl/zsw/algorytmy_listowe.htm
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random(42);

        int numberOfMachines = 10;
        int numberOfJobs = 20;
        Job[] jobs = new Job[numberOfJobs];

        BaseSorter<Job> sorter = new QuickSorter<>();
        PrioSimpleQueue<Worker> pq = new PrioArrayHeapQueue<>();

        System.out.println("Jobs count: " + numberOfJobs);
        System.out.println("----------------------------");
        for (int i = 0; i < numberOfJobs; i++) {
            String name = Integer.toString(i);
            int cost = r.nextInt(100);
            jobs[i] = new Job(name, cost);
            System.out.println(jobs[i]);
        }

       // jobs = sorter.sort(jobs);

        for (int i = 0; i < numberOfMachines; i++) {
            pq.enqueue(new Worker());
        }

        for (int j = numberOfJobs - 1; j >= 0; j--) {
            Worker leastBusy = pq.dequeueMin();
            leastBusy.addJob(jobs[j]);
            pq.enqueue(leastBusy);
        }

        System.out.println("Workers in least busy order LPT:");
        System.out.println("----------------------------");
        while (!pq.isEmpty()) {
            System.out.println(pq.dequeueMin());
            System.out.println("----------------------------");
        }
    }

}
