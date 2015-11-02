package Algorithms;


import Util.AssortedMethods;
import java.util.Arrays;




public class MergeSort {	
	public static void mergesort(int[] array) {
		int[] helper = new int[array.length];
		mergesort(array, helper, 0, array.length - 1);
	}

	public static void mergesort(int[] array, int[] helper, int low, int high) {
//		          System.out.println("MERGESORT: low="+low+", high="+high);
                if (low < high) {
			int middle = (low + high) / 2;
//                        System.out.println("MERGESORT2: middle="+middle);
			mergesort(array, helper, low, middle); // Sort left half
			mergesort(array, helper, middle+1, high); // Sort right half
//                        System.out.println("ARRAY="+Arrays.toString(array)+", HELPER="+Arrays.toString(helper));
			merge(array, helper, low, middle, high); // Merge them
		}
	}

	public static void merge(int[] array, int[] helper, int low, int middle, int high) {
		/* Copy both halves into a helper array */
                System.out.println("MERGE: low="+low+", middle="+middle+", high="+high+", array="+Arrays.toString(array)+", helper="+Arrays.toString(helper));
                
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

                System.out.println("MERGE2: helper:"+Arrays.toString(helper));
		int helperLeft = low;
		int helperRight = middle + 1;
		int current = low;

		/* Iterate through helper array. Compare the left and right
		 * half, copying back the smaller element from the two halves
		 * into the original array. */
		while (helperLeft <= middle && helperRight <= high) {
                        System.out.println("MERGEWHILE: helperleft="+helperLeft+", helperRight="+helperRight+", current="+current);
			if (helper[helperLeft] <= helper[helperRight]) {
				array[current] = helper[helperLeft];
				helperLeft++;
			} else { // If right element is smaller than left element
				array[current] = helper[helperRight];
				helperRight++;
			}
			current++;
                        System.out.println("MERGEWHILE2: helper="+Arrays.toString(helper));
                        System.out.println("MERGEWHILE2: array="+Arrays.toString(array));
		}

                System.out.println("MERGEAFTERWHILE: helper="+Arrays.toString(helper));
                System.out.println("MERGEAFTERWHILE: array="+Arrays.toString(array));
		/* Copy the rest of the left side of the array into the
		 * target array */
		int remaining = middle - helperLeft;
                System.out.println("MERGE BEFOREFINAL: current="+current+", middle="+middle+", helperLeft="+helperLeft+", remaining="+remaining);
		for (int i = 0; i <= remaining; i++) {
			array[current + i] = helper[helperLeft + i];
		}
                System.out.println("MERGEFINAL: helper="+Arrays.toString(helper));
                System.out.println("MERGEFINAL: array="+Arrays.toString(array));
                
	}
	
	public static void main(String[] args) {
		int size = 7;
		int[] array = new int[] {3,1,2,4,5,7,6}; //AssortedMethods.randomArray(size, 0, size - 1);
		int[] validate = new int[size];
		AssortedMethods.printIntArray(array);
//		for (int i = 0; i < size; i++) {
//                    	validate[array[i]-1]++;
//		}
		mergesort(array);
//		for (int i = 0; i < size; i++) {
//			validate[array[i]-1]--;
//		}		
		AssortedMethods.printIntArray(array);
		for (int i = 0; i < size; i++) {
			if (validate[i] != 0 || (i < (size-1) && array[i] > array[i+1])) {
				System.out.println("ERROR");
			}
		}
	}

}
