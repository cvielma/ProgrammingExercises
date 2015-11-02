/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

/**
* Given a circular linked list, implement an algorithm which returns the node at the beginning of the loop.
  DEFINITION:
  Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so as to make a loop in the linked list.
  EXAMPLE
  Input: A -> B -> C -> D -> E -> C [the same C as earlier]
  Output: C
*/

public class CircularLinkedList2d6 {
	
	static class Node {
		Node next;
		int value;
	}

	public static Node findLoopStartingNode(final Node node) {
		try {
			Node fast = node.next.next;
			Node slow = node.next;
			
			while (fast != slow) {
				slow = slow.next;
				fast = fast.next.next;
			}

			// Found loop. If not it would have thrown a NullPointerException
			slow = node;
			while (fast != slow) {
				fast = fast.next;
				slow = slow.next;
			}

			return slow;
			
		} catch (final NullPointerException ex) {
			return null;
		}	

	}

	public static Node createLinkedList(final int[] values) {
		Node root = new Node();
		Node curr = root;
		Node previous = null;
		for (int i : values) {
			if (previous != null) {
			  curr = new Node();
			  previous.next = curr;			
			}
			curr.value = i;
			previous = curr;			
		}

		Node loopStartNode = root;
		final int loopIndex = values.length / 2 -1;
		for (int i = 0; i < loopIndex; i++) {
			loopStartNode = loopStartNode.next;
		} 
		curr.next = loopStartNode;
		return root;
	}

	public static void main(String args[]) {
		int[] list1 = {1, 2, 3, 4, 5, 6, 7, 8};
		int[] list2 = {1, 1, 3, 2, 2, 2};
                int[] list3 = {1, 2, 3};

		Node node1 = createLinkedList(list1);
		Node node2 = createLinkedList(list2);
                Node node3 = createLinkedList(list3);
                
//                Node curr = node1;
//                for(int i = 0; i<=list1.length; i++){
//                    System.out.println(curr.value);
//                    curr = curr.next;
//                }

		System.out.println(findLoopStartingNode(node1).value);
		System.out.println(findLoopStartingNode(node2).value);		
                System.out.println(findLoopStartingNode(node3).value);		
	
	}

}
