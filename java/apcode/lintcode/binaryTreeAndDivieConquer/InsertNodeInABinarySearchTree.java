package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;

public class InsertNodeInABinarySearchTree {
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // keyword: BST
        // 递归 vs 非递归
        // 重复了呢？
        if (node == null) {
            return root;
        }
        if (root == null) {
            return node;
        }
        if (node.val < root.val) {
            root.left = insertNode(root.left, node);
        } else if (node.val >= root.val) {
            root.right = insertNode(root.right, node);
        }
        return root;
    }
}
