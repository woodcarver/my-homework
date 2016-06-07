package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePathSum {
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // 递归和非递归
    	// A valid path is from root node to any of the leaf nodes, 注意其中的起点root，终点是leaf
    	// 注意题目是要求所有的，dfs
    	// 再强调下，一定要画图，一定要画图，一定要画图，不管题目多么的简单
    	// 我明显是个视觉系动物，需要通过可视化来学习
    	
    	List<List<Integer>> paths = new ArrayList<List<Integer>>();
    	if (root == null) {
    		return paths;
    	}
    	binaryTreePathSumHelper(root, paths, new ArrayList<Integer>(), target);
    	return paths;
    }
    // path的add和remove一定要对等
    private void binaryTreePathSumHelper(TreeNode root, List<List<Integer>> paths,
    		List<Integer> path, int rest) {
    	if (root == null) { // 这个不能省略，万一root一开始传入就是null呢？
    		return;
    	}
    	path.add(root.val);
    	// 如果不加这个判断叶子节点的condition， 一条path会被重复的计入paths中
    	// 因为一个leaf node有两个null节点
//    	if (root.left == null && root.right == null && rest == 0) {
    	if (root.left == null && root.right == null) {
    		if (rest == root.val) {
    			paths.add(new ArrayList<Integer>(path));
    		}
    		path.remove(path.size() - 1); // tricky
    		return;
    	}
    	
    	binaryTreePathSumHelper(root.left, paths, path, rest - root.val);
    	binaryTreePathSumHelper(root.right, paths, path, rest - root.val);
    	path.remove(path.size() - 1);
    }
}
