package lintcode.binaryTreeAndDivieConquer;

import java.util.LinkedList;
import java.util.Queue;

import algorithms.TreeNode;
/**
 * 速度，速度太慢！！！！！
 * 如何快速编码？？头痛！！
 * @author xiedandan
 *
 */
public class BinaryTreeSerialization {
    /**
     * 要序列化一个二叉树，其实就找一个种遍历方法，但是在所有的遍历方法中
     * 都是在只有当树是满树的时候才能唯一，
     * 否则都是可能多种树的遍历结果是一样的。所以一定要借助
     * dummy node把变成满树才行。
     * 这里继续用bfs的方法
     * 
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    private final String DUMMY_NODE = "#";
    private final String SPERATE_NOTE = ",";
    public String serialize(TreeNode root) {
        StringBuilder data = new StringBuilder();
        if (root == null) {
            return data.toString();
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        data.append(root.val);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            data.append(SPERATE_NOTE);
            if (current.left != null) {
                data.append(current.left.val);
                queue.offer(current.left);
            } else {
                data.append(DUMMY_NODE);
            }
            data.append(SPERATE_NOTE);
            if (current.right != null) {
                data.append(current.right.val);
                queue.offer(current.right);
            } else {
                data.append(DUMMY_NODE);
            }
        }
        // System.out.println("serialize:" + data.toString());
        return data.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] list = data.split(SPERATE_NOTE);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        int start = 0;
        while (list[start].equals(DUMMY_NODE)) {
            start++;
        }
        int len = list.length;
        if (start >= len) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(list[start]));
        queue.offer(root);
        int i;
        for (i = start + 1; i + 1 < len; i += 2) {
            TreeNode parent = queue.poll();

            if (!list[i].equals(DUMMY_NODE)) {
                int val = Integer.parseInt(list[i]);
                TreeNode left = new TreeNode(val);
                parent.left = left;
                queue.offer(left);
            }
            if (!list[i + 1].equals(DUMMY_NODE)) {
                int val = Integer.parseInt(list[i + 1]);
                TreeNode right = new TreeNode(val);
                parent.right = right;
                queue.offer(right);
            }
        }
        // the last node
        if (i < len) {
            TreeNode parent = queue.poll();
            if (!list[i].equals(DUMMY_NODE)) {
                int val = Integer.parseInt(list[i]);
                TreeNode left = new TreeNode(val);
                parent.left = left;
                queue.offer(left);
            }
        }
        return root;
    }
}
