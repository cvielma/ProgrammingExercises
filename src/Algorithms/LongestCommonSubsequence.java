/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest common subsequence (LCS)

LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. So a string of length n has 2^n different possible subsequences.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.

 */
public class LongestCommonSubsequence {

    public static String lcs(final String input1, final String input2) {

        if (input1 == null || input2 == null || input1.length() == 0 || input2.length() == 0) {
            return "";
        }

        int n1 = input1.length();
        int n2 = input2.length();

        if (input1.substring(n1 - 1, n1).equals(input2.substring(n2 - 1, n2))) {
            return lcs(input1.substring(0, n1 - 1), input2.substring(0, n2 - 1)) + input1.substring(n1 - 1, n1);
        } else {
            String s1 = lcs(input1, input2.substring(0, n2 - 1));
            String s2 = lcs(input1.substring(0, n1 - 1), input2);
            if (s1.length() > s2.length()) {
                return s1;
            } else {
                return s2;
            }
        }

    }

    public static String lcsDP(final String input1, final String input2) {
        return lcsDP(input1, input2, new HashMap<String, Map<String, String>>());
    }

    public static String lcsDP(final String input1, final String input2, Map<String, Map<String, String>> partial) {

        if (input1 == null || input2 == null || input1.length() == 0 || input2.length() == 0) {
            return "";
        }

        int n1 = input1.length();
        int n2 = input2.length();
        String i1 = input1.substring(0, n1 - 1);
        String i2 = input2.substring(0, n2 - 1);

        if (input1.substring(n1 - 1, n1).equals(input2.substring(n2 - 1, n2))) {
            String currLcs = lcsExist(partial, i1, i2);
            if (currLcs != null) {
                return currLcs + input1.substring(n1 - 1, n1);
            } else {
                String result = lcsDP(i1, i2, partial) + input1.substring(n1 - 1, n1);
                setMap(partial, i1, i2, result);
                return result;
            }
        } else {
            String s1;
            String currLCS1 = lcsExist(partial, input1, i2);
            if (currLCS1 != null) {
                s1 = currLCS1;
            } else {
                s1 = lcsDP(input1, i2, partial);
                setMap(partial, input1, i2, s1);
            }

            String s2;
            String currLCS2 = lcsExist(partial, i1, input2);
            if (currLCS2 != null) {
                s2 = currLCS2;
            } else {
                s2 = lcsDP(i1, input2, partial);
                setMap(partial, i1, input2, s2);
            }

            if (s1.length() > s2.length()) {
                return s1;
            } else {
                return s2;
            }
        }

    }
    
    

    private static void setMap(final Map<String, Map<String, String>> map, final String input1, final String input2, final String lcs) {
        Map<String, String> temp = new HashMap<>();
        temp.put(input2, lcs);
        map.put(input1, temp);
    }

    private static String lcsExist(final Map<String, Map<String, String>> map, final String input1, final String input2) {
        if (map.containsKey(input1)) {
            return map.get(input1).get(input2);
        }
        return null;
    }

    public static void main(String[] args) {
        long start, end, diff;
        start = System.nanoTime();
        System.out.println(lcs("ABCDGH", "AEDFHR"));
        System.out.println(lcs("AGGTAB", "GXTXAYB"));
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time: " + diff / 1000000000 + "(" + diff + ")");

        start = System.nanoTime();
        System.out.println(lcsDP("ABCDGH", "AEDFHR"));
        System.out.println(lcsDP("AGGTAB", "GXTXAYB"));
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time: " + diff / 1000000000 + "(" + diff + ")");
    }
}
