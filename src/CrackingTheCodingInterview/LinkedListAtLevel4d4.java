/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CrackingTheCodingInterview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 4.4) Given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth.
 */
public class LinkedListAtLevel4d4 {

    public static class TreeNode {

        int value;
        TreeNode left, right;
        
        public TreeNode(final int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + value + "}";
        }
    }

    public static Map<Integer, List<TreeNode>> getNodesAtLevel(final TreeNode root) {
        return getNodesAtLevel(root, new HashMap<Integer, List<TreeNode>>(), 0);
    }

    public static Map<Integer, List<TreeNode>> getNodesAtLevel(final TreeNode root, final Map<Integer, List<TreeNode>> track, final int level) {
        if (root == null) {
            return track;
        }

        if (track.containsKey(level)) {
            track.get(level).add(root);
        } else {
            List<TreeNode> levelList = new ArrayList<>();
            levelList.add(root);
            track.put(level, levelList);
        }

        getNodesAtLevel(root.left, track, level + 1);
        getNodesAtLevel(root.right, track, level + 1);
        return track;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(1);
        
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(2);
        root.left.right = new TreeNode(7);
        
        root.right.left = new TreeNode(6);
        root.right.left.left = new TreeNode(8);
        root.right.left.left.left = new TreeNode(9);
        
        Map<Integer, List<TreeNode>> result = getNodesAtLevel(root);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
