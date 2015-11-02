/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Codility;

/**
 *
 *
 * There is an N × N square mesh-shaped grid of wires, as shown in a figure
 * below. Nodes of the grid are at points (X, Y), where X and Y are integers
 * from 0 to N−1. An electric current flows through the grid, between the nodes
 * at (0, 0) and (N−1, N−1).
 *
 * Initially, all the wires conduct the current, but the wires burn out at a
 * rate of one per second. The burnouts are described by three zero-indexed
 * arrays of integers, A, B and C, each of size M. For each moment T (0 ≤ T <
 * M), in the T-th second the wire between nodes (A[T], B[T]) and:
 *
 * (A[T], B[T] + 1), if C[T] = 0 or (A[T] + 1, B[T]), if C[T] = 1
 *
 * burns out. You can assume that the arrays describe existing wires, and that
 * no wire burns out more than once. Your task is to determine when the current
 * stops flowing between the nodes at (0,0) and (N−1,N−1).
 *
 * Write a function:
 *
 * class Solution { public int solution(int N, int[] A, int[] B, int[] C); } *
 * that, given integer N and arrays A, B and C, returns the number of seconds
 * after which the current stops flowing between the nodes at (0, 0) and (N−1,
 * N−1). If the current keeps flowing even after all M wires burn out, the
 * function should return −1.
 *
 * For example, given N = 4, M = 9 and the following arrays:
 *
 * A[0] = 0 B [0] = 0 C[0] = 0 A[1] = 1 B [1] = 1 C[1] = 1 A[2] = 1 B [2] = 1
 * C[2] = 0 A[3] = 2 B [3] = 1 C[3] = 0 A[4] = 3 B [4] = 2 C[4] = 0 A[5] = 2 B
 * [5] = 2 C[5] = 1 A[6] = 1 B [6] = 3 C[6] = 1 A[7] = 0 B [7] = 1 C[7] = 0 A[8]
 * = 0 B [8] = 0 C[8] = 1 * your function should return 8, because just after
 * the eighth wire burns out, there is no connection between the nodes at (0, 0)
 * and (N−1, N−1). This situation is shown in the following figure:
 *
 * Given N = 4, M = 1 and the following arrays:
 *
 * A[0] = 0 B [0] = 0 C[0] = 0 * your function should return −1, because burning
 * out a single wire cannot break the connection between the nodes at (0, 0) and
 * (N−1, N−1).
 *
 * Assume that:
 *
 * N is an integer within the range [1..400]; M is an integer within the range
 * [0..2*N*(N−1)]; each element of array A is an integer within the range
 * [0..N−1]; each element of array B is an integer within the range [0..N−1];
 * each element of array C is an integer within the range [0..1].
 *
 * Complexity:
 *
 * expected worst-case time complexity is O(N2*log(N)); expected worst-case
 * space complexity is O(N2), beyond input storage (not counting the storage
 * required for input arguments).
 *
 * Elements of input arrays can be modified.
 *
 *
 *
 *
 *
 *
 *
 *
 *

 *
 * @author Christian Vielma <cvielma@librethinking.com>
 */
public class Solution {

    class TreeNode {

        int a;
        int b;
        boolean reachEnd;
        TreeNode leftLeaf;
        TreeNode rightLeaf;
        boolean isFinal;
        TreeNode parent;

        public TreeNode(int a, int b) {
            this.a = a;
            this.b = b;
            reachEnd = true;
            this.leftLeaf = null;
            this.parent = null;
        }

        public TreeNode(int a, int b, boolean reachEnd) {
            this.a = a;
            this.b = b;
            this.reachEnd = reachEnd;
            this.leftLeaf = null;
        }

        public TreeNode getLeftLeaf() {
            return leftLeaf;
        }

        public void setLeftLeaf(TreeNode leftLeaf) {
            this.leftLeaf = leftLeaf;
        }

        public TreeNode getRightLeaf() {
            return rightLeaf;
        }

        public void setRightLeaf(TreeNode rightLeaf) {
            this.rightLeaf = rightLeaf;
        }

