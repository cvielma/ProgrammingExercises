/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.LinkedList;
import java.util.List;

/**
* 2.5) You have two numbers represented by a linked list, where each node contains a single digit. The digits are stored in reverse order, such that the 1's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.

Example:
input: (7->1->6) + (5->9->2). That is, 617 + 295.
output: 2->1->9. That is, 912.

Follow up
Suppose the digits are stored in forward order. Repeat the above problem.
input: (6->1->7) + (2->9->5). that is, 617 + 295/
output: 9->1->2. that is, 912.
*/

public class LinkedListSum2d5 {

	public static List<Byte> sumLists(final LinkedList<Byte> list1, final LinkedList<Byte> list2) {
		final List<Byte> result = new LinkedList<>();
		final LinkedList<Byte> longList = list1.size() >= list2.size() ? list1 : list2;
		final LinkedList<Byte> shortList = list1.size() < list2.size() ? list1 : list2;
 
		final int size = longList.size();
		boolean remaining = false;
		for (int i = 0; i < size; i++) {
			byte shortNum = 0;
			byte longNum = longList.pop();	
			if (shortList.size() > 0) {
			  shortNum = shortList.pop();
			}
			byte newDigit = (byte) (shortNum + longNum);
			if (remaining) {
				newDigit++;
				remaining = false;			
			}			

			if (newDigit > 10) {
				remaining = true;
				newDigit -= 10;
			}
			result.add(newDigit);
		}

		return result;
		
	}

	public static String printList(List<Byte> list) {
		String result = "";		
		for (Byte num : list) {
			result += num + "->";
		}
		result += "|";

		return result;

	}
        
        public static List<Byte> sumListsForward(final LinkedList<Byte> list1, final LinkedList<Byte> list2) {
		return (List<Byte>) reverseList(sumLists(reverseList(list1), reverseList(list2)));

	}

	private static LinkedList<Byte> reverseList(final List<Byte> list) {
		LinkedList<Byte> newList = new LinkedList<>();
		for (Byte num : list) {
			newList.addFirst(num);
		}
		return newList;
	}

	public static void main(String args[]) {
		LinkedList<Byte> list1 = new LinkedList<>();
		LinkedList<Byte> list2 = new LinkedList<>();

		list1.add((byte) 7);
		list1.add((byte) 1);
		list1.add((byte) 6);

		list2.add((byte) 5);
		list2.add((byte) 9);
		list2.add((byte) 2);
		
		System.out.println(printList(sumLists(list1, list2)));
                
                list1 = new LinkedList<>();
                list2 = new LinkedList<>();
                
                list1.add((byte) 6);
		list1.add((byte) 1);
		list1.add((byte) 7);

		list2.add((byte) 2);
		list2.add((byte) 9);
		list2.add((byte) 5);
                
                System.out.println(printList(sumListsForward(list1, list2)));
                
                list1 = list2;
                System.out.println(list1 == list2);
	}

}
