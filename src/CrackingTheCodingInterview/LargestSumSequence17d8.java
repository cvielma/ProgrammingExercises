/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.Arrays;

/**
* You are given an array of integers (both positive and negative). Find the contiguous sequence with the largest sum. Return the sum.
	EXAMPLE:
		Input: 2, -8, 3, -2, 4, -10
		Output: 5
*/

public class LargestSumSequence17d8 {

	public static int findLargestSum(final int[] array) {
		int[] partials = new int[array.length];
		int maxSum = Integer.MIN_VALUE;
		
		partials[0] = array[0];
		for (int i = 1; i < array.length; i++) {
			partials[i] = partials[i-1] + array[i];		
		}
                
//                System.out.println("Original partials: " + Arrays.toString(partials));

		for (int j = 0; j < partials.length; j++) {
			for (int i = j; i < array.length; i++) {
				if (maxSum < partials[i]) {
					maxSum = partials[i];
				}
//                                System.out.println("Partials for " + array[j] + ": " + Arrays.toString(partials));
				partials[i] -= array[j];
                                
			}
		}

		return maxSum;
	}
        
        public static int getMaxSum(int[] a) {
            int maxsum = 0;
            int sum = 0;
            for (int i = 0; i < a.length; i++) {
                sum += a[i];
                if (maxsum < sum) {
                    maxsum = sum;
                } else if (sum < 0) {
                    sum = 0;
                }
            }
            return maxsum;
        }


	public static void main(String[] args) {
		int[] array = new int[]{2, -8, 3, -2, 4, -10};
		System.out.println(findLargestSum(array));
                
                System.out.println(getMaxSum(array));
                
	}
}

