/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * 
Problem Statement
    
Cat Taro likes strings. He is currently constructing a string S of length N. Each character of S will be either 'A' or 'B'. Taro has already chosen some of the characters. You are given these choices as a int[] position and a String value. For each valid i, the character at the 1-based index position[i] in S is the character value[i].

To Taro, the ugliness of a string is the number of pairs of equal consecutive characters. For example, the ugliness of "ABABAABBB" is 3: there is one pair "AA" and two (overlapping) pairs "BB".

Taro now wants to finish S by filling in the remaining characters. His goal is to create a string with the smallest possible ugliness. Let X be the number of possible strings Taro may produce. Return the value (X modulo 1,000,000,007).
Definition
    
Class:
TaroFillingAStringDiv1
Method:
getNumber
Parameters:
int, int[], String
Returns:
int
Method signature:
int getNumber(int N, int[] position, String value)
(be sure your method is public)
Limits
    
Time limit (s):
2.000
Memory limit (MB):
256
Stack limit (MB):
256
Constraints
-
N will be between 1 and 1,000,000,000, inclusive.
-
position will contian between 1 and 50 elements, inclusive.
-
value and position will contain the same number of elements.
-
Each element of position will be between 1 and N, inclusive.
-
All elements of position will be distinct.
-
value will consist only of characters 'A' and 'B'.
Examples
0)

    
3
{1, 3}
"AB"
Returns: 2
Currently, Taro's string looks as follows: A_B. He can either produce the string AAB or the string ABB. Both have the same ugliness.
1)

    
4
{2, 1, 3, 4}
"ABBA"
Returns: 1
All characters of S have already been chosen.
2)

    
25
{23, 4, 8, 1, 24, 9, 16, 17, 6, 2, 25, 15, 14, 7, 13}
"ABBBBABABBAAABA"
Returns: 1

3)

    
305
{183, 115, 250, 1, 188, 193, 163, 221, 144, 191, 92, 192, 58, 215, 157, 187, 227, 177, 206, 15, 272, 232, 49, 11, 178, 59, 189, 246}
"ABAABBABBAABABBBBAAAABBABBBA"
Returns: 43068480

This problem statement is the exclusive and proprietary property of TopCoder, Inc. Any unauthorized use or reproduction of this information without the prior written consent of TopCoder, Inc. is strictly prohibited. (c)2003, TopCoder, Inc. All rights reserved.
 */
package TopCoder;

/**
 *
 * @author cvielma
 */

public class TaroFillingAStringDiv1 {

	public int getNumber(final int N, int[] position, String value) {
		char[] chars = new char[N];
		int totalStrings = 0;
		
		for (int i = 0; i < position.length; i++) {
			chars[position[i]-1] = value.charAt(position[i]-1);
		}
		
		for(int i = 0; i < N; i++) {
			if (chars[i] == '\0') {
				int uglyA = countUglyness(chars, i, 'A');
				int uglyB = countUglyness(chars, i, 'B');
				if (uglyA == uglyB) {
					totalStrings += 2;
				} else {
					if (uglyA < uglyB) {
						chars[i] = 'A';
					} else {
						chars[i] = 'B';
					}
				}
				
			}
		}
		totalStrings = totalStrings == 0 ? 1 : totalStrings;
		return totalStrings;
	
	}
	
	private int countUglyness(final char[] chars, final int pos, final char test) {
		int count = 0;
		if ((pos -1) >= 0 && chars[pos-1] != '\0' && chars[pos-1] == test) count++;
		if ((pos +1) < chars.length && chars[pos+1] != '\0' && chars[pos+1] == test) count++;
		return count;
	}

        public static void main(String[] args) {
            System.out.println("AB".charAt(0));
            System.out.println("AB".charAt(1));
            System.out.println("AB".charAt(2));
        }
}