/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

/**
 *
 * @author cvielma
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Algorithms.AVL;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author cvielma
 */
public class BST {

    BSTNode root;

    public class BSTNode {

        int value;
        BSTNode left, right;

        public BSTNode() {
        }

        public BSTNode(final int value) {
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

        private void printNode(StringBuilder sb, BSTNode node, int level, String branch) {
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

    public BSTNode search(final int value) {
        return search(root, value);
    }

    private BSTNode search(final BSTNode node, final int value) {
        if (node == null) {
            return null;
        } else {
            if (node.value == value) {
                return node;
            } else if (value < node.value) {
                return search(node.left, value);
            } else if (value >= node.value) {
                return search(node.right, value);
            } else {
                return null;
            }
        }
    }

    public void insert(final int value) {
        root = insert(root, value);
    }

    private BSTNode insert(final BSTNode node, final int value) {
        if (node == null) {
            return new BSTNode(value);
        } else {
            if (value < node.value) {
                BSTNode left = insert(node.left, value);
                node.left = left;
            } else {
                BSTNode right = insert(node.right, value);
                node.right = right;
            }
        }
        return node;
    }

    public void delete(final int value) {
        delete(root, value);
    }
    
    private BSTNode delete(final BSTNode node, final int value) {
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
            }
        }
        return null;
    }

    public List<BSTNode> inOrder() {
        List<BSTNode> inOrder = new LinkedList<>();
        traverseInOrder(inOrder, root);
        return inOrder;
    }

    public void traverseInOrder(final List<BSTNode> result, final BSTNode node) {
        if (node != null) {
            traverseInOrder(result, node.left);
            result.add(node);
            traverseInOrder(result, node.right);
        } else {
            //result.add(null);
        }
    }
    
    public List<BSTNode> preOrder() {
        List<BSTNode> inOrder = new LinkedList<>();
        traversePreOrder(inOrder, root);
        return inOrder;
    }

    public void traversePreOrder(final List<BSTNode> result, final BSTNode node) {
        if (node != null) {
            result.add(node);
            traversePreOrder(result, node.left);
            traversePreOrder(result, node.right);
        } else {
            result.add(null);
        }
    }

    public String toPreOrderString() {
        List<BSTNode> preOrder = preOrder();
        StringBuilder sb = new StringBuilder();
        for (BSTNode avl : preOrder) {
            sb.append(avl == null ? "-" : avl.value);
            sb.append(",");
        }

        return sb.toString();
    }
    
    public String toInOrderString() {
        List<BSTNode> inOrder = inOrder();
        StringBuilder sb = new StringBuilder();
        for (BSTNode avl : inOrder) {
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

        System.out.println(tree);
        
        System.out.println("Search:");
        System.out.println(tree.search(6));

    }
}

