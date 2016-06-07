package lintcode.binaryTreeAndDivieConquer;

import java.util.LinkedList;
import java.util.Queue;

import algorithms.TreeNode;

public class CompleteBinaryTree {
    public boolean isComplete(TreeNode root) {
        // 只有一个节点可能没有右孩子，其余的节点要么是leaf node，要么是左右都有
        // 层序遍历？
        // 不需要记住第几层
        // 判断规则是只要检测到有不满节点开始，后面的节点都必须是leaf node。
        if (root == null) {
            return true;
        }
        Queue<TreeNode>  queue = new LinkedList<TreeNode>();
        queue.offer(root);
        boolean leafSwitch = false;
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            // System.out.println(current.val + ",");
            if (leafSwitch && (current.left != null || current.right != null)) {
                return false;
            }
            if (current.left != null && current.right != null) {
                queue.offer(current.left);
                queue.offer(current.right);
            } else if (current.left != null && current.right == null) {
                queue.offer(current.left);
                leafSwitch = true;
            } else if (current.left == null && current.right == null) {
                leafSwitch = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
