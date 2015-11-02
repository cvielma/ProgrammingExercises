
package Other;

/**
 * Proposed solution to find the largest Binary Search Tree in a Binary Tree.
 *
 * Proposed Solution:
 *  To find the largest BST in a tree there are different options to traverse the tree: we could take an top-down approach
 *  or a bottom-up. 
 *  
 *  The top-down approach require us to check at each node from the root if the tree starting at that node is a BST. This
 *  makes us traverse multiple times the tree to find the bst. Although it stops as soon as it find a BST because it will be 
 *  the largest. This solution has a time complexity of O(n^2) in worst-case (degenerated tree into list) or O(nLogn) (balanced tree)
 *  where n is the number of nodes in the tree.
 * 
 *  A better approach will be bottom-up where we check from the bottom of the tree the nodes to check if the trees created are BST.
 *  This makes the evaluation of a BST in O(1) for each node, although we still have to traverse the tree completely, so this 
 *  approach has a time complexity of O(n) (in fact we have to traverse the tree twice because to get to the bottom nodes we must
 *  traverse from the root and then again from the bottom to the top). THIS IS THE IMPLEMENTATION SHOWN BELOW. Given the 
 *  implementation is recursive, this has a space complexity of O(n). 
 * 
 *  Assumptions:
 *    - A null tree is not a BST.
 *    - A one node Tree is a BST (assuming this, any not null tree will have at least one BST).
 *    - Although there can be repeated nodes in the tree, the BST will not have repeated nodes (BST definition).
 *    - Tree nodes will have non-null integer values. 
 *    - Although problem says that there are no two same-sized largest BSTs, if there were any, it will return the leftmost one.
 *    - There are no "leaf" kind of nodes. Although there are nodes that have left and right children null.
 *
 * Some assumptions: 
 *  - A null tree is not a BST. 
 *  - A one node Tree is a BST. 
 *  - When there are several same sized BSTs it will return any of the trees. 
 *  - There are no "leaf" type of nodes. 
 *  - All nodes have left and right sons, although they might be null to 
 *    simulate final leaves. 
 *  - Nodes contain int values (there might be limitation in the range). 
 *  - Nodes cannot contain empty values.
 *  - There can be repeated nodes in the tree. Although there cannot be repeated
 *    nodes in the BST.
 *
 * @author Christian Vielma <christianvielma@gmail.com>
 */
public class LargestBstFull{

    /**
     * Binary Tree Class
     */
    public static class TreeNode {

        int value;
        TreeNode left;
        TreeNode right;

        /**
         * Not implementing constructor, getters or setters for simplicity in
         * this case Following OO value, left and right should be private and be
         * accessed only through getters and setters.
         *
         * Some methods it would have:
         *  - Constructors (default and with parameters)
         *  - Getters and setters
         *  - AddNode (it will depend on how is suppose to behave this tree, if it going to be balanced or not for example)
         *  - DeleteNode (by value (if assumption of repeated values is removed or deleting all nodes with that value) or giving a TreeNode)
         *  - Implementation of equals and hashCode.
         *  - GetNode (by value (if assumption of repeated values is removed or returning a list of nodes with that value) or giving a TreeNode)
         */
        
        /**
         * This Method creates a Tree from an array assuming each value
         * i in the array will have its children in positions
         * 2*i + 1 and 2*i +2. The array can contain null values.
         * 
         * @param nodes Array with node values.         
         * @return A complete tree.
         */
        public static TreeNode createTree(Integer[] nodes) {
            return createTree(nodes, 0);
        }
        
