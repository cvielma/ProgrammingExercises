package Util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * Problem Statement
    
Beaver Bindu has some chocolates arranged in a row. The wrapping of each chocolate has a single color. Multiple chocolates can share the same color. In this problem, each of the possible colors is represented by an uppercase letter. You are given a String chocolates. For each i, the i-th chocolate (0-based index) in the row has the color chocolates[i].  The spread of a row of chocolates is the maximum number of adjacent chocolates that all share the same color. Formally, the spread can be defined as the maximum value of (j-i+1), where i <= j and all the chocolates in the positions between i and j, inclusive, have the same color.  You are also given an int maxSwaps. Bindu can swap any two adjacent chocolates. She has decided to make at most maxSwaps such swaps.  Return the maximum spread she can obtain.  
Definition
    
Class:
ColorfulChocolates
Method:
maximumSpread
Parameters:
String, int
Returns:
int
Method signature:
int maximumSpread(String chocolates, int maxSwaps)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
64
Constraints
-
chocolates will contain between 1 and 50 characters, inclusive.
-
Each character in chocolates will be an uppercase letter ('A'-'Z').
-
maxSwaps will be between 1 and 2500, inclusive.
Examples
0)

    
"ABCDCBC"
1
Returns: 2
One optimal solution is to swap chocolates at positions 2 and 3, obtaining the row "ABDCCBC", which has spread 2.
1)

    
"ABCDCBC"
2
Returns: 3
The only optimal solution is to produce the row "ABDCCCB".
2)

    
"ABBABABBA"
3
Returns: 4
The row "ABBBBAABA" can be produced with 3 swaps.
3)

    
"ABBABABBA"
4
Returns: 5
An optimal solution is to produce the row "AABBBBBAA".
4)

    
"QASOKZNHWNFODOQNHGQKGLIHTPJUVGKLHFZTGPDCEKSJYIWFOO"
77
Returns: 5

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */

/**
 *
 * @author cvielma
 */
import java.util.*;

public class ColorfulChocolates {
	public static int maximumSpread(final String chocolates, final int maxSwaps) {
		return maximumSpread(chocolates, maxSwaps, new HashMap<String,Integer>());
	}
	
	private static int maximumSpread(final String chocolates, final int maxSwaps, final Map<String, Integer> map) {
			if (chocolates.length() == 1) {
                                 map.put(chocolates, 1);
				return 1;
			} else if (chocolates.length() == 2) {
                                int temp = chocolates.substring(0,1).equals(chocolates.substring(1,2)) ? 2 : 1;
                                map.put(chocolates, temp);
                                return temp;
			} else {
				String firstChar = chocolates.substring(0,1);
				String secondChar = chocolates.substring(1,2);
				int maxSwapping = 0;
				int max = 0;
				if (!map.containsKey(chocolates)) {
					if (maxSwaps > 0) {
						maxSwapping = maximumSpread(firstChar + chocolates.substring(2, chocolates.length()), maxSwaps - 1, map);
					}
					int maxNotSwapping = maximumSpread(chocolates.substring(1, chocolates.length()), maxSwaps, map);
					max = Math.max(maxSwapping, maxNotSwapping);
				} else {
					max = map.get(chocolates);
				}
				max = firstChar.equals(secondChar) ? max + 1 : max; 
				map.put(chocolates, max);
				return max;			
			}
	}
        
        public static int maximumSpread2(final String chocolates, final int maxSwaps) {
			if (chocolates.length() == 1) {
				return 1;
			} else if (chocolates.length() == 2) {
				return chocolates.substring(0,1).equals(chocolates.substring(1,2)) ? 2 : 1;
			} else {
				String firstChar = chocolates.substring(0,1);
				String secondChar = chocolates.substring(1,2);
				int maxSwapping = 0;
				int max = 0;
				if (maxSwaps > 0) {
                                    maxSwapping = maximumSpread2(firstChar + chocolates.substring(2, chocolates.length()), maxSwaps - 1);
				}
                                int maxNotSwapping = maximumSpread2(chocolates.substring(1, chocolates.length()), maxSwaps);
				max = Math.max(maxSwapping, maxNotSwapping);
				max = firstChar.equals(secondChar) ? max + 1 : max; 
				return max;			
			}
	}
        
        public static void main(String[] args) {
            long start = System.nanoTime();
            System.out.println(maximumSpread("QASOKZNHWNFODOQNHGQKGLIHTPJUVGKLHFZTGPDCEKSJYIWFOO", 77));
            long end = System.nanoTime();
            System.out.println((end-start)/1000000000);
            
            start = System.nanoTime();
            System.out.println(maximumSpread2("QASOKZNHWNFODOQNHGQKGLIHTPJUVGKLHFZTGPDCEKSJYIWFOO", 77));
            end = System.nanoTime();
            System.out.println((end-start)/1000000000);
            
            
        }

}
