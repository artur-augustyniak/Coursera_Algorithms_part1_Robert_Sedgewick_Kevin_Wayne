/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts;

/**
 *
 * @author aaugustyniak
 */
public interface SimpleList {

    public void insertAfter(int k, String v);

    public SimpleNode find(int k);

    public void display();

}
