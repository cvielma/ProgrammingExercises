/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cvielma
 */
public class BucketSort {

    final static int BUCKET_SIZE = 3;

    public static long startTime() {
        return System.nanoTime();
    }
    
    public static long stopAndDiff(final long start) {
        long stop = System.nanoTime();
        long diff = stop - start;
        System.out.println("Time: " + diff + " (" + diff / 1000000000 + ")");
        return diff;
    }
     
    public static int[] bucketSort(final int[] input, final int maxNum) {        
        List<Integer>[] buckets = new List[maxNum / BUCKET_SIZE];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        return bucketSort(input, buckets);

    }

    public static int[] bucketSort(final int[] input, final List<Integer>[] buckets) {
        for (int i = 0; i < input.length; i++) {
            final int pos = input[i] / BUCKET_SIZE;
            buckets[pos].add(input[i]);
        }

        System.out.println("Buckets = " + Arrays.toString(buckets));
        int pos = 0;
        for (final List<Integer> curr : buckets) {
            Collections.sort(curr);
            while (!curr.isEmpty()) {
                input[pos++] = curr.get(0);
                curr.remove(0);
            }
        }
        return input;
    }

    public static int[] bucketSortInsertion(final int[] input, final int maxNum) {
        List<Integer>[] buckets = new List[maxNum / BUCKET_SIZE];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        return bucketSortInsertion(input, buckets);

    }

    public static int[] bucketSortInsertion(final int[] input, final List<Integer>[] buckets) {
        for (int i = 0; i < input.length; i++) {
            final int pos = input[i] / BUCKET_SIZE;
            buckets[pos].add(input[i]);
        }

//        System.out.println("Buckets = " + Arrays.toString(buckets));
        int pos = 0;
        for (final List<Integer> curr : buckets) {
            while (!curr.isEmpty()) {
                input[pos++] = curr.get(0);
                curr.remove(0);
            }
        }
        return insertionSort(input);
    }

    public static int[] insertionSort(final int[] input) {
        int[] sorted = new int[input.length];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = -1;
        }

        for (int i = 0; i < input.length; i++) {
            sorted = insert(input[i], sorted);
        }
        return sorted;
    }

    public static int[] insert(final int value, final int[] sorted) {
        for (int i = 0; i < sorted.length; i++) {
            if (sorted[i] == -1) {
                sorted[i] = value;
                return sorted;
            } else if (sorted[i] > value) {
                int currPos = i;
                int currTemp = value;
                int nextTemp;
                // Shifts elements
                while (currPos < sorted.length && currTemp != -1) {
                    nextTemp = sorted[currPos];
                    sorted[currPos] = currTemp;
                    currTemp = nextTemp;
                    currPos++;
                }
                return sorted;
            }
        }
        return sorted;
    }

    public static void main(String[] args) {
        int[] test = {10, 1, 3, 3, 2, 4, 5, 9, 8, 7, 6, 10, 30, 31, 32, 256, 0, 9, 156};
        long start;
        start = startTime();
        System.out.println(Arrays.toString(bucketSort(test, 1000)));
        stopAndDiff(start);
        
        
        test = new int[]{10, 1, 3, 3, 2, 4, 5, 9, 8, 7, 6, 10, 30, 31, 32, 256, 0, 9, 156};
        start = startTime();
        System.out.println(Arrays.toString(bucketSortInsertion(test, 1000)));
        stopAndDiff(start);
    }
}
