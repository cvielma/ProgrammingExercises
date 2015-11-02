package Algorithms;


import java.util.Arrays;

/**
* Heapsort
*/

public class Heapsort {

	public static void heapsort(final int[] array) {
		
		heapify(array);

                int end = array.length -1;
		while(end > 0) {
			swap(array, 0, end);
                        end--;
			siftDown(array, 0, end);
		}

	}

	private static void heapify(final int[] array) {
		for(int i = (array.length -2)/2; i >= 0; i--) {
			siftDown(array, i, array.length -1);
		}

	}

	private static void swap(final int[] array, final int start, final int end) {
		int temp = array[end];
		array[end] = array[start];
		array[start] = temp;
	}

	private static void siftDown(final int[] array, final int start, final int end) {
		int root = start;
		while (root*2 + 1 <= end) {
			int child = root * 2 + 1; 
			int swap = root;

			if (array[swap] < array[child]) {
				swap = child;			
			}
			if (child + 1 <= end && array[swap] < array[child+1]) {
				swap = child + 1;
			}

			if (swap == root) {
				return;				
			} else {
				swap(array, root, swap);
				root = swap;
			}
			
		}
	}

	public static void main(String[] args) {
		int[] array0 = {7, 2, 1, 3};
		int[] array1 = {9, 2, 1, 0, 5, 3, 2, 4, 5, 7, 6};
		int[] array2 = {};

		heapsort(array0);
		heapsort(array1);
		heapsort(array2);
		
		System.out.println(Arrays.toString(array0));
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
	}


}