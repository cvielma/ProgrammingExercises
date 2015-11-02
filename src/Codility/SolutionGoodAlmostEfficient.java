/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class SolutionGoodAlmostEfficient {

    //determines if it is matematically possibly that substr appears at least 
    //n times in S
    public boolean canOccur(int n, int i, String S) {
        if (i * n <= S.length()) {
            return true;
        }
        return false;
    }

    //counts how many times does substr appear in S
    //if n>0 it stops checking after n occurrences have been found.
    public int countOccurs(int n, int start, int end, char[] ca) {
        if (ca == null || start < 0 || end < 0 || start > ca.length || end > ca.length) {
            return 0;
        }

        int occurs = 0;
        n = n >= 0 ? n : ca.length;
        char[] prefix = Arrays.copyOfRange(ca, 0, start);
        for (int i = start; i < end && occurs < n; i += start) {
            char[] temp = Arrays.copyOfRange(ca, i, i + start);
            if (Arrays.equals(prefix, temp)) {
                occurs++;
            }
        }

        return occurs;
    }

    private boolean isBorder(int i, char[] ca) {
        if (ca.length-i>i) {
            for (int j = 0; j < i; j++) {
                if (ca[j] != ca[ca.length-i+j]){
                    return false;
                }
            }
        }
        return true;        
    }

    public int solution(String S) {
        /**
         * From the largest prefix to the min prefix. If we find one match
         * that's it.
         */
        if (S == null) {
            return -1;
        }
        char[] ca = S.toCharArray();
        for (int i = ca.length / 2 + 1; i > 0; i--) {
            //String beg = S.substring(0, i);
            if (canOccur(3, i, S) && isBorder(i, ca)) {
                //this way we do not search all the string but just a fraction
                int occurs = countOccurs(1, i, ca.length - i, ca);
                if (occurs >= 1) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws FileNotFoundException {

        String path = "";
        boolean test = true;
        if (test) {
            path = "/home/cvielma/Documents/Development/ToGoogle/ToGoogle/src/codeeval/tests/testCodibilitySmall.txt";;
        } else {
            path = args[0];
        }
        SolutionGoodAlmostEfficient s = new SolutionGoodAlmostEfficient();
        Scanner sc = new Scanner(new File(path));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            //System.out.println(line+"="+checkEmail(line));
            System.out.println(line+"="+s.solution(line));
        }
    }
}