        /**
         * This method implements the createTree method recursively and 
         * prevents the first to be called with bad arguments. 
         * @param nodes Array with node values.
         * @param i Position of current node.
         * @return A complete tree for node i.
         * 
         * This method has O(n) time complexity (since it traverse the whole array)
         * and O(n) space complexity because it is implemented recursively. 
         */
        private static TreeNode createTree(Integer[] nodes, int i) {
            if (nodes == null || i >= nodes.length || nodes[i] == null) { //Possible wrong cases.
                return null;
            }
            TreeNode node = new TreeNode();
            node.value = nodes[i];
            node.left = createTree(nodes, 2 * i + 1);
            node.right = createTree(nodes, 2 * i + 2);

            return node;
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

    /**
     * This class is a wrapper (although it could implement 
     * decorator pattern if methods were implemented) for a
     * TreeNode to contain extra information about the current node, needed to
     * determine the largestBST while going up. This is because a Java's
     * limitation to only return one type.
     */
    public static class TreeNodeHelper {

        TreeNode node;
        int nodes; //number of nodes
        Integer maxValue;
        Integer minValue;
        boolean isBST;

        public TreeNodeHelper() {
        }

        public TreeNodeHelper(TreeNode node, int nodes, Integer maxValue, Integer minValue, boolean isBST) {
            this.node = node;
            this.nodes = nodes;
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.isBST = isBST;

        }     
    }

    /**
     * This is the main method. This method determines if a tree has BST inside
     * and return the largest (through a helper class) and how many nodes has it.
     * @param tree The root of the tree to be analyzed.
     * @return TreeNodeHelper with some information as: number of nodes in 
     * largestBST and the link to the BST node.
     *   
     */
    public static TreeNodeHelper getLargestBST(TreeNode tree) {
        if (tree == null) {
            return new TreeNodeHelper(null, 0, null, null, false);
        }
        if (tree.left == null && tree.right == null) {
            TreeNodeHelper helper = new TreeNodeHelper(tree, 1, tree.value, tree.value, true);
            return helper;
        } else {
            TreeNodeHelper leftBst = getLargestBST(tree.left);
            TreeNodeHelper rightBst = getLargestBST(tree.right);            

            if (!(rightBst.isBST && rightBst.minValue > tree.value)) {
                rightBst.isBST = false;
            }

            if (!(leftBst.isBST && leftBst.maxValue < tree.value)) {
                leftBst.isBST = false;
            }

            if (leftBst.isBST && rightBst.isBST) { //Both leaves are BST and BST is satisfied with current root
                return new TreeNodeHelper(tree, rightBst.nodes + leftBst.nodes + 1, rightBst.maxValue, leftBst.minValue, true);
            } else if (tree.left == null && rightBst.isBST) { //Only right leaf and BST is satisfied with current root
                return new TreeNodeHelper(tree, ++rightBst.nodes, rightBst.maxValue, tree.value, true);
            } else if (tree.right == null && leftBst.isBST) {//Only left leaf and BST is satisfied with current root.
                return new TreeNodeHelper(tree, ++leftBst.nodes, tree.value, leftBst.minValue, true);
            } else { //No BST is satisfied with current root
                //At this point is determined that no BST can include current node, so we return
                //the leaf that had the largest BST until now (if there was one)
                return leftBst.nodes >= rightBst.nodes ? leftBst : rightBst;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] treeA = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        TreeNodeHelper result = getLargestBST(tree);
        System.out.println("1 - Largest BST: \n" + result.node);


        treeA = new Integer[]{8, 3, 4, 2, 4, 9, 7, 1, null, null, null, 8, 9};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("2 - Largest BST: \n" + result.node);

        treeA = new Integer[]{null};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("3 - Largest BST: \n" + result.node);

        treeA = null;
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("3.1 - Largest BST: \n" + result.node);

        treeA = new Integer[]{1};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("4 - Largest BST: \n" + result.node);

        treeA = new Integer[]{9, 4, 14, 2, 6, 12, 16, 1, 3, 5, 7, 10, 15, 22, 15, -1, null, null, null, null, null, null, 8};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("5 - Largest BST: \n" + result.node);

        treeA = new Integer[]{14, 12, 16, 10, 15, 22, 15};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("6 - Largest BST: \n" + result.node);


        treeA = new Integer[]{4, 2, 6, 1, 3, 5, 7, -1, null, null, null, null, null, null, 8};
        tree = TreeNode.createTree(treeA);
        System.out.println("\n---\n" + tree);
        result = getLargestBST(tree);
        System.out.println("7 - Largest BST: \n" + result.node);



    }
}
