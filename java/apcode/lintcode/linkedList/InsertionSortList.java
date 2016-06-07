package lintcode.linkedList;

import algorithms.ListNode;

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // need dummy
    	if (head == null || head.next == null) {
    		return head;
    	}
    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode current = dummy.next; // the first one put in ordered sequence
        ListNode temp = current.next;
    	ListNode node = dummy;
    	while (current.next != null) {
    		temp = current.next;
    		node = dummy;
    		while (node.next != null) {
    			if (current.next.val <= node.next.val) { // current.next作为一个哨兵，碰见==的时候，一定退出循环
    				break;
    			}
    // 			node.next = node.next.next; // -- 问题原来是这句话，这句话是删除节点，一定注意引用的用法！！！！！
                node = node.next;
    		}
    // 		System.out.println("##" + dummy.next.val+"," +current.next.val + "," +node.next.val);
    		if (node.next == null || node.next == current.next) {
    			current = current.next;
    			continue;
    		}
    		// delete current.next
    		current.next = current.next.next;
    		// insert
    		temp.next = node.next;
    		node.next = temp;
    	}
    	return dummy.next;
    }
}
