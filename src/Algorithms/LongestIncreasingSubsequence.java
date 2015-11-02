/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * Longest Increasing Subsequence
For example, length of LIS for { 10, 22, 9, 33, 21, 50, 41, 60, 80 } is 6 and LIS is {10, 22, 33, 50, 60, 80}. 


http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 */
public class LongestIncreasingSubsequence {

    public static int lis(final int[] input, final int n) {
        if (input == null || input.length == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int res;
        int max_ending_here = 1;

        for (int i = 1; i < n; i++) {
            res = lis(input, i);
            if (input[i - 1] < input[n - 1] && res + 1 > max_ending_here) {
                max_ending_here = res + 1;
            }
        }

        return max_ending_here;
    }
    
    public static int lis(final int[] input) {
        return lis(input, input == null ? 0 : input.length);
    }
    
    public static int lisDP(final int[] input, final int[] partials, final int n) {
        if (input == null || input.length == 0) {
            return 0;
        }

        int res;
        int max_ending_here = 1;

        for (int i = 1; i < n; i++) {
            if (partials[i] != -1) {
                res = partials[i-1];
            } else {
                res = lisDP(input, partials, i);
                partials[i-1] = res;
            }
            if (input[i -1] < input[n - 1] && res + 1 > max_ending_here) {
                max_ending_here = res + 1;
            }
        }

        return max_ending_here;
    }
    
    public static int lisDP(final int[] input) {
        int[] partials = new int[input == null ? 0 : input.length];
        for (int i = 0; i < partials.length; i++) {
            partials[i] = -1;
        }
        return lisDP(input, partials, input == null ? 0 : input.length);
    }
    
    
    
    public static List<Integer> lisSequence(final int[] input, final int[] partials, final int n) {
        List<Integer> result = new LinkedList<>();
        if (input == null || input.length == 0) {
            return result;
        }

        int res;
        int max_ending_here = 1;

        for (int i = 1; i < n; i++) {
            if (partials[i] != -1) {
                res = partials[i-1];
            } else {
                res = lisDP(input, partials, i);
                partials[i-1] = res;
            }
            if (input[i -1] < input[n - 1] && res + 1 > max_ending_here) {
                max_ending_here = res + 1;
                result.add(input[i-1]);
            }
        }
        
        result.add(input[n-1]);

        return result;
    }
    
    public static List<Integer> lisSequence(final int[] input) {
        int[] partials = new int[input == null ? 0 : input.length];
        for (int i = 0; i < partials.length; i++) {
            partials[i] = -1;
        }
        return lisSequence(input, partials, input == null ? 0 : input.length);
    }
    
    
    public static void main(String[] args) {
        long start, end, diff;
        start = System.nanoTime();
        System.out.println(lis(new int[]{10,22,9,33,21,50,41,60,80, 100, 90}));
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time:" + diff / 1000000000 + "(" + diff + ")");
        
        
        start = System.nanoTime();
        System.out.println(lisDP(new int[]{10,22,9,33,21,50,41,60,80}));
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time:" + diff / 1000000000 + "(" + diff + ")");
        
        System.out.println("");
        System.out.print("{");
        List<Integer> result = lisSequence(new int[]{10,22,9,33,21,50,41,60,80});
        for(int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() -1) {
                System.out.print(",");
            }
        }
        System.out.println("}");
        
        
        System.out.println("");
        System.out.print("{");
        result = lisSequence(new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15});
        for(int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() -1) {
                System.out.print(",");
            }
        }
        System.out.println("}");
    }
}
