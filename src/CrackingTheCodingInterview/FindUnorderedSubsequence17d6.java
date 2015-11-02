/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.ArrayList;
import java.util.Collections;

/**
* Given an array of integers, write a method to find indices m and n such that if you sorted elements m through n, the entire array would be sorted. Minimize n-m (that is, find the smallest such sequence)
  EXAMPLE
	Input: 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
	Output: (3, 9)
*/

public class FindUnorderedSubsequence17d6 {
	
	static class Element implements Comparable<Element> {
		int value;
		int pos;

                @Override
		public int compareTo(Element elem) {
			if (elem != null) {
				return this.value - elem.value;
			}
			return 1;
		}
	}

	public static class Result {
		int start, end;
		
		public Result(int start, int end) {
			this.start = start;
			this.end = end;
		}

		public String toString() {
			return "(" + start + "," + end + ")";
		}
	}

	public static Result findUnorderedSubsequence(final int[] array) {
		ArrayList<Element> parseList = new ArrayList<>();
		for (int i = 0; i < array.length; i++) {
			Element elem = new Element();
			elem.value = array[i];
			elem.pos = i;
			parseList.add(elem);
		}
		Collections.sort(parseList);

		int min = -1;
		int max = -1;
		for (int i = 0; i < parseList.size(); i++) {
			Element elem = parseList.get(i);
			if (elem.pos != i) {
				if (min == -1) {
					min = i;
				}
				else {
					max = i;
				}
			}
		}

		return new Result(min, max);
	}

	public static void main(String[] args) {
		int[] array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};

		System.out.println(findUnorderedSubsequence(array));
	}

}