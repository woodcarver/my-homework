package lintcode.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.TreeNode;

public class BinaryTreeZigzagLevelOrderTraversal {
    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        // 层序遍历的一个小小的变种, bfs
        ArrayList<ArrayList<Integer>> zigzag = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return zigzag;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // TreeNode current = queue.poll();
            // 原来是一次就取一层
            ArrayList<Integer> levelList = new ArrayList<Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                levelList.add(current.val);
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            if (level % 2 != 0) {
                Collections.reverse(levelList);
            }
            zigzag.add(levelList);
            level++;
        }
        return zigzag;
    }
}
