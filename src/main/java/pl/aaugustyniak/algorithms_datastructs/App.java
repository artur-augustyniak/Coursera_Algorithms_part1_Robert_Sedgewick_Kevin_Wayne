/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs;

import pl.aaugustyniak.algorithms_datastructs.sorting.divideandconqer.MergeSorter;



/**
 *
 * @author artur
 */
public class App {

    private static int ROWS;
    private static int row = 0;
    private static int VERTICAL = 70;
    
    
     // draw one row of trace
    public static void show(Double[] a, int lo, int hi) {
        double y = ROWS - row - 1;
        for (int k = 0; k < a.length; k++) {
            if      (k < lo)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else if (k > hi)             StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
            else                         StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledRectangle(k, y + a[k]*.25, .25, a[k]*.25);
        }
        row++;
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int M = 4;
        int N = 20;
        Double[] a = new Double[N];
        Double[] b = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = ((int) (1 + Math.random() * M)) / (double) M;
            b[i] = a[i];
        }
        
        MergeSorter<Double> s = new MergeSorter<>();

        // precompute the number of rows
        
        StdDraw.show(0);
        ROWS = 0;
        s.sort(b);
        ROWS = row;
        row = 0;
        StdDraw.clear();

        StdDraw.setCanvasSize(800, ROWS * VERTICAL);
        StdDraw.show(0);
        StdDraw.square(.5, .5, .5);
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(-0.5, ROWS);
        StdDraw.show(0);
        s.sort(a);
        StdDraw.show(0);

//        SimpleStack<String> stack = new StackOfStringsLinkedList();
//        String data = "to be or not to - be - - that - - - is";
//        String[] words = data.split(" ");
//
//        for (String s : words) {
//            if (s.equals("-")) {
//                String item = stack.pop();
//                System.out.println(item);
//
//            } else {
//                stack.push(s);
//            }
//        }
    }
}
