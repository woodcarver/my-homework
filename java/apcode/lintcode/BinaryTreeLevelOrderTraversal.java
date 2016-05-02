package lintcode;

import algorithms.TreeNode;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
/**
 * 
 * @author xiedandan
 * 错误case：
 * {} --> [], 居然返回是空，我自定义成了null
 */
public class BinaryTreeLevelOrderTraversal {
    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode root) {
    	ArrayList<ArrayList<Integer>> levelOrder = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        TreeNode dummy = new TreeNode(0);
        queue.offer(root);
        queue.offer(dummy);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == dummy) { //add dummy node
//                queue.offer(dummy); //居然写了死循环，我也是醉了。。。。。
                if (!queue.isEmpty()) {
                    queue.offer(dummy);
                }
                levelOrder.add(list);
                System.out.println(list);
                list = new ArrayList<Integer>();
            } else {
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        return levelOrder;
    }
    public ArrayList<ArrayList<Integer>> levelOrder2(TreeNode root) {
        ArrayList<ArrayList<Integer>> levelOrder = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return levelOrder;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            levelOrder.add(list);
        }
        return levelOrder;
    }
}
