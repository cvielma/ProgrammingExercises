package Algorithms;

import java.util.Arrays;

/**
* Mergesort
*/

public class MergesortMine {

	public static int[] mergesort(final int[] unsorted) {
                if (unsorted.length > 0) {
                    return split(unsorted, 0, unsorted.length -1);
                } else {
                    return unsorted;
                }
	}

	private static int[] split(final int[] unsorted, final int start, final int end) {
		if (start == end) {
			int[] result = new int[1];
			result[0] = unsorted[start];
			return result;
		} else {
                        int middle = (start + end) /2;
			int[] half1 = split(unsorted, start, middle);
			int[] half2 = split(unsorted, middle +1, end);
			return merge(half1, half2);
		}
	}

	private static int[] merge(final int[] half1, final int[] half2) {
		int i1 = 0;
		int i2 = 0;
		int i3 = 0;
		int[] result = new int[half1.length + half2.length];
		while (i1 < half1.length && i2 < half2.length) {
			if (half1[i1] < half2[i2]) {
				result[i3] = half1[i1];
				i1++;
			} else {
				result[i3] = half2[i2];			
				i2++;
			}
			i3++;
		}

		if (i1 == half1.length) {
			for(int i = i2; i < half2.length; i++) {
				result[i3] = half2[i];
				i3++;	
			}
		} else {
			for(int i = i1; i < half1.length; i++) {
				result[i3] = half1[i];
				i3++;	
			}
		}
		return result;

	}

	public static void main(String args[]) {
		int[] test1 = {8, 5, 3, 6, 2, 4, 1, 7};
		int[] test2 = {9, 9, 9, 2, 2, 1, 0};
		int[] test3 = {-1, -2, -3};
		int[] test4 = {};

		System.out.println(Arrays.toString(mergesort(test1)));
		System.out.println(Arrays.toString(mergesort(test2)));
		System.out.println(Arrays.toString(mergesort(test3)));
		System.out.println(Arrays.toString(mergesort(test4)));
		
	}
}