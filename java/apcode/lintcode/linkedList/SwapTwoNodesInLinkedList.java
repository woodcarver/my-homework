package lintcode.linkedList;

import algorithms.ListNode;

public class SwapTwoNodesInLinkedList {
    /**
     * @param head a ListNode
     * @oaram v1 an integer
     * @param v2 an integer
     * @return a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // 1. no duplicate values
        // 2. using dummy
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode node1Prev = null;
        ListNode node2Prev = null;
        //find node1,node2
        ListNode node = dummy;
        while (node.next != null) {
            if (node.next.val == v1) {
                node1Prev = node;
            } else if (node.next.val == v2){
                node2Prev = node;
            }
            node = node.next;
        }
        if (node1Prev == null || node2Prev == null) {
            return head;
        }
        // swap, 这种swap方法不靠谱，对于两个相邻的节点会出错，会产生cycle
        if (node2Prev.next == node1Prev) {
            // 相邻,且node2prev在前
            ListNode tmp = node2Prev;
            node2Prev = node1Prev;
            node1Prev = tmp;
        }
        ListNode node1 = node1Prev.next;
        ListNode node2 = node2Prev.next;
        if (node1Prev.next == node2Prev) {
            node1Prev.next = node1Prev.next.next;
            node1.next = node2.next;
            node2.next = node1;
        } else {
            node1Prev.next = node1Prev.next.next;
            node1.next = node2.next;
            node2.next = node1;
            
            node2Prev.next = node2Prev.next.next;
            node2.next = node1Prev.next;
            node1Prev.next = node2;
        }
        
        return dummy.next;
    }
}
