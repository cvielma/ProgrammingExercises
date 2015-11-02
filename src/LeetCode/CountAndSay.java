/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

/**
 * Count and say:  *
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 *
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211.
 *
 * Given an integer n, generate the nth sequence.
 *
 * Note: The sequence of integers will be represented as a string.
 * https://leetcode.com/problems/count-and-say/
 */
public class CountAndSay {
    
    public static long startTime() {
        return System.nanoTime();
    }
    
    public static long stopAndDiff(final long start) {
        long stop = System.nanoTime();
        long diff = stop - start;
        System.out.println("Time: " + diff + " (" + diff / 1000000000 + ")");
        return diff;
    }

    public static String countAndSay(int n) {
        int value = 1;
        String temp = "" + value;
        for (int i = 0; i < n -1; i++) {
            int currPos = 0;
            int count = 1;
            String next = "";
            while (currPos < temp.length()) {
                count = 1;
                while (currPos + count < temp.length() && temp.charAt(currPos) == temp.charAt(currPos + count)) {
                    count++;
                }
                next += count + "" + temp.charAt(currPos);
                currPos = currPos + count;
            }
            temp = next;
//            System.out.println(temp);
        }

        return temp;
    }
    
    public static String countAndSayMoreEfficient(int n) {
        int value = 1;
        String temp = "" + value;
        for (int i = 0; i < n-1; i++) {
            int currPos = 0;
            int count = 1;
            StringBuilder next = new StringBuilder();
            while (currPos < temp.length()) {
                count = 1;
                while (currPos + count < temp.length() && temp.charAt(currPos) == temp.charAt(currPos + count)) {
                    count++;
                }
                next.append(count).append(temp.charAt(currPos));
                currPos = currPos + count;
            }
            temp = next.toString();
//            System.out.println(temp);
        }

        return temp;
    }

    public static void main(String[] args) {
        long start;
        start = startTime();
        countAndSay(60);
        stopAndDiff(start);
        
        start = startTime();
        countAndSayMoreEfficient(60);
        stopAndDiff(start);
    }
}
