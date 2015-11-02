/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 * Lowest Common Ancestor
PROBLEM Given the value of two nodes in a binary search tree, find the lowest
(nearest) common ancestor. You may assume that both values already exist in
the tree.
For example, using the tree shown in Figure 5-7, assume 4 and 14 are the two
given nodes. The lowest common ancestor would be 8 because it’s an ancestor to
both 4 and 14, and there is no node lower on the tree that is an ancestor to both 4
and 14.
 */
public class FindLowestCommonAncestor {

    public static class TreeNode {

        int value;
        TreeNode left, right;
        
        public TreeNode(final int value) {
            this.value = value;
        }
        
        public String toString() {
            return "{" + value + "}";
        }
    }

    // null
    // v1
    // v2
    // other
    // same value as foundLeft --> means tree has duplicates
    
    //FORGOT TO CHECK CASE ONE OF THE NODES IS NOT IN THE TREE.
    public static TreeNode findCommonAncestor(final TreeNode root, final int v1, final int v2) {
        if (root == null) {
            return null;
        }

        TreeNode foundLeft = findCommonAncestor(root.left, v1, v2);
        if (checkFound(foundLeft, v1, v2)) {
            return foundLeft;
        }

        TreeNode foundRight = findCommonAncestor(root.right, v1, v2);
        if (checkFound(foundRight, v1, v2)) {
            return foundRight;
        }

        if (foundLeft == null && foundRight == null) {
            if (root.value == v1 || root.value == v2) {
                return root;
            }
            return null;
        }

        if (foundLeft != null && foundRight != null) {
            if (foundLeft.value != foundRight.value) {
                return root;
            } else {
                if ((root.value == v1 || root.value == v2) && foundLeft.value != root.value) {
                    return root; // case: one node is parent of the other.
                } else {
                    return foundLeft; // or right, doesn't mind. This means duplicates.
                }
            }
        }

        return foundLeft == null ? foundRight : foundLeft;
    }

    public static boolean checkFound(final TreeNode root, final int v1, final int v2) {
        return root != null && root.value != v1 && root.value != v2;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        
        System.out.println(findCommonAncestor(root, 4, 14));
    }
}