/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.Arrays;

/**
 *
 * @author cvielma
 */
public class QuicksortMine {

        public static int[] quicksort(int[] unsorted) {
            return quicksort(unsorted, 0, unsorted.length -1);
        }
    
        public static int[] quicksort(int[] unsorted, final int lo, final int hi) {
		if (lo < hi) {
			int pivot = partition(unsorted, lo, hi);
			quicksort(unsorted, lo, pivot-1);
			quicksort(unsorted, pivot+1, hi);
		}
                return unsorted;
	}


	private static int partition(final int[] unsorted, final int lo, final int hi) {
		int pivotIndex = (lo + hi) /2; // as good as any
		int pivotValue = unsorted[pivotIndex];

		int currIndex = lo;
		swap(unsorted, pivotIndex, hi);

		for (int i = lo; i < hi; i++) {
			if (unsorted[i] < pivotValue) {
				swap(unsorted, i, currIndex);
				currIndex++;
			}
		}
		swap(unsorted, currIndex, hi);
		return currIndex;
		

	}


	private static void swap(final int[] unsorted, int lo, int hi) {
		final int temp = unsorted[lo];
		unsorted[lo] = unsorted[hi];
		unsorted[hi] = temp;
	}
        
        public static void main(String args[]) {
		int[] test1 = {8, 5, 3, 6, 2, 4, 1, 7};
		int[] test2 = {9, 9, 9, 2, 2, 1, 0};
		int[] test3 = {-1, -2, -3};
		int[] test4 = {};

		System.out.println(Arrays.toString(quicksort(test1)));
		System.out.println(Arrays.toString(quicksort(test2)));
		System.out.println(Arrays.toString(quicksort(test3)));
		System.out.println(Arrays.toString(quicksort(test4)));
		
	}


}
