package lintcode;

import algorithms.TreeNode;
import java.util.*;
/**
 * 这道题怎么是加强版的successor呢？
 * 使用超时，总共接近用了2h
 * @author xiedandan
 * 错误case：
 * {1,#,2,#,3}
 */
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */
public class BinarySearchTreeIterator {
    //@param root: The root of binary tree.
    TreeNode curNode;
    TreeNode dummeyNode = new TreeNode(-1);
    ArrayList<TreeNode> savePoint;
    public BinarySearchTreeIterator(TreeNode root) {
        savePoint = new ArrayList<TreeNode>();
        savePoint.add(null);
        //如果初始化？？？
        curNode = getMinChild(root);
        if (curNode != null) {
            savePoint.add(curNode);
            curNode.left = dummeyNode;//
            curNode = curNode.left; // 少了一步骤，忘记了吧
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (curNode == null) {
            return false;
        }
        // System.out.println(curNode.val + "curNode:" + curNode.val);
        TreeNode node = curNode.right;
        while ((node != null) && (node.left != null)) {
            node = node.left;
        }
        if (node != null) {
            return true;
        }
        if (savePoint.size() > 0 && savePoint.get(savePoint.size() - 1) != null) {
            return true;
        }
        return false;
    }
    
    //@return: return next node
    public TreeNode next() {
        // 就是那道找successor的题
        // 最小右子树孩子，如果没有，那么
        // 最小右parent
        if (curNode == null) {
            return null;
        }
        TreeNode node = getMinChild(curNode.right);
        // System.out.println("next1:" + curNode.val);
        if (node != null) {
            curNode = node;
        } else if (savePoint.size() > 0){
            curNode = savePoint.remove(savePoint.size() - 1);
        }
        // System.out.println("next2:" + curNode.val);
        return curNode;
    }
    private TreeNode getMinChild(TreeNode node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            savePoint.add(node);
            node = node.left;
        }
        return node;
    }
}
