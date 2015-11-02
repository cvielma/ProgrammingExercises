/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author cvielma
 */
/**
 * A sample of base 10 radix sort algorithm.
 */
public class WikipediaRadixSort {
    // base 10
    // LinkedList is also a Queue
    @SuppressWarnings("unchecked")
    private static LinkedList<Integer>[] counter = new LinkedList[] {
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>(),
        new LinkedList<Integer>()
    };
 
    public static void sortLSD(int[] array, int maxDigitSymbols) {
        int mod = 10;
        int dev = 1;
        for (int i = 0; i < maxDigitSymbols; i++, dev *= 10, mod *= 10) {
            System.out.println("i=" + i + ", dev=" + dev + ", mod=" + mod);
            for(int j = 0; j < array.length; j++) {
                int bucket = (array[j] % mod) / dev;
                counter[bucket].add(array[j]);
            }
            System.out.println("Counter: " + Arrays.toString(counter));
            int pos = 0;
            for(int j = 0; j < counter.length; j++) {
                Integer value = null;
                while ((value = counter[j].poll()) != null) {
                    array[pos++] = value;
                }
            }
            System.out.println(Arrays.toString(array));
            System.out.println("------------------------");
//            System.out.println(Arrays.toString(counter));
        }
 
    }
 
    public static void main(String... args) {        
        int[] test = { 10, 1, 3, 30, 256, 0, 9, 156 };
 
        // we choose 3, because we have 156 with 3 digits
        WikipediaRadixSort.sortLSD(test, 3);
 
        System.out.println(Arrays.toString(test));
    }
 
}
