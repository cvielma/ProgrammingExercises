/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 * https://leetcode.com/problems/reverse-linked-list-ii/
 *
 * @author cvielma
 */
public class ReverseList {

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

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode start = new ListNode(-1);
        ListNode pre = start;
        pre.next = head;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }
        
        ListNode last = pre.next;
        ListNode curr = pre;
        ListNode next = curr.next;
        ListNode prev = pre.next;
        for (int i = m; i <= n; i++) {
            curr = next;
            next = next.next;
            curr.next = prev;
            prev = curr;
        }
        
        pre.next = curr;
        last.next = next;
        
        return start.next;

}

public static ListNode newList() {
        ListNode list = new ListNode(1);
        list.next = new ListNode(2);
        list.next.next = new ListNode(3);
        list.next.next.next = new ListNode(4);
        list.next.next.next.next = new ListNode(5);
        list.next.next.next.next.next = new ListNode(6);
        return list;
    }
    
    public static void main(String[] args) {
        ListNode list = newList();
        System.out.println(list);
        
        //System.out.println(reverseBetween(list, 1, 2));// fails
        System.out.println(reverseBetween(list, 2, 2) + "---(2, 2)");
        
        list = newList();
        System.out.println(reverseBetween(list, 2, 3) + "---(2, 3)");
        
        list = newList();
        System.out.println(reverseBetween(list, 2, 4) + "---(2, 4)");
        
        list = newList();
        System.out.println(reverseBetween(list, 2, 5) + "---(2, 5)"); // fails
        
        list = newList();
        System.out.println(reverseBetween(list, 2, 6) + "---(2, 6)"); // fails
        
        list = newList();
        System.out.println(reverseBetween(list, 1, 6) + "---(1, 6)"); // fails
        
        
        
    }
}
