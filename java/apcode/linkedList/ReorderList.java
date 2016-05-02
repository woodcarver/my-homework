package lintcode.linkedList;

import algorithms.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {  
        // 基本思路：
        // 1. 找到中点，切成两个list -- left，right
        // 2. right
        // 3. 合并left，right
        // - 问题，到底多长分割成一个函数
        // - 什么时候用dummy node，值得思考
        // - findMiddleNode的slow和fast的初始值在list的长度是偶数时候会影响，
        // 到底取n/2,还是n/2 + 1。如果取n/2, 记得fast=head.next,slow=head
        //  这样等价于：fast=(1 + 2x) or (2x), slow= 2x
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode middle = findMiddleNode(head);
        ListNode left = head;
        ListNode right = middle.next;
        middle.next = null; //切断list
        
        //reverse right list
        ListNode rightDummy = new ListNode(0);
        while (right != null) {
            ListNode temp = right.next;
            right.next = rightDummy.next;
            rightDummy.next = right;
            right = temp;
        }
        right = rightDummy.next;
        // merge two list
        while (left != null && right != null) {
            ListNode temp = right.next;
            right.next = left.next;
            left.next = right;
            left = left.next.next;
            right = temp;
        }
        // return left;
    }
    private ListNode findMiddleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
