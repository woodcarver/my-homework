package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;

public class SymmetricBinaryTree {
	public boolean isSymmetric(TreeNode root) {
        // 不是递归结构？？
        // if (root == null || (root.left == null && root.right == null)) {
        if (root == null) {
            return true;
        }
        // if (root.left == null || root.right == null) {
        //     return false;
        // }
        // if (root.left.val != root.right.val) {
        //     return false;
        // }
        return isSymmetricHelper(root.left, root.right);
    }
    private boolean isSymmetricHelper(TreeNode leftTree, TreeNode rightTree) {
        if (leftTree == null && rightTree == null) {
            return true;
        }
        if (leftTree == null || rightTree == null) {
            return false;
        }
        if (leftTree.val != rightTree.val) {
            return false;
        }
        return isSymmetricHelper(leftTree.left, rightTree.right) &&
            isSymmetricHelper(leftTree.right, rightTree.left);
    }
}
