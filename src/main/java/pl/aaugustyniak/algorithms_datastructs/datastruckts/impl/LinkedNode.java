/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleNode;

/**
 *
 * @author aaugustyniak
 */
public class LinkedNode implements SimpleNode{
    
    public int key;
    public String payload;
    public LinkedNode next;

    public LinkedNode(int key, String payload) {
        this.key = key;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "LinkedNode{" + "key=" + key + ", payload=" + payload + ", next=" + ((next!=null)? next.key : "null") + '}';
    }
    
    
       
}
