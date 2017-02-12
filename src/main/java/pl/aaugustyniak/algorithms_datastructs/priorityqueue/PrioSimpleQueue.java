/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.priorityqueue;

import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleQueue;

/**
 *
 * @author aaugustyniak
 * @param <T>
 */
public interface PrioSimpleQueue<T extends Comparable> extends SimpleQueue<T> {

    /**
     * Get max item from queue total order
     *
     * @return T
     */
    public T dequeueMax();

    /**
     * Get m item from queue total order
     *
     * @return T
     */
    public T dequeueMin();

}
