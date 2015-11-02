/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
* Find longest palindrome in a string

madam => madam
mymadamthisplahalpasdf => plahalp
asdfasdf => ""
bcaaaabc => "aaaa"


*/

public class LongestPalindrome {

	public static String longestPalindromeN3(final String input) {
		String longest = "";
		for (int i = 0; i < input.length(); i++) {
			for (int j = i + 1; j <= input.length(); j++) {
				String temp = input.substring(i, j);
				if (isPalindrome(temp) && temp.length() > longest.length()) {
					longest = temp;
				}
			}
		}
		return longest;

	}
        
        public static String longestPalindromeN3Improved(final String input) {
		String longest = "";
		for (int subLength = input.length()-1; subLength >= 0; subLength--) {
                       for (int i = 0; i < input.length() - subLength; i++) {
				String temp = input.substring(i, i+subLength);
				if (isPalindrome(temp) && temp.length() > longest.length()) {
					longest = temp;
				}
			}
		}
		return longest;

	}

	private static boolean isPalindrome(final String input) {
		int start = 0;
		int end = input.length() -1;
		while (start < end) {
			if (input.substring(start, start + 1).equals(input.substring(end, end + 1))) {
				start++;
				end--;
			} else {
				return false;
			}
			
		}

		return true;

	}

	// http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
	public static String longestPalindromeDP(final String input) {
		boolean[][] partialPalindrome = new boolean[input.length()][input.length()];
		int start = 0;
		int maxLength = 1;		

		for (int i = 0; i < input.length(); i++) {
			partialPalindrome[i][i] = true;
		}

		for (int i = 0; i < input.length() - 1; i++) {
			if (input.charAt(i) == input.charAt(i+1)) {
				partialPalindrome[i][i+1] = true;
				start = i;
				maxLength = 2;
			}
		}

		// Check for lengths greater than 2. k is length
	        // of substring
    		for (int k = 3; k <= input.length(); ++k) {
			// Fix the starting index
		        for (int i = 0; i < input.length()-k+1 ; ++i) {
		            // Get the ending index of substring from
            		    // starting index i and length k
			    int j = i + k - 1;
 
			    // checking for sub-string from ith index to
			    // jth index iff str[i+1] to str[j-1] is a
			    // palindrome
			    if (partialPalindrome[i+1][j-1] && input.charAt(i) == input.charAt(j)) {
				partialPalindrome[i][j] = true;
		 
				if (k > maxLength) {
				    start = i;
				    maxLength = k;
				}
			    }
			}
		}

		return input.substring(start, start + maxLength);

	}

	// http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
	public static String longestPalindromeManacher(final String input) {
		String processed = preProcess(input);
		int[] p = new int[processed.length()];
		int center = 0;
		int right = 0;
		for (int i = 1; i < p.length - 1; i++) {
			int i_mirror = 2*center - i;	
			
			p[i] = right > i ? Math.min(right-i, p[i_mirror]) : 0;

			while (processed.charAt(i + 1 + p[i]) == processed.charAt(i - 1 - p[i])) {
				p[i]++;
			}

			if (i + p[i] > right) {
				center = i;
				right = i + p[i];
			}
					
		}
		
		int max = 0;
		int centerIndex = 0;
		for (int i = 1; i < p.length -1; i++) {
			if (p[i] > max) {
				max = p[i];
				centerIndex = i;
			}
		}

                int start = (centerIndex - 1 - max)/2;
		return input.substring(start, start + max);
	}

	private static String preProcess(final String input) {
		if (input == null || input.length() == 0) {
			return "^$";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("^");
		String SEPARATOR = "#";
		for(char c : input.toCharArray()) {
			sb.append(SEPARATOR);
			sb.append(c);
		}
		sb.append("#$");

		return sb.toString();

	}

	
        public static void main(String[] args) {
        
            long start, end, time;
            System.out.println("--------------N^3-----------------------");
            start = System.nanoTime();
            System.out.println(longestPalindromeN3("madam")); //madam
            System.out.println(longestPalindromeN3("mymadamthisplahalpasdf")); //plahalp
            System.out.println(longestPalindromeN3("asdfasdf")); //a
            System.out.println(longestPalindromeN3("bcaaaabc")); //aaaa
            end = System.nanoTime();
            time = end - start;
            System.out.println("N^3 - Time:  " + time / 1000000000 + " (" + time + ")");
            
            System.out.println("--------------N^3 Improved-----------------------");
            start = System.nanoTime();
            System.out.println(longestPalindromeN3Improved("madam")); //madam
            System.out.println(longestPalindromeN3Improved("mymadamthisplahalpasdf")); //plahalp
            System.out.println(longestPalindromeN3Improved("asdfasdf")); //a
            System.out.println(longestPalindromeN3Improved("bcaaaabc")); //aaaa
            end = System.nanoTime();
            time = end - start;
            System.out.println("N^3 Improved - Time:  " + time / 1000000000 + " (" + time + ")");
            
            
            System.out.println("--------------N^2-----------------------");
            start = System.nanoTime();
            System.out.println(longestPalindromeDP("madam")); //madam
            System.out.println(longestPalindromeDP("mymadamthisplahalpasdf")); //plahalp
            System.out.println(longestPalindromeDP("asdfasdf")); //a
            System.out.println(longestPalindromeDP("bcaaaabc")); //aaaa
            end = System.nanoTime();
            time = end - start;
            System.out.println("N^2 - Time:  " + time / 1000000000 + " (" + time + ")");
            
            System.out.println("--------------Manacher-----------------------");
            start = System.nanoTime();
            System.out.println(longestPalindromeManacher("madam")); //madam
            System.out.println(longestPalindromeManacher("mymadamthisplahalpasdf")); //plahalp
            System.out.println(longestPalindromeManacher("asdfasdf")); //a
            System.out.println(longestPalindromeManacher("bcaaaabc")); //aaaa
            end = System.nanoTime();
            time = end - start;
            System.out.println("Manacher - Time:  " + time / 1000000000 + " (" + time + ")");

    }
}
