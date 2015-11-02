package CodeEval;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * CodeEval: Longest Subsequence You are given two sequences. Write a program to
 * determine the longest common subsequence between the two strings (each string
 * can have a maximum length of 50 characters). NOTE: This subsequence need not
 * be contiguous. The input file may contain empty lines, these need to be
 * ignored.
 *
 * @author Christian Vielma <christianvielma@gmail.com>
 */
public class MainLongestSubsequence {

    public static String getLongestSubstring(String s1, String s2) {
        ArrayList<String> result = new ArrayList<>();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        for (int i = 0; i < c1.length; i++) {
            int currMax = -1;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < c1.length; j++) {
                for (int k = 0; k < c2.length; k++) {
                    if(c1[j] == c2[k] && currMax <k){
                        sb.append(c1[j]);
                        currMax=k;
                        break;
                    }
                }                
            }
            result.add(sb.toString());
        }
        
        String maxString ="";
        int maxLength =0;
        for(String s: result){
            if(s.length()>maxLength){
                maxString=s;
                maxLength=s.length();
            }
        }

        return maxString.trim();
    }

    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner input = new Scanner(new File(args[0]));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if(line.isEmpty()) continue;
            String[] s = line.split(";");
            if(s.length!=2) continue;
            System.out.println(getLongestSubstring(s[0],s[1]));
        }
        
    }
}
