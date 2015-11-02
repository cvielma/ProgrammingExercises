/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 *  
1) Every node has a color either red or black.

2) Root of tree is always black.

3) There are no two adjacent red nodes (A red node cannot have a red parent or red child).

4) Every path from root to a NULL node has same number of black nodes.

http://www.geeksforgeeks.org/red-black-tree-set-1-introduction-2/
 * @author cvielma
 */
public class RedBlack {

    RBNode root;

    public enum Color {

        RED, BLACK;
    }

    public class RBNode {

        public static final String ANSI_RESET = "\u001B[0m";
        public static final String ANSI_BLACK = "\u001B[30m";
        public static final String ANSI_RED = "\u001B[31m";
        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";
        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";
        public static final String ANSI_WHITE = "\u001B[37m";
        int value;
        Color color;
        RBNode left, right, parent;

        public RBNode() {
            color = Color.RED;
        }

        public RBNode(final int value) {
            this.value = value;
            this.color = Color.RED;
        }
        
        public RBNode(final RBNode parent, final int value) {
            this.parent = parent;
            this.value = value;
            this.color = Color.RED;
        }

        public void flipColor() {
            if (color == Color.BLACK) {
                color = Color.RED;
            } else {
                color = Color.BLACK;
            }
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
            sb.append(color == color.RED ? ANSI_RED : ANSI_BLACK).append("(").append(color).append(")").append(ANSI_RESET);
            sb.append(branch);
            if (left != null) {
                printNode(sb, left, level, "(L)");
            }

            if (right != null) {
                printNode(sb, right, level, "(R)");
            }
        }

        private void printNode(StringBuilder sb, RBNode node, int level, String branch) {
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

        public boolean equals(final Object obj) {
            return obj instanceof RBNode && obj != null && ((RBNode) obj).value == value;
        }
    }
    ///////////////////////////////////////////////////////////////////////////////

    public RBNode search(final int value) {
        return search(root, value);
    }

    private RBNode search(final RBNode node, final int value) {
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
        if (root == null) {
            root = new RBNode(value);
            root.color = Color.BLACK;
        } else {
            root = insert(root, value);
        }
    }

    private RBNode insert(final RBNode node, final int value) {
        if (node == null) {
            return new RBNode(value);
        } else {
            if (value < node.value) {
                RBNode left = insert(node.left, value);
                node.left = left;
                left.parent = node;
                return insertionBalanceTree(left);
            } else {
                RBNode right = insert(node.right, value);
                node.right = right;
                right.parent = node;
                return insertionBalanceTree(right);
            }
        }
    }

    private RBNode getUncle(final RBNode node) {
        RBNode grandP = getGrandParent(node);
        if (grandP != null) {
            if (node.parent.equals(grandP.left)) {
                return grandP.right;
            } else {
                return grandP.left;
            }
        }
        return null;
    }

    private RBNode getGrandParent(final RBNode node) {
        return node.parent == null ? null : node.parent.parent;
    }

    // http://en.wikipedia.org/wiki/Red%E2%80%93black_tree#Insertion
    public RBNode insertionBalanceTree(final RBNode node) {
        if (node.parent == null) { // Case 1
            node.color = Color.BLACK;
        } else if (node.parent.color == Color.RED) { // Case 3
            RBNode uncle = getUncle(node);
            if (uncle != null && uncle.color == Color.RED) {
                uncle.color = Color.BLACK; 
                node.parent.color = Color.BLACK;
                RBNode grandP = getGrandParent(node);
                if (grandP != null) { 
                    grandP.color = Color.RED;
                    return insertionBalanceTree(grandP); 
                }
            } else { // Case 4
                RBNode result = node;
                if (isRightChild(node) && isLeftChild(node.parent)) {
                    result = rotateLeft(node.parent);
                } else if (isLeftChild(node) && isRightChild(node.parent)) {
                    result = rotateRight(node.parent);
                }
                // Case 5
                RBNode grandP = getGrandParent(result);
                if (result.parent != null) {
                    result.parent.color = Color.BLACK;
                    if (grandP != null) {
                        grandP.color = Color.RED;
                    }
                }

                if (isLeftChild(result)) {
                    result = rotateRight(grandP);
                } else {
                    result = rotateLeft(grandP);
                }

//              return result;
            }
        }

        return node;
    }

