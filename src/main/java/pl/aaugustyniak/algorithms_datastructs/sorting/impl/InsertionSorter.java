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
public class InsertionSorter<T extends Comparable<T>> extends BaseSorter<T> implements VerboseSorter<T> {

    @Override
    public T[] sort(T[] arr) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                arrayCost++;
                if (less(arr[j], arr[j - 1])) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
        long stopTime = System.currentTimeMillis();
        this.microtime = stopTime - startTime;
        return arr;
    }
}
