/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 * Proposed solution to find the largest Binary Search Tree in a Binary Tree.
 *
 * Traversing the tree from top down will require to check at each node if the
 * tree starting on that node is bst. This would have a time complexity of
 * O(n^2) where n is the number of nodes in the tree.
 *
 * The most efficient approach is to traverse the tree bottom up. This makes
 * checking for the bst at each node in constant time or O(1). The cons of this
 * solution is that we have to traverse the tree top down (fast) and then bottom
 * up passing up some parameters to the parent nodes that will allow the parent
 * nodes to check for bst in constant time. This will require to visit each node
 * exactly twice, so the time complexity will be O(2n) = O(n).
 *
 * Implementing this algorithm recursively will have a space complexity of O(n)
 * because of the recursive calls. The original passed tree will be modified while
 * finding the bst so no extra space will be needed.
 * 
 * Assuming a tree with one node is a BST.
 * 
 *
 *
 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
//TODO: try it iteratively or by tail recursion.
public class LargestBst {

    /**
     * Binary Tree Class
     */
    public static class TreeNode {

        /**
         * assuming: the value of the node will be int there are no "leaves" in
         * the tree
         */
        Integer value; //TODO: how to manage 0;
        TreeNode left;
        TreeNode right;

        /**
         * Not implementing constructor, getters or setters for simplicity in
         * this case Following OO value, left and right should be private and be
         * accessed only through getters and setters.
         */
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            this.toString(sb, 0, "");
            return sb.toString();
        }

        public void toString(StringBuilder sb, int level, String branch) {
            for (int i = 0; i < level; i++) {
                sb.append("-");
            }
            sb.append(value);
            sb.append(branch);
            if (left != null) {
                printNode(sb, left, level, "(L)");                
            }

            if (right != null) {
                printNode(sb, right, level, "(R)");                
            }
        }

        public void printNode(StringBuilder sb, TreeNode node, int level, String branch) {
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

    /**
     * This class is a wrapper (or decorator if methods were implemented) for a
     * TreeNode to contain extra information about the current node, needed to
     * determine the largestBST while going up. This is because a Java's
     * limitation to only return one type.
     */
    private static class TreeNodeHelper {

        TreeNode node;
        int nodes; //number of nodes
        Integer maxValue;
        Integer minValue;
        boolean isBst;

        public TreeNodeHelper() {
        }

        public TreeNodeHelper(TreeNode node, int nodes, Integer maxValue, Integer minValue, boolean isBst) {
            this.node = node;
            this.nodes = nodes;
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.isBst = isBst;

        }
    }
    
    public static TreeNode getLargestBST(TreeNode tree){
        TreeNodeHelper helper = isBST(tree);
        if (helper == null) {
            return null; //or error
        } else if (helper.nodes > 0) {
            return helper.node;
        } else {
            return null;
        }
    }

    /**
     *
     */
    private static TreeNodeHelper isBST(TreeNode tree) {
        if(tree == null){
            return null; //or error depending on what's expected
        }
        if (tree.left == null && tree.right == null) {
            TreeNodeHelper helper = new TreeNodeHelper(tree, 1, tree.value, tree.value, true);
            return helper;
        } else if (tree.left == null && tree.right != null) { //if only has right leaf
            TreeNodeHelper rightBst = isBST(tree.right);
            if (rightBst.isBst) { 
                if (rightBst.minValue > tree.value) {
                    return new TreeNodeHelper(tree, ++rightBst.nodes, rightBst.maxValue, tree.value, true);
                } else {
                    rightBst.isBst = false; //TODO: try to make this more clear. It is like this to avoid a leaf to concat with wrong parent
                    return rightBst;
                }
            } else {
                return rightBst;
            } 
        } else if (tree.right == null && tree.left != null) {
            TreeNodeHelper leftBst = isBST(tree.left);
            if(leftBst.isBst){
                if (leftBst.maxValue < tree.value) {
                    return new TreeNodeHelper(tree, ++leftBst.nodes, tree.value, leftBst.minValue, true);
                } else {
                    leftBst.isBst = false; //TODO: make it clear
                    return leftBst;
                }
            } else {
                return leftBst;
            }            
        } else {
            TreeNodeHelper rightBst = isBST(tree.right);
            TreeNodeHelper leftBst = isBST(tree.left);
            if (rightBst.isBst && leftBst.isBst) {
                if (leftBst.maxValue < tree.value && rightBst.minValue > tree.value) {
                    return new TreeNodeHelper(tree, leftBst.nodes+rightBst.nodes+1, rightBst.maxValue, leftBst.minValue, true);
                } else {
                    leftBst.isBst = false;
                    rightBst.isBst = false;
                    return leftBst.nodes >= rightBst.nodes ? leftBst : rightBst; //TODO: this results in an assumption that when given 2 bsts gets the left one 
                }                
            } else {
                if (leftBst.isBst) {
                    leftBst.isBst = false;
                    return leftBst;
                } else {
                    rightBst.isBst = false;
                    return rightBst;
                }                
            }
        }
    }

    protected static TreeNode createTree(Integer[] nodes, int i) {
        if (i >= nodes.length || nodes[i] == null) {
            return null;
        }
        TreeNode node = new TreeNode();
        node.value = nodes[i];
        node.left = createTree(nodes, 2 * i + 1);
        node.right = createTree(nodes, 2 * i + 2);

        return node;
    }

    public static void main(String[] args) {
        Integer[] treeA = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        TreeNode result = getLargestBST(tree);
        System.out.println("1 - Largest BST: \n"+result);
        
        
        treeA = new Integer[]{8, 3, 4, 2, 4, 9, 7, 1, null, null, null, 8, 9};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("2 - Largest BST: \n"+result);
        
        treeA = new Integer[]{null};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("3 - Largest BST: \n"+result);
        
        treeA = new Integer[]{1};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("4 - Largest BST: \n"+result);
        
        treeA = new Integer[]{9, 4, 14, 2, 6, 12, 16, 1, 3, 5, 7, 10, 15, 22, 15, -1, null, null, null, null, null, null, 8};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("5 - Largest BST: \n"+result);
        
        treeA = new Integer[]{14,12, 16,10, 15, 22, 15};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("6 - Largest BST: \n"+result);
        
        
        treeA = new Integer[]{4,2, 6,1, 3, 5, 7, -1, null, null, null, null, null, null, 8};
        tree = createTree(treeA, 0);
        System.out.println("\n---\n"+tree);
        result = getLargestBST(tree);
        System.out.println("7 - Largest BST: \n"+result);
        
        

    }
}
