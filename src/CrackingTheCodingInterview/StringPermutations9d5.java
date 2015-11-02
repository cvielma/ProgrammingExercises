/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author cvielma
 */
public class StringPermutations9d5 {

    public static List<String> stringPermutation(final String str) {
        List<String> result = new LinkedList<>();
        if (str == null) {
            return null;
        }
        if (str.length() == 1) {
            result.add(str);
            return result;
        } else if (str.length() == 2) {
            result.add(str);
            result.add("" + str.charAt(1) + str.charAt(0));
            return result;
        } else {
            List<String> subResult = stringPermutation(str.substring(1, str.length()));
            for (String s : subResult) {
                for (int i = 0; i <= s.length(); i++) {
                    result.add(s.substring(0, i) + str.substring(0, 1) + s.substring(i, s.length()));
                }
            }
            return result;
        }

    }
    
    
    // Doesn't work as expected
    public static List<String> stringPermutationDP(final String str, final Map<String, List<String>> map) {
        List<String> result = new LinkedList<>();
        if (str == null) {
            return null;
        }
        if (str.length() == 1) {
            result.add(str);
            map.put(str, result);
            return result;
        } else if (str.length() == 2) {
            result.add(str);
            result.add("" + str.charAt(1) + str.charAt(0));
            map.put(str, result);
            return result;
        } else {
            List<String> subResult = map.get(str);
            if (subResult == null) {
                subResult = stringPermutation(str.substring(1, str.length()));
            }
            for (String s : subResult) {
                for (int i = 0; i <= s.length(); i++) {
                    result.add(s.substring(0, i) + str.substring(0, 1) + s.substring(i, s.length()));
                }
            }
            map.put(str, result);
            return result;
        }

    }

    public static List<String> stringPermutationDP(final String str) {
        return stringPermutationDP(str, new HashMap<String, List<String>>());
    }

    public static void main(String[] args) {
        long start, end, diff;
        
        start = System.nanoTime();
        List<String> result = stringPermutation("abcdefghij");
//        System.out.println(result);
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time: " + diff / 1000000000 + "(" + diff + ")");
        System.out.println(result.size());
        System.out.println("--------------------");
        start = System.nanoTime();
        result = stringPermutationDP("abcdefghij");
//        System.out.println(result);
        end = System.nanoTime();
        diff = end - start;
        System.out.println("Time: " + diff / 1000000000 + "(" + diff + ")");
        System.out.println(result.size());
    }
}
