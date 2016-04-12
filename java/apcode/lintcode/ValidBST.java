package lintcode;

import algorithms.TreeNode;

/**
 * 不能重复?-- {1,1}
 *    1
 *    /
 *   1
 * @author xiedandan
 *
 */
public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode maxLeftChild = getMaxChild(root.left);
        TreeNode minRightChild = getMinChild(root.right);
//        if (maxLeftChild != null && maxLeftChild.val > root.val) {
        if (maxLeftChild != null && maxLeftChild.val >= root.val) {
            return false;
        }
//        if (minRightChild != null && minRightChild.val < root.val) {
        if (minRightChild != null && minRightChild.val <= root.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }
    private TreeNode getMaxChild(TreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.right != null) {
            root = root.right;
        }
        return root;
    }
    private TreeNode getMinChild(TreeNode root) {
        if (root == null) {
            return null;
        }
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
}
