/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
 * List flattening (Code interview Exposed). (4.3)
 */
public class ListFlattening {

    public static class Node {

        Node next, prev, child;
        int value;

        public Node(final int value) {
            this.value = value;
        }

        public boolean hasChild() {
            return child != null;
        }

        @Override
        public String toString() {
            return "{" + this.value + " - [next: " + (this.next != null ? this.next.value : null)
                    + "] [prev: " + (this.prev != null ? this.prev.value : null)
                    + "] [child: " + (this.child != null ? this.child.value : null) + "}";
        }
    }

    public static class ListNode {

        Node head, tail;

        public ListNode() {
        }

        public ListNode(final Node head, final Node tail) {
            this.head = head;
            this.tail = tail;
        }
        
        public void add(final Node node) {
            if (this.head == null && this.tail == null) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
                this.tail = node;
            }           
        }
        
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = head;
            while (curr != null) {
                sb.append(curr.toString()).append(" -> ");
                curr = curr.next;
            }
            
            sb.append("null");
            return sb.toString();
            
        }
    }

    public static ListNode flattenList(final ListNode list) {
        if (list == null) {
            return null;
        }

        Node curr = list.head;
        Node next = curr != null ? curr.next : null;


        while (next != null) {
            if (curr.hasChild()) {
                ListNode sublist = flattenList(new ListNode(curr.child, curr.child));
                curr.next = sublist.head;
                sublist.head.prev = curr;
                sublist.tail.next = next;
                next.prev = sublist.tail;
                curr.child = null;
            }
            curr = next;
            next = curr.next;
        }

        ListNode result = new ListNode();
        result.head = list.head;
        result.tail = curr;
        return result;
    }
    
    public static void main(String[] args) {
       ListNode myTest = new ListNode();
       Node child1 = new Node(5);
       child1.child = new Node(3);
       
       child1.child.child = new Node(2);
       
       child1.child.next = new Node(1);
       child1.child.next.prev = child1.child.next;
       myTest.add(child1);
       myTest.add(new Node(4));
       
       System.out.println(myTest);
        System.out.println(flattenList(myTest)); 
    }
}
