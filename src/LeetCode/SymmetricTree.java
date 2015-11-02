/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/problems/symmetric-tree/
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 *
 * For example, this binary tree is symmetric:
 *
 * 1
 * / \
 * 2 2
 * / \ / \
 * 3 4 4 3
 *
 * But the following is not:
 *
 * 1
 * / \
 * 2 2
 * \ \
 * 3 3
 *
 * Note: Bonus points if you could solve it both recursively and iteratively.
 *
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized
 * on OJ.
 *
 * @author cvielma
 */
public class SymmetricTree {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        public String toString() {
            return "{" + val + "}";
        }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        
        Stack<TreeNode> stackLeft = new Stack<>();
        pushIntoInOrder(root.left, stackLeft);
        
        Stack<TreeNode> stackRight = new Stack<>();
        pushIntoInOrder(root.right, stackRight);

        while (!stackLeft.isEmpty() && !stackRight.isEmpty()) {
            if (stackLeft.pop().val != stackRight.pop().val) {
                return false;
            }
        }
        
        return stackLeft.isEmpty() && stackRight.isEmpty();
    }

    private void pushIntoInOrder(TreeNode root, Stack<TreeNode> stack) {
        if (root != null) {
            if (root.left != null) {
                pushIntoInOrder(root.left, stack);
            } else if (root.left == null && root.right != null) {
                stack.push(new TreeNode(Integer.MIN_VALUE));
            }

            stack.push(root);

            if (root.right != null) {
                pushIntoInOrder(root.right, stack);
            } else if (root.right == null && root.left != null) {
                stack.push(new TreeNode(Integer.MIN_VALUE));
            }
        }
    }

    public static void main(String[] args) {
        SymmetricTree treeChecker = new SymmetricTree();
        TreeNode tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.left.left = new TreeNode(3);
        tree.left.right = new TreeNode(4);
        tree.right.left = new TreeNode(4);
        tree.right.right = new TreeNode(3);

        System.out.println(treeChecker.isSymmetric(tree));

        tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(2);
        tree.left.right = new TreeNode(3);
        tree.right.right = new TreeNode(3);
        treeChecker = new SymmetricTree();

        System.out.println(treeChecker.isSymmetric(tree));

        tree = new TreeNode(1);
        tree.left = new TreeNode(2);
        tree.right = new TreeNode(3);
        tree.left.left = new TreeNode(3);
        tree.right.left = new TreeNode(2);
        treeChecker = new SymmetricTree();

        System.out.println(treeChecker.isSymmetric(tree));
    }

}
