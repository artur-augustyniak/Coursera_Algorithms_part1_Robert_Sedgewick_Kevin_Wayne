/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.datastruckts.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.aaugustyniak.algorithms_datastructs.datastruckts.SimpleStack;

/**
 *
 * @author artur
 */
public class SimpleStacksTest {

    @Test
    public void testLinkedListStack() {
        SimpleStack<String> stack = new StackOfStringsLinkedList();
        doTestWith(stack);
        stack = new StackOfStringsLinkedList();
        doIterationWith(stack);
    }

    @Test
    public void testArrayStack() {
        SimpleStack<String> stack = new StackOfStringsArray(10);
        doTestWith(stack);
        stack = new StackOfStringsArray(20);
        doIterationWith(stack);
    }

    @Test
    public void testArrayDoublingResizingStack() {
        SimpleStack<String> stack = new StackOfStringsArrayResizing();
        doTestWith(stack);
        stack = new StackOfStringsArrayResizing();
        doIterationWith(stack);
    }

    private void doTestWith(SimpleStack<String> stack) {

        String data = "to be or not to - be - - that - - - is";
        String[] words = data.split(" ");
        List<String> actual = new ArrayList<>();
        for (String s : words) {
            if (s.equals("-")) {
                String item = stack.pop();
                System.out.println(item);
                actual.add(item);
            } else {
                stack.push(s);
            }
        }
        String[] expectedArr = {"to", "be", "not", "that", "or", "be"};
        List<String> expected;
        expected = new ArrayList<>(Arrays.asList(expectedArr));
        Assert.assertEquals(expected, actual);

    }

    private void doIterationWith(SimpleStack<String> stack) {
        String[] expectedArr = {"to", "be", "not", "that", "or", "be"};
        for (String string : expectedArr) {
            stack.push(string);
        }
        Iterator<String> it = stack.iterator();
        System.out.println("iter###########" + stack);
        while (it.hasNext()) {
            String string = it.next();
            System.out.println(string);
        }
        System.out.println("ENDiter###########");
    }

}
