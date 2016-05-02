package lintcode.linkedList;

import algorithms.ListNode;
import algorithms.TreeNode;

public class ConvertSortedListToBalancedBST {
    public TreeNode sortedListToBST(ListNode head) {  
        // 思路：类似于MergeSort的方法，or Divide Conquer。学会链表切割技术。
        // 1. findMiddleNode -- middle这里真心难处理
        // 2. getLeftTree
        // 3. getRightTree
        // 4. mergeLeftRight
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        ListNode middle = findMiddleNode(head);
        TreeNode subTree = new TreeNode(middle.next.val);
        
        TreeNode rightSubTree = sortedListToBST(middle.next.next);
        // middle = null; //cut the list, 这种cut方法是大大的错误,只是改了引用，而没有修改指向的内容。
        middle.next = null;
        TreeNode leftSubTree = sortedListToBST(head);
        
        subTree.left = leftSubTree;
        subTree.right = rightSubTree;
        return subTree;
    }
    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
