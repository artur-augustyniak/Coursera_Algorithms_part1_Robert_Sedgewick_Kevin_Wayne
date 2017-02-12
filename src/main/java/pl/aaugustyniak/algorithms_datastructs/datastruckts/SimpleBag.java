/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts;

/**
 *
 * @author artur
 * @param <T>
 */
public interface SimpleBag<T> extends Iterable<T>{

    public void add(T item);

    public boolean isEmpty();

    public int size();
}
