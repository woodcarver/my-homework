package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;
/*
 * Check two given binary trees are identical or not. Assuming any number of tweaks are allowed. 
 * A tweak is defined as a swap of the children of one node in the tree.
 * There is no two nodes with the same value in the tree.
 *  1             1
   / \           / \
  2   3   and   3   2
 /                   \
4                     4
 * are identical.
 *
 *
 *  1             1
   / \           / \
  2   3   and   3   2
 /             /
4             4
 * are not identical.
 * 
 * 注意可能数中是部分tweak的。
 * 分冶？子树是identical，整棵树就是identical？
 */
public class TweakedIdenticalBinaryTree {
	public boolean isTweakedIdentical(TreeNode a, TreeNode b) {
		if (a == null && b == null) {
			return true;
		}
		// Conquer
		if (a == null || b == null) {
			return false;
		}
		if (a.val != b.val) {
			return false;
		}
		boolean leftRet = false;
		boolean rightRet = false;
//		if (a.left.val == b.left.val && a.right.val == b.right.val) {
//			leftRet = isTweakedIdentical(a.left, b.left);
//			rightRet = isTweakedIdentical(a.right, b.right);
//		} else if (a.left.val == b.right.val && a.right.val == b.left.val) {
			// tweaked
//			leftRet = isTweakedIdentical(a.left, b.right);
//			rightRet = isTweakedIdentical(a.right, b.left);
//		} else {
//			return false;
//		}
		// try no tweak
		leftRet = isTweakedIdentical(a.left, b.left);
		rightRet = isTweakedIdentical(a.right, b.right);
		if (leftRet && rightRet) {
			return true;
		}
		// try tweak
		leftRet = isTweakedIdentical(a.left, b.right);
		rightRet = isTweakedIdentical(a.right, b.left);
		return leftRet && rightRet;
	}
}
