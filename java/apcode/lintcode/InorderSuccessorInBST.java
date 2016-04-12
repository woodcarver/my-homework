package lintcode;

import algorithms.TreeNode;
/**
 * 第一种解法是错误的，测试用例{3,1,4,#,2},node with value 2。 在寻找父节点的时候，不一定是紧挨这的。
 *     10
 *     /
 *    7
 *   /
 *  6
 *  \
 *   9
 *例如这颗树，9的successor是10，但是10不是9的直接parent。
 * @author xiedandan
 *
 */
public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor1(TreeNode root, TreeNode p) {
        return inorderSuccessorHelper(p, null, root);
    }
    private TreeNode inorderSuccessorHelper(TreeNode p, TreeNode parent, TreeNode curNode) {
        if (curNode == null) {
            return null;
        }
        if (curNode.val == p.val) {
            if (curNode.right != null) {
                TreeNode smallestRightChild = getMinChild(curNode.right);
                System.out.println("smallestRightChild:" + smallestRightChild.val);
                return smallestRightChild;
//            } else if (parent != null && parent.left == curNode) {
            } else if (parent != null) {
                System.out.println("parent:" + parent.val);
                return parent;
            } else {
                return null;
            }
        } else if (curNode.val > p.val) {
            return inorderSuccessorHelper(p, curNode, curNode.left);
        } else {
//            return inorderSuccessorHelper(p, curNode, curNode.right);
            return inorderSuccessorHelper(p, parent, curNode.right);
        }
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
