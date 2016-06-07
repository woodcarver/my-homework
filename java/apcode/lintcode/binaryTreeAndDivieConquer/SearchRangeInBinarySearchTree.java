package lintcode.binaryTreeAndDivieConquer;

import java.util.ArrayList;

import algorithms.TreeNode;

public class SearchRangeInBinarySearchTree {
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2) {
        // 数据库索引中的范围查找理解重点
        // 好像就是求最大公共祖先的问题有关联
        // 话说BST怎么遍历出的顺序是排序好的？—— 中序遍历
        //   2
        //  / \   ==> inorder: 123, preorder: 213, postorder: 132
        // 1   3
        ArrayList<Integer> range = new ArrayList<Integer>();
        if (root == null) {
            return range;
        }
        searchRangeHelper(root, range, k1, k2);
        return range;
    }
    private void searchRangeHelper(TreeNode root, ArrayList<Integer> range,
        int k1, int k2) {
        if (root == null) {
            return;
        }
        
        if (root.val > k1) {
            searchRangeHelper(root.left, range, k1, k2);
        }
        if (root.val >= k1 && root.val <= k2) {
            range.add(root.val);
        }
        if (root.val < k2) {
            searchRangeHelper(root.right, range, k1, k2);
        }
    }
}