    private boolean isRightChild(RBNode node) {
        return node != null && node.parent != null && node.equals(node.parent.right);
    }

    private boolean isLeftChild(RBNode node) {
        return node != null && node.parent != null && node.equals(node.parent.left);
    }

    private int getHeightDiff(final RBNode node) {
        int left = getHeight(node.left);
        int right = getHeight(node.right);
        return left - right;
    }

    private RBNode rotateRight(final RBNode node) {
        RBNode temp = node.left;
        temp.parent = node.parent;
        
        node.left = temp.right;
        if (node.left != null) {
            node.left.parent = node;
        }
        temp.right = node;
        node.parent = temp;
        
        return temp;
    }

    private RBNode rotateLeft(final RBNode node) {
//        RBNode saved_p = node.parent.left;
//        RBNode saved_left = node.left;
//        node.parent.left = node;
//        node.left = saved_p;
//        saved_p.right = saved_left;
//        
//        saved_p.parent = node;
        
                      
        RBNode temp = node.right;
        temp.parent = node.parent;
        
        node.right = temp.left;
        if (node.right != null) {
            node.right.parent = node;
        }
        temp.left = node;
        node.parent = temp;
        return temp;
    }

    public boolean isBalanced(final RBNode node) {
        return Math.abs(getHeightDiff(node)) <= 1;
    }

    public void delete(final int value) {
        root = insertionBalanceTree(delete(root, value));
    }

    private RBNode delete(final RBNode node, final int value) {
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

    public int getHeight(final RBNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////
    public List<RBNode> inOrder() {
        List<RBNode> inOrder = new LinkedList<>();
        traverseInOrder(inOrder, root);
        return inOrder;
    }

    public void traverseInOrder(final List<RBNode> result, final RBNode node) {
        if (node != null) {
            traverseInOrder(result, node.left);
            result.add(node);
            traverseInOrder(result, node.right);
        } else {
            //result.add(null);
        }
    }

    public List<RBNode> preOrder() {
        List<RBNode> inOrder = new LinkedList<>();
        traversePreOrder(inOrder, root);
        return inOrder;
    }

    public void traversePreOrder(final List<RBNode> result, final RBNode node) {
        if (node != null) {
            result.add(node);
            traversePreOrder(result, node.left);
            traversePreOrder(result, node.right);
        } else {
            result.add(null);
        }
    }

    public String toPreOrderString() {
        List<RBNode> preOrder = preOrder();
        StringBuilder sb = new StringBuilder();
        for (RBNode avl : preOrder) {
            sb.append(avl == null ? "-" : avl.value);
            sb.append(",");
        }

        return sb.toString();
    }

    public String toInOrderString() {
        List<RBNode> inOrder = inOrder();
        StringBuilder sb = new StringBuilder();
        for (RBNode avl : inOrder) {
            sb.append(avl == null ? "-" : avl.value);
            sb.append(",");
        }

        return sb.toString();
    }

    public String toString() {
        return root == null ? "" : root.toString();
    }

    public static void main(String[] args) {
        RedBlack tree = new RedBlack();
        int[] nodes = new int[]{10, 20, 30, 15};

        for (int node : nodes) {
            tree.insert(node);
            System.out.println(tree);
            System.out.println("---------------------------");
        }

//        System.out.println(tree);
//        System.out.println("HEIGHT: " + tree.getHeight(tree.root));

//        System.out.println("SEARCH:");
//        System.out.println(tree.search(6));

//        System.out.println("DELETE (6):");
//        tree.delete(6);
//        System.out.println(tree);
//        System.out.println("INSERT (6):");
//        tree.insert(6);
//        System.out.println(tree);
//        System.out.println("DELETE (15):");
//        tree.delete(15);
//        System.out.println(tree);
//        System.out.println("HEIGHT: " + tree.getHeight(tree.root));

//        nodes = new int[]{-1, 0, -2, 1, 2, 3};
//        for (int node : nodes) {
//            tree.insert(node);
//        }

//        for (int node : nodes) {
//            tree.delete(node);
//        }
        System.out.println(tree);


    }
}
