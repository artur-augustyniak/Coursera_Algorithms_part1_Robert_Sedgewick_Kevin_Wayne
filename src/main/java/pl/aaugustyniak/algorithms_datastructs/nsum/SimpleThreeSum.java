/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.algorithms_datastructs.nsum;

import java.util.Random;

/**
 *
 * @author artur
 */
public class SimpleThreeSum {

    public static int count(int[] a) {
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        
         
        Random r = new Random(46);
        
        int[] a = new int[16000];
        //int[] digits = {30, -40, -20, -10, 40, 0, 10, 5};
        for (int i = 0; i < a.length; i++) {
           a[i] = r.nextInt(40) - r.nextInt(41);
        }
        
        System.out.println(count(a) / 1000.0);
        
    }
}
