/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.comparables;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aaugustyniak
 */
public class EqualPersonTest {

    EqualPerson a = new EqualPerson("Alice", 1234);
    EqualPerson b = new EqualPerson("Alice", 1234);
    EqualPerson c = new EqualPerson("Bob", 1234);
    EqualPerson d = new EqualPerson("Alice", 4321);

    @Test
    public void testEqualsWithSelf() {
        assertTrue(a.equals(a));
    }

    @Test
    public void testEqualsWithSame() {
        assertTrue(a.equals(b));
    }

    @Test
    public void testEqualsWithDiffer() {
        assertFalse(a.equals(c));
        assertFalse(a.equals(d));
    }

    @Test
    public void testEqualsWithNull() {
        assertFalse(a.equals(null));
    }

    @Test
    public void testEqualsWithOtherClass() {
        assertFalse(a.equals(new Integer(2)));
    }

    @Test
    public void testEqualsConsistentWithCompare() {
        assertTrue(a.equals(a));
        assertTrue(a.compareTo(a) == 0);

    }

}
