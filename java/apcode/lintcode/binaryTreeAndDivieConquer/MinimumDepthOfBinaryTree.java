package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;

/*
 * 重点： depth这种即有返回值，又有参数的递归到底是什么？
 * 如果对于遍历是参数，返回值是分冶的话
 */
public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        // nearest leaf node
        // 记录一个depth，当远远大于global就没有必要再跑了是吧
        // 遍历，不是分冶 --> 还是分冶
        // depth必须用遍历 + 分冶(不算分冶其实)
        
        if (root == null) {
            return 0;
        }
        return minDepthHelper(root, 1);
    }
    private int minDepthHelper(TreeNode root, int depth) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return depth;
        }
        int leftDepth = Integer.MAX_VALUE;
        int rightDepth = Integer.MAX_VALUE;
        if (root.left != null ) {
            leftDepth = minDepthHelper(root.left, depth + 1);
        }
        if (root.right != null) {
            rightDepth = minDepthHelper(root.right, depth + 1);
        }
        return Math.min(leftDepth, rightDepth);
    }
}
