package Interviews;

/** 
 * Omitting actual question for confidentiality reasons. This is my proposed solution.
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
 *  @author Christian Vielma <christianvielma@gmail.com>
 */
 
 public class AmazonInterview {
     
     /**
      * This will be our tree class. Although it will be probably be more clear the code if it were in another file.
      */
     public static class TreeNode {
         //this attributes ideally would be encapsulated.
         int value;
         TreeNode left;
         TreeNode right;
         /* Leaving out constructors, getters and setters and some operations like add, delete or search/get because of the time. */         
     }     
     
     /** We will need some extra information when going bottom-up, this wrapper class can help us.
      *  It could be implemented as TreeNode decorator (if we were implementing methods)
      */
     private static class TreeNodeHelper {
         TreeNode node; //current root
         int nodes; //number of nodes in current tree starting in node.
         Integer maxValue; // Integer because we want to know when it hasn't been set.
         Integer minValue;
         boolean isBST;
         
         /** The same comments from TreeNode class apply here: fields should be encapsulated, constructors, and other methods implemented*/         
         
         public TreeNodeHelper(TreeNode node, int nodes, Integer maxValue, Integer minValue, boolean isBST) {
             this.node = node;
             this.nodes = nodes;
             this.maxValue = maxValue;
             this.minValue = minValue;
             this.isBST = isBST;             
         }
     }
     
     /**
      * This is the main method. It returns the largest BST tree. 
      */
     public static TreeNode getLargestBST(TreeNode tree) {
         TreeNodeHelper helper = isBST(tree);
         return helper.node;
     }
     
     /**
      * This is a helper method and the one that calculates the BST
      * @param tree Node of the current root.
      * @returns A TreeNodeHelper with the information about the current inspected node as: if it is bst, number
      *  of nodes in the largest bst from this node, and other values.
      */
      private static TreeNodeHelper isBST(TreeNode tree){//TODO: there is repeated code in this method.
          //Base case
          if (tree == null) {
              return new TreeNodeHelper(null, 0, null, null, false);
          }
          
          if (tree.left == null && tree.right == null) {//1- Check when both children are null
              return new TreeNodeHelper(tree, 1, tree.value, tree.value, true); // => this is bst
          } else if (tree.left == null && tree.right != null) { //2- Check when only left child is null
                TreeNodeHelper rightBst = isBST(tree.right);
                if (rightBst.isBST) { // right is bst ? 
                    if (rightBst.minValue > tree.value) { //yes => is the lowest element > than current value?
                        return new TreeNodeHelper(tree, ++rightBst.nodes, rightBst.maxValue, tree.value, true); //yes => this is bst
                    } else { //no => this is not bst, return right child
                        rightBst.isBST = false; //Right had a BST inside, but current node is not part of it, so we want to flag it but keep the current bst                        
                        return rightBst;
                    }
                } else { //no => this is not bst either
                    return rightBst;
                }
            } else if (tree.right == null && tree.left !=null) { //3- Check when only right child is null
                TreeNodeHelper leftBst = isBST(tree.left);
                if (leftBst.isBST) { // left is bst ?
                    if (leftBst.maxValue < tree.value) { //yes => is largest element < than current value?
                        return new TreeNodeHelper(tree, ++leftBst.nodes, tree.value, leftBst.minValue, true); //yes => this is bst
                    } else { //no => this is not bst, return left child.
                        leftBst.isBST = false; //The same logic as above.
                        return leftBst;
                    }
                } else { //no => this is not bst either
                    return leftBst;
                }
            }     
            else { //4- Check when neither of the children is null
                TreeNodeHelper rightBst = isBST(tree.right);
                TreeNodeHelper leftBst = isBST(tree.left);
                if (rightBst.isBST && leftBst.isBST) { // are both bst ? 
                    if (leftBst.maxValue < tree.value && rightBst.minValue > tree.value) { //yes => check both conditions from above
                        return new TreeNodeHelper(tree, leftBst.nodes+rightBst.nodes+1, rightBst.maxValue, leftBst.minValue, true);
                    } else { // no => this is not bst, return largest
                        leftBst.isBST = false;
                        rightBst.isBST = false;
                        return leftBst.nodes >= rightBst.nodes ? leftBst : rightBst; //Assumption: if there are two bst of equal length will return the leftmost one
                    }
                } else { //no => at least one bst? // yes => this is not bst, return the one that is bst.
                    if(leftBst.isBST) {
                        leftBst.isBST = false;
                        return leftBst;
                    } else {
                        rightBst.isBST = false;
                        return rightBst;                        
                    }                    
                }
                    
                }
            }          
     
         
         
    /** Method to create a Tree from an array (testing purposes) */
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
        Integer[] treeA = new Integer[]{8, 3, 4, 2, 4, 9, 7, 1, null, null, null, 8, 9}; //This one is the one from the example
        TreeNode tree = createTree(treeA, 0);
        TreeNodeHelper result = isBST(tree);  //the real method to test is largestBST, but to know if the answer is right we need the helper
        System.out.println("1-Result - node = '"+result.node.value+"', nodes = "+result.nodes); 
        assert(result.nodes == 4);
        
        treeA = new Integer[]{null};
        tree = createTree(treeA, 0);
        result = isBST(tree);
//        System.out.println("2-Result - node = '"+result.node.value+"', nodes = "+result.nodes); 
        assert(result.nodes == 0);
        
        treeA = new Integer[]{1};
        tree = createTree(treeA, 0);
        result = isBST(tree);
        System.out.println("3-Result - node = '"+result.node.value+"', nodes = "+result.nodes); 
        assert(result.nodes == 1);
    }
     
     
     
     
     
     
     }