/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.sorting.impl;

import pl.aaugustyniak.algorithms_datastructs.sorting.VerboseSorter;

/**
 *
 * @author artur
 * @param <T>
 */
public class BubbleSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    @Override
    public T[] sort(T[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arrayCost++;
                if (less(arr[i], arr[j])) {
                    swap(arr, i, j);
                }
            }
        }
        long stopTime = System.currentTimeMillis();
        this.microtime = stopTime - startTime;
        return arr;
    }

}
