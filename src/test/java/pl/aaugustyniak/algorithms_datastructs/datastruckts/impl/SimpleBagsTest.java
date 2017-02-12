/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.Iterator;

import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleBag;

/**
 *
 * @author artur
 */
public class SimpleBagsTest {

    @Test
    public void testLikedListBag() {
        SimpleBag<String> bag = new BagOfStringsLinkedList();
        makeTest(bag);
    }

    private void makeTest(SimpleBag<String> bag) {

        String[] expectedArr = {"to", "be", "not", "that", "or", "be"};
        for (String string : expectedArr) {
            bag.add(string);
        }
        Iterator<String> it = bag.iterator();
        System.out.println("iter###########");
        while (it.hasNext()) {
            String string = it.next();
            System.out.println(string);
        }
        System.out.println("iter###########");

    }

}
