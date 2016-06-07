package lintcode.linkedList;

import algorithms.ListNode;

public class RemoveDuplicatesFromSortedList {
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     * 留一个副本
     */
    public static ListNode deleteDuplicates(ListNode head) {
        // 对比II，这道好easy，head是确定型的
        // 关键词：Sorted
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cursor = head;
        while (cursor.next != null) {
            if (cursor.val == cursor.next.val) {
                cursor.next = cursor.next.next;
            } else {
                cursor = cursor.next;
            }
        }
        return head;
    }
    /**
     * @param ListNode head is the head of the linked list
     * @return: ListNode head of linked list
     * 不留副本,head变成不确定型的， 要用dummy node
     */
    public static ListNode deleteDuplicatesII(ListNode head) {
    	
    }
}
