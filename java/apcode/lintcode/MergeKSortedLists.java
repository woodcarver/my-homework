package lintcode;

import algorithms.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class MergeKSortedLists {
	/**
	 * Definition for ListNode.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int val) {
	 *         this.val = val;
	 *         this.next = null;
	 *     }
	 * }
	 */ 
	class ListNodeComparator implements Comparator<ListNode> {
	    @Override
	    public int compare(ListNode x, ListNode y) {
	        if (x == null) {
	            return 1;
	        }
	        if (y == null) {
	            return -1;
	        }
	        return x.val - y.val;
	    }
	}
    public ListNode mergeKLists(List<ListNode> lists) {
        // time complexity: n is the total elements, and k is the lists size
        //      priorityQueue offer and poll O(nlogk)
        if (lists == null || lists.size() == 0) {
            return null;
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = null;
        ListNode pointer = dummy;
        // can I change the node's next in the lists? Yes, I can
        Comparator<ListNode> listNodeComparator = new ListNodeComparator();
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<ListNode>(
            lists.size(), listNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            // if (lists.get(i).size() == 0) { // bug, it is a ListNode, not a list anymore
            if (lists.get(i) != null) {
                priorityQueue.offer(lists.get(i));
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode mini = priorityQueue.poll();
            if (mini.next != null) {
                priorityQueue.offer(mini.next);
            }
            pointer.next = mini;
            pointer = pointer.next;
        }
        return dummy.next;
    }
    
    //version 2: two two merge -- parallel
    public ListNode mergeKLists2(List<ListNode> lists) {
        // version 2: 
        if (lists == null || lists.size() == 0) {
            return null;
        }
        if (lists.size() == 1) {
            return lists.get(0);
        }
        
        // ListNode head = null;
        for (int step = 1; step < lists.size(); step *= 2) {
            for (int i = 0; i + step < lists.size(); i = i + 2 * step) {
                // System.out.println(i + "," + step);
                // head = mergeTwoList(lists.get(i), lists.get(i + step));// 这种想法是错误的
                lists.set(i, mergeTwoList(lists.get(i), lists.get(i + step)));
                // printList(lists.get(i));
            }
        }
        return lists.get(0);
        // no tail!!!!
        // consider the tail of lists
        // if ((lists.size() & 1) == 1) {
        //     System.out.println("add tail");
        //     head = mergeTwoList(head, lists.get(lists.size() - 1));
        // }
        // return head;
    }
    private ListNode mergeTwoList(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        } else if (node2 ==  null) {
            return node1;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                tail.next = node1;
                node1 = node1.next;
            } else {
                tail.next = node2;
                node2 = node2.next;
            }
            tail = tail.next;
        }
        while (node1 != null) {
            tail.next = node1;
            node1 = node1.next;
            tail = tail.next;
        }
        while (node2 != null) {
            tail.next = node2;
            node2 = node2.next;
            tail = tail.next;
        }
        return dummy.next;
    }
    // version 3: divide & conquer
    private void printList(ListNode list) {
        while (list != null) {
            System.out.print(list.val + "->");
            list = list.next;
        }
        System.out.println("end");
    }
}
/******************
 * jiuzhang answer
 ******************/
//version 2: Heap
class JiuzhangMergeKSortedLists {
    private Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode left, ListNode right) {
            if (left == null) {
                return 1;
            } else if (right == null) {
                return -1;
            }
            return left.val - right.val;
        }
    };

    public ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }

        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode head = heap.poll();
            tail.next = head;
            tail = head;
            if (head.next != null) {
                heap.add(head.next);
            }
        }
        return dummy.next;
    }
}
