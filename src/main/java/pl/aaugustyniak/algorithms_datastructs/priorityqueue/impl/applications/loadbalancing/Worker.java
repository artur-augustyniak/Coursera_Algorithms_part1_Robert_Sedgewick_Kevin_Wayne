package pl.aaugustyniak.algorithms_datastructs.priorityqueue.impl.applications.loadbalancing;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author aaugustyniak
 */
public class Worker implements Comparable<Worker> {

    private final Queue<Job> jobs;

    public Worker() {
        jobs = new PriorityQueue<>();
    }

    public void addJob(Job j) {
        jobs.add(j);
    }

    public int getLoad() {
        int load = 0;
        for (Job job : jobs) {
            load += job.cost;
        }
        return load;
    }

    @Override
    public int compareTo(Worker that) {
        if (this.getLoad() > that.getLoad()) {
            return 1;
        } else if (this.getLoad() < that.getLoad()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String ret = "Worker: \n"
                + jobs
                + "\n"
                + "Total worker load: " + getLoad();
        return ret;
    }

}
