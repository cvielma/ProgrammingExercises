/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.LinkedList;
import java.util.List;

/**
* 2.4) Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equals to x.
*/

public class PartitionList2d4 {

	public static List<Integer> partition(final LinkedList<Integer> list, final int x) {
		LinkedList<Integer> lesser = new LinkedList<>();
		LinkedList<Integer> greater = new LinkedList<>();
		boolean found = false;
		
		int length = list.size();
		for (int i = 0; i < length; i++) {
			int currVal = list.remove(0);
			if (currVal < x) {
				lesser.add(currVal);			
			} else if (!found && currVal == x) {
				found = true;
			} else {
				greater.add(currVal);
			}
		}

		if (found) {
			lesser.add(x);
		}
		lesser.addAll(greater);
		return lesser;
	}

	public static String listToString(List list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append("->");
		}
		sb.append("|");
		return sb.toString();
	}


	public static void main(String args[]) {

		LinkedList<Integer> list = new LinkedList<>();
		list.add(5);
                list.add(3);
                list.add(2);
                list.add(4);
                list.add(1);
                list.add(4);
                list.add(6);
		System.out.println(partition(list, 4));

		list = new LinkedList<>();
		list.add(1);
                list.add(1);
                list.add(1);
                list.add(1);
                list.add(1);
                list.add(1);
                list.add(1);
		System.out.println(partition(list, 1));
	}
}
