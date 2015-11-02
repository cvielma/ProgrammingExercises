/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

/**
 * https://leetcode.com/problems/scramble-string/
 *
 * @author cvielma
 */
public class ScrambleString {

    public static boolean isScramble(final String s1, final String s2) {
        return isScramble(s1, s2, true);
    }

    public static boolean isScramble(final String s1, final String s2, final boolean canReverse) {
        if (s1 == null && s2 == null) {
            return true;
        } else if ((s1 == null && s2 != null) || (s2 == null && s1 != null) || (s1.length() != s2.length())) {
            return false;
        }

        System.out.println("Comparing: " + s1 + ", " + s2);

        if (s1.equals(s2)) {
            return true;
        } else if (s1.length() == 1) {
            return false;
        } else if (s1.length() == 2) {
            String swapped = "" + s1.charAt(1) + s1.charAt(0);
            return swapped.equals(s2);
        } else {
            int halfLength = s1.length() / 2;
            boolean div1 = isScrambleHelper(s1, s2, halfLength);
            boolean div2 = false, div3 = false, div4 = false;
            if (div1 == false && s1.length() % 2 != 0) {
                div2 = isScrambleHelper(s1, s2, halfLength + 1);
            }

            if (!div1 && !div2 && canReverse) {
                div3 = isScrambleHelper2(s1, s2, halfLength);
                if (!div3 && s1.length() % 2 != 0) {
                    div4 = isScrambleHelper2(s1, s2, halfLength + 1);
                }
            }

            return div1 || div2 || div3 || div4;
        }

    }

    public static boolean isScrambleHelper(final String s1, final String s2, final int halfLength) {
        String s1FirstHalf = s1.substring(0, halfLength);
        String s1SecondHalf = s1.substring(halfLength, s1.length());
        String s2FirstHalf = s2.substring(0, halfLength);
        String s2SecondHalf = s2.substring(halfLength, s2.length());
        boolean fHalfScramble = false, sHalfScramble = false;

        fHalfScramble = isScramble(s1FirstHalf, s2FirstHalf);

        if (fHalfScramble) {
            sHalfScramble = isScramble(s1SecondHalf, s2SecondHalf);
        }

        return fHalfScramble && sHalfScramble;
    }

    public static boolean isScrambleHelper2(final String s1, final String s2, final int halfLength) {
        String s1FirstHalf = s1.substring(0, halfLength);
        String s1SecondHalf = s1.substring(halfLength, s1.length());

        return isScramble(s1SecondHalf + s1FirstHalf, s2, false);
    }

    public static void main(String[] args) {
//        System.out.println(isScramble("great", "rgtae"));
        System.out.println(isScramble("abab", "aabb"));
        System.out.println(isScramble("abab", "bbaa"));
    }
}
