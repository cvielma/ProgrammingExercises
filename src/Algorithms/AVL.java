/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cvielma
 */
public class AVL {

    AVLNode root;

    public class AVLNode {

        int value;
        AVLNode left, right;

        public AVLNode() {
        }

        public AVLNode(final int value) {
            this.value = value;
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
            sb.append(value);
            sb.append(branch);
            if (left != null) {
                printNode(sb, left, level, "(L)");
            }

            if (right != null) {
                printNode(sb, right, level, "(R)");
            }
        }

        private void printNode(StringBuilder sb, AVLNode node, int level, String branch) {
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
    ///////////////////////////////////////////////////////////////////////////////

    public AVLNode search(final int value) {
        return search(root, value);
    }

    private AVLNode search(final AVLNode node, final int value) {
        if (node == null) {
            return null;
        } else {
            if (node.value == value) {
                return node;
            } else if (value < node.value) {
                return search(node.left, value);
            } else {
                return search(node.right, value);
            }
        }
    }

    public void insert(final int value) {
        root = insert(root, value);
    }

    private AVLNode insert(final AVLNode node, final int value) {
        if (node == null) {
            return new AVLNode(value);
        } else {
            if (value < node.value) {
                AVLNode left = insert(node.left, value);
                node.left = left;
            } else {
                AVLNode right = insert(node.right, value);
                node.right = right;
            }
        }
        return balanceTree(node);
    }

    public AVLNode balanceTree(final AVLNode node) {
        if (node == null) 
            return null;
        
        if (getHeightDiff(node) < -1) { // unbalanced to the right
            if (getHeightDiff(node.right) > 0) { // the left child of current's node right child is the one unbalancing the tree
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            } else {
                return rotateLeft(node);
            }
        } else if (getHeightDiff(node) > 1) { // unbalanced to the left
            if (getHeightDiff(node.left) < 0) { // the right child of current's node left child is the one unbalancing the tree
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            } else {
                return rotateRight(node);
            }
        }
        return node;
    }

    private int getHeightDiff(final AVLNode node) {
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return left - right;
    }

    private AVLNode rotateRight(final AVLNode node) {
        AVLNode temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    private AVLNode rotateLeft(final AVLNode node) {
        AVLNode temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    public boolean isBalanced(final AVLNode node) {
        return Math.abs(getHeightDiff(node)) <= 1;
    }

    public void delete(final int value) {
        root = balanceTree(delete(root, value));
    }

    private AVLNode delete(final AVLNode node, final int value) {
        if (node != null) {
            if (node.value == value) {
                // No children
                if (node.left == null && node.right == null) {
                    return null;
                } else if (node.left != null && node.right == null) { // Only left child
                    return node.left;
                } else if (node.right != null && node.left == null) { // Only right child
                    return node.right;
                } else {
                    int temp = node.left.value;
                    node.left.value = node.value;
                    node.value = temp;
                    node.left = delete(node.left, value);
                    return node;
                }
            } else if (value < node.value) {
                node.left = delete(node.left, value);
                return node;
            } else {
                node.right = delete(node.right, value);
                return node;
            }
        }
        return null;
    }

    public int getHeight(final AVLNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public List<AVLNode> inOrder() {
        List<AVLNode> inOrder = new LinkedList<>();
        traverseInOrder(inOrder, root);
        return inOrder;
    }

    public void traverseInOrder(final List<AVLNode> result, final AVLNode node) {
        if (node != null) {
            traverseInOrder(result, node.left);
            result.add(node);
            traverseInOrder(result, node.right);
        } else {
            //result.add(null);
        }
    }

    public List<AVLNode> preOrder() {
        List<AVLNode> inOrder = new LinkedList<>();
        traversePreOrder(inOrder, root);
        return inOrder;
    }

    public void traversePreOrder(final List<AVLNode> result, final AVLNode node) {
        if (node != null) {
            result.add(node);
            traversePreOrder(result, node.left);
            traversePreOrder(result, node.right);
        } else {
            result.add(null);
        }
    }

    public String toPreOrderString() {
        List<AVLNode> preOrder = preOrder();
        StringBuilder sb = new StringBuilder();
        for (AVLNode avl : preOrder) {
            sb.append(avl == null ? "-" : avl.value);
            sb.append(",");
        }

        return sb.toString();
    }

    public String toInOrderString() {
        List<AVLNode> inOrder = inOrder();
        StringBuilder sb = new StringBuilder();
        for (AVLNode avl : inOrder) {
            sb.append(avl == null ? "-" : avl.value);
            sb.append(",");
        }

        return sb.toString();
    }

    public String toString() {
        return root == null ? "" : root.toString();
    }

    public static void main(String[] args) {
        AVL tree = new AVL();
        int[] nodes = new int[]{10, 15, 6, 3, 8, 11};

        for (int node : nodes) {
            tree.insert(node);
        }

//        System.out.println(tree);
//        System.out.println("HEIGHT: " + tree.getHeight(tree.root));

//        System.out.println("SEARCH:");
//        System.out.println(tree.search(6));

//        System.out.println("DELETE (6):");
        tree.delete(6);
//        System.out.println(tree);
//        System.out.println("INSERT (6):");
        tree.insert(6);
//        System.out.println(tree);
//        System.out.println("DELETE (15):");
        tree.delete(15);
//        System.out.println(tree);
//        System.out.println("HEIGHT: " + tree.getHeight(tree.root));

        nodes = new int[]{-1,0,-2, 1, 2, 3};
        for (int node : nodes) {
            tree.insert(node);
        }
        
        for (int node : nodes) {
            tree.delete(node);
        }
        System.out.println(tree);
        

    }
}
