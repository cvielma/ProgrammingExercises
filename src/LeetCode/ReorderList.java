/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

/**
 *
 * @author cvielma
 */
public class ReorderList {

    public static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        public String toString() {
            return val + "->" + next;
        }
    }

    public static void reorderList(ListNode head) {
        int length = getLength(head);
        int count = 0;
        if (length % 2 == 0) {
            count = length / 2 - 1;
        } else {
            count = length / 2;
        }

        ListNode curr = head;
        ListNode next = curr != null ? curr.next : null;
        ListNode secondToLast = getSecondToLast(curr);
        ListNode last = secondToLast != null ? secondToLast.next : null;

        while (count > 0 && last != null) {
            curr.next = last;
            last.next = next;
            secondToLast.next = null;
            curr = next;
            next = next.next;
            secondToLast = getSecondToLast(curr);
            last = secondToLast != null ? secondToLast.next : null;
            count--;
        }
    }

    public static int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }

        int count = 1;
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
            count++;
        }

        return count;

    }

    public static ListNode getSecondToLast(ListNode curr) {
        ListNode result = curr;
        while (result != null && result.next != null && result.next.next != null) {
            result = result.next;
        }

        return result;
    }
    
    public static ListNode newList() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        list.next.next.next.next.next = new ListNode(6);
        list.next.next.next.next.next.next = new ListNode(7);
        list.next.next.next.next.next.next.next = new ListNode(8);
        list.next.next.next.next.next.next.next.next = new ListNode(9);
        return list;
    }
    
    public static void main(String[] args) {
        ListNode list = newList();
        System.out.println(list);
        
        reorderList(list);
        System.out.println(list);
        
    }
}
