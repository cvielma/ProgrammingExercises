/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * string input:  kdhffdaapgkjehatldkdhfp
set of chars {h, a, p}
find the shortest subsequence in the string input that contains all the chars in the given set.

 * @author cvielma
 */
public class GoogleInterview2 {
    static int testCounter = 0;

    public static class CurrMin {

        int length;
        int beginIndex;
        int endIndex;
    }

    public static long startTime() {
        return System.nanoTime();
    }

    public static long stopAndDiff(final long start) {
        long stop = System.nanoTime();
        long diff = stop - start;
        System.out.println("Time: " + diff + " (" + diff / 1000000000 + ")");
        return diff;
    }

    public static String shortestSubsequenceN2(final String input, final Set<Character> set) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                String temp = input.substring(i, j);
                if (containsAll(temp, set)) {
                    result.add(temp);
                    break;
                }
            }
        }

        String minString = "";
        int minLength = Integer.MAX_VALUE;
        for (String s : result) {
            if (s.length() < minLength) {
                minString = s;
                minLength = s.length();
            }
        }

        return minString;

    }

    public static boolean containsAll(final String input, final Set<Character> set) {
        for (Character c : set) {
            if (!input.contains("" + c)) {
                return false;
            }
        }
        return true;
    }

    public static String shortestSubsequenceAlmostLinear(final String input, final Set<Character> set) {
        Map<Character, Integer> count = new HashMap<>();
        Set<Character> remaining = new HashSet<>(set);
        for (Character c : set) {
            count.put(c, 0);
        }

        int beginIndex = -1;
        CurrMin result = new CurrMin();
        result.beginIndex = -1;
        result.endIndex = -1;

        for (int i = 0; i < input.length(); i++) {
            char currChar = input.charAt(i);
            if (!set.contains(currChar)) {
                continue;
            } else {
                int currCount = count.get(currChar);
                if (result.beginIndex == -1) {
                    result.beginIndex = i;
                    beginIndex = i;
                }

                if (currCount == 0) {
                    remaining.remove(currChar);
                    result.endIndex = i;
                }

                count.put(currChar, ++currCount);

                if (remaining.isEmpty()) {
                    if (input.charAt(beginIndex) == input.charAt(i)) {
                        beginIndex = moveToNextWhileCount(input, beginIndex, count);
                        if (beginIndex > 0 && i >= beginIndex && i - beginIndex < result.endIndex - result.beginIndex) {
                            result.length = i - beginIndex;
                            result.beginIndex = beginIndex;
                            result.endIndex = i;
                        }
                    }
                }
            }

        }

        if (result.endIndex != -1 && remaining.isEmpty()) {
            return input.substring(result.beginIndex, result.endIndex + 1);
        }

        return "";

    }

    public static int moveToNextWhileCount(final String input, final int index, final Map<Character, Integer> map) {
        int currIndex = index;
        while (currIndex < input.length()) {
            char currChar = input.charAt(currIndex);
            if (map.containsKey(currChar)) {
                int count = map.get(currChar);
                if (count <= 1) {
                    break;
                } else {
                    count--;
                    map.put(currChar, count);
                }
            }
            currIndex++;
        }

        return currIndex == input.length() ? -1 : currIndex;
    }
    
    public static void test(final String test, final Set<Character> set) {
        long start;
        System.out.println("--------------------------------");
        System.out.println("Test" + ++testCounter + ": " + test);
        start = startTime();
        System.out.println(shortestSubsequenceN2(test, set));
        stopAndDiff(start);
        start = startTime();
        System.out.println(shortestSubsequenceAlmostLinear(test, set));
        stopAndDiff(start);
    }

    public static void main(String[] args) {
        Set<Character> set = new HashSet<>();
        set.add('h');
        set.add('a');
        set.add('p');
        long start;
        String test = "kdhffdapapgkjehatldkdhafp"; //hafp
        test(test, set);
        
        test = "kdhffdapapgkjehatldkdhafapkjlkppahjkl1s"; //pah
        test(test, set);

        test = "";
        test(test, set);

        test = "afpfhkdhffdapapgkjehatldkdhfaffp"; // afpfh
        test(test, set);
    }
}
