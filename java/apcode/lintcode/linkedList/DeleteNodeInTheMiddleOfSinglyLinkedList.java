package lintcode.linkedList;

import algorithms.ListNode;

public class DeleteNodeInTheMiddleOfSinglyLinkedList {
    /**
     * @param node: the node in the list should be deleted
     * @return: nothing
     */
    public void deleteNode(ListNode node) {
        // 把后面的node copy过来，然后删除后面的一个node
    	if (node == null || node.next == null) {
    		node = null;
    		return;
    	}
    	// copy next node
    	node.val = node.next.val;
    	// delete next node
    	node.next = node.next.next;
    }
}
