package lintcode.linkedList;

import algorithms.ListNode;

public class MiddleOfLinkedList {
    public ListNode middleNode(ListNode head) {
        // fast,slow pointers to find middle
        // 数学原理去初始化fast，slow
        //
        // 长度奇数 ——> N = 2x+1, middle = x+1, pos=x --> 1->2->3->null
        // fast = slow = 0; fast = 2x, slow = x --> 2
        // fast = 1, slow = 0; fast = 2x+1(跳到空上), slow = x --> 2
        //
        // 长度偶数 ——> N = 2x, middle = x, pos= x-1 --> 1->2->3->4->null
        // fast = slow = 0; fast = 2x(跳到空上), slow = x --> 3
        // fast = 1, slow =0; fast = 2x+1, slow = x --> 2
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        //ListNode fast = head.next; // which one is right, 关系到left优先还是right优先
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