        //~O(log(N2))
        public TreeNode getNode(int a, int b) {
            if (this.a == a && this.b == b) {
                return this;
            } else if (leftLeaf == null && rightLeaf == null) {
                return null;
            } else {
                //TODO: could be improved by ranging a and b (if a > this.a then go left)
                TreeNode left = leftLeaf != null ? leftLeaf.getNode(a, b) : null;
                TreeNode right = rightLeaf != null ? rightLeaf.getNode(a, b) : null;
                if (left != null) {
                    return left;
                } else if (right != null) {
                    return right;
                } else {
                    return null;
                }
            }
        }

        public void addNode(int a, int b) {
            TreeNode newNode = new TreeNode(a, b);
            addNode(a, b, newNode);
        }

        //~O(log(N2))
        public void addNode(int a, int b, TreeNode newNode) {
            if ((this.a == (a-1) && this.b == b) || (this.a == a && this.b == (b-1))) {
                if(a> this.a){
                    this.leftLeaf = newNode;
                    this.leftLeaf.parent = this;
                }
                if(b > this.b){
                    this.rightLeaf = newNode;
                    this.rightLeaf.parent = this;
                }
            } else {
                TreeNode left = this.getNode(a - 1, b);
                TreeNode right = this.getNode(a, b - 1);
                if (left != null) {
                    left.addNode(a, b, newNode);
                }
                if (right != null) {
                    right.addNode(a, b, newNode);
                }
                if (left == null && right == null) {
                    System.out.println("Node: " + a + ", " + b + " - already added");
                }
            }
        }

        //True if end is still reachable from this node after removing        
        //~O(log(n2))
        public boolean removePath(int a1, int b1, int a2, int b2) {
            TreeNode node = this.getNode(a1, b1);
            if (node != null) {
                if (node.leftLeaf!= null && node.leftLeaf.a == a2 && node.leftLeaf.b == b2) {
                    node.setLeftLeaf(null);

                } else if (node.rightLeaf!=null && node.rightLeaf.a == a2 && node.rightLeaf.b == b2) {
                    node.setRightLeaf(null);
                }

                if (node.leftLeaf == null && node.rightLeaf == null && !node.isFinal) {
                    node.reachEnd = false;
                }
            }
            return checkDeepReach(node);
        }

        //~O(log(N2))
        private boolean checkDeepReach(TreeNode leaf) {
            if (leaf == null) {
                return true;
            }
            
            if ((leaf.leftLeaf != null && leaf.leftLeaf.reachEnd) || (leaf.rightLeaf != null && leaf.rightLeaf.reachEnd)) {
                leaf.reachEnd = true;
                return true;
            } else {
                leaf.reachEnd = false;
                if(leaf.parent==null){
                    return false;
                } 
                else {return checkDeepReach(leaf.parent);}
            }
        }
        
        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append("("+this.a+", "+this.b+")");
            sb.append(this.leftLeaf!=null? ","+this.leftLeaf : ", -");
            sb.append(this.rightLeaf!=null? ", "+this.rightLeaf : ", -");
            return sb.toString();
        }
    }

    //N2*log(n2)
    private TreeNode buildTree(int N) {
        if (N > 0) {
            TreeNode tree = new TreeNode(0, 0);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    }
                    tree.addNode(i, j);
                }
            }
            tree.getNode(N-1, N-1).isFinal = true;
            return tree;
        }
        return null;
    }

    public int solution(int N, int[] A, int[] B, int[] C) {
        TreeNode tree = buildTree(N);
        //System.out.println(tree);
        boolean stillFlow = true;
        for (int i = 0; i < A.length; i++) {
            stillFlow = tree.removePath(A[i], B[i], A[i] + C[i], B[i] + Math.abs(1 - C[i]));
            System.out.println("Removing: "+A[i]+", "+B[i]+" to "+(A[i] + C[i])+", "+(B[i] + Math.abs(1 - C[i]))+".");
            if (!stillFlow) {
                return i;
            }
        }
        return -1;
    }
    
    public static void main(String[] args) {
        int N = 4;
        int[] A = {0, 1, 1, 2, 3, 2, 1, 0, 0};
        int[] B = {0, 1, 1, 1, 2, 2, 3, 1, 0};
        int[] C = {0, 1, 0, 0, 0, 1, 1, 0, 1};
        Solution s = new Solution();
        System.out.println(s.solution(N, A, B, C));
    }
}
