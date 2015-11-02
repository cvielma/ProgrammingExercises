/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.ArrayList;
import java.util.List;
import static sun.awt.geom.Curve.prev;

/**
 *
 * @author cvielma
 */
public class InPostOrder {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
        
        /**
         * Just for testing and debugging purposes
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            this.toString(sb, 0, "");
            return sb.toString();
        }

        /**
         * The next two methods help printing the tree nicely on console.         
         */
        private void toString(StringBuilder sb, int level, String branch) {
            for (int i = 0; i < level; i++) {
                sb.append("-");
            }
            sb.append(val);
            sb.append(branch);
            if (left != null) {
                printNode(sb, left, level, "(L)");
            }

            if (right != null) {
                printNode(sb, right, level, "(R)");
            }
        }

        private void printNode(StringBuilder sb, TreeNode node, int level, String branch) {
            sb.append("\n");
            for (int i = 0; i < level * 3; i++) {
                sb.append(" ");
            }
            sb.append("|\n");
            for (int i = 0; i < level * 3; i++) {
                sb.append(" ");
            }
            sb.append("|");
            node.toString(sb, level + 1, branch);
        }
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode root = null;
        List<Integer> prevs = new ArrayList<>();
        int curr = -1;
        for (int i = postorder.length - 1; i >= 0; i--) {
            curr = findPos(inorder, postorder[i]);
            if (curr < 0) {
                return null; // error
            } else {
                if (prevs.isEmpty()) {
                    root = new TreeNode(postorder[i]);
                } else {
                    int prev = getPrev(prevs, curr);
                    if (curr - prev > 0) {
                        insert(root, false, inorder[prev], postorder[i]);
                    } else {
                        insert(root, true, inorder[prev], postorder[i]);
                    }
                }
                prevs.add(curr);
            }
        }

        return root;
    }

    private static int getPrev(List<Integer> prevs, int pos) {
        int minDiff = Integer.MAX_VALUE;
        int result = -1;
        for (int prev : prevs) {
            int currVal = Math.abs(prev - pos);
            if (currVal <= minDiff) {
                minDiff = currVal;
                result = prev;
            }
        }
        
        return result;
    }
    
    private static int findPos(int[] array, int elem) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elem) {
                return i;
            }
        }

        return -1;
    }

    private static boolean insert(TreeNode root, boolean left, int parent, int value) {
        if (root == null) {
            return false;
        }

        if (root.val == parent) {
            if (left == true) {
                root.left = new TreeNode(value);
            } else {
                root.right = new TreeNode(value);
            }
            return true;
        } else {
            boolean inserted = insert(root.left, left, parent, value);

            if (!inserted) {
                inserted = insert(root.right, left, parent, value);
            }

            return inserted;
        }
    }
    
    public static void main(String[] args) {
        int[] inorder = {2, 3, 1};
        int[] postorder = {3, 2, 1};
        
        System.out.println(buildTree(inorder, postorder));
    }
}
