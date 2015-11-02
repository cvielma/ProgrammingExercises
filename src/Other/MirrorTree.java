/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

/**
 *
 * @author cvielma
 */
public class MirrorTree {

    public static class TreeNode {

        int value;
        TreeNode left, right;
        
        public TreeNode(int value) {
            this.value = value;
        }
        
        public String toString() {
            return "" +value;
        }
    }

    public TreeNode leftPointer, rightPointer;

    public static boolean isMirror(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    public static boolean isMirror(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }

        if (areEquals(left.left, right.right) && areEquals(left.right,right.left)) {
            return isMirror(left.left, right.right) && isMirror(left.right, right.left);
        }

        return false;
    }
    
    public static boolean areEquals(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        } else if ((left == null && right != null) || (right == null && left != null)) {
            return false;
        } else {
            return left.value == right.value;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);
        
        root.left.left.right = new TreeNode(4);
        root.right.right.right = new TreeNode(4);
        
        System.out.println(isMirror(root));
        
    }
}
