/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.comparables;

/**
 *
 * @author aaugustyniak
 */
public class SimpleDate implements Comparable<SimpleDate> {

    private final int year;
    private final int month;
    private final int day;

    public SimpleDate(int year, int month, int day) {

        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public int compareTo(SimpleDate that) {
        if (this.year < that.year) {
            return -1;
        }
        if (this.year > that.year) {
            return +1;
        }
        if (this.month < that.month) {
            return -1;
        }
        if (this.month > that.month) {
            return +1;
        }
        if (this.day < that.day) {
            return -1;
        }
        if (this.day > that.day) {
            return +1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return year + "-" + month + "-" + day;
    }

}
