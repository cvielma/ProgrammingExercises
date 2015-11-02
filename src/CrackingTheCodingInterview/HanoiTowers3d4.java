/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.Arrays;
import java.util.Stack;

/**
* In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of diffierent sizes which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size from top to bottom (i.e., each disk sits on top of an even larger one). You have the following contraints:
1) Only one disk can be moved at a time.
2) A disk is slid off the top of one tower onto the next tower.
3) A disk can only be placed on top of a larger disk.

Write a program to move the disks from the first tower to the last using stacks.
*/


public class HanoiTowers3d4 {
	public static void moveFromTo(Stack<Integer> first, Stack<Integer> last, Stack<Integer> temp, int disks) {
		if (disks == 0) {
			return;
		}
		if (disks == 1) {
			last.add(first.pop());
			return;
		}
		if (disks == 2) {
			temp.add(first.pop());
			last.add(first.pop());
			last.add(temp.pop());
			return;
		}
		if (disks > 2) {
			moveFromTo(first, temp, last, (disks -1));
			last.add(first.pop());
			moveFromTo(temp, last, first, (disks -1));
			return;
		}
	}

	public static Stack<Integer> createFirst(int[] list) {
		Stack<Integer> first = new Stack<>();
                for(int i : list) {
                    first.add(i);
                }
		return first;
	}

	public static void main(String args[]) {

		Stack<Integer> first = new Stack<>();
		Stack<Integer> last = new Stack<>();
		Stack<Integer> temp = new Stack<>();

		int[] list = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
		first = createFirst(list);

		moveFromTo(first, last, temp, list.length);

		System.out.println("First size: " + first.size() + ", temp size: " + temp.size() + ", last size: " + last.size());
		for(int i = 0; i < list.length; i++) {
			System.out.print(last.pop() + " ");
		}

		last = new Stack<>();
		temp = new Stack<>();

		int[] list1 = {4, 3, 2, 1};
		first = createFirst(list1);

		moveFromTo(first, last, temp, list1.length);
                System.out.println("");
		System.out.println("First size: " + first.size() + ", temp size: " + temp.size() + ", last size: " + last.size());
		for(int i = 0; i < list1.length; i++) {
			System.out.print(last.pop() + " ");
		}

		


		
	}
}
