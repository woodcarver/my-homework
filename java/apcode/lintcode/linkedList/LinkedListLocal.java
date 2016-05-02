package lintcode.linkedList;

import algorithms.ListNode;

public class LinkedListLocal {
	   ListNode removeNthFromEnd(ListNode head, int n) {
	        // 指针的问题：1. 头指针的问题，总是要特殊处理。
	        // 2. 对于删除一定要定位到其parent，至于要不是要用这个显示的指针，我倾向于用，不容易出错。
	        // 3. 当然还有永远的边界情况考虑，比如此题的n=0的时候
	        if (head == null || n <= 0) {
	            return head;
	        }
	        ListNode right = head;
	        int i;
	        for (i = 0; i < n && right != null; i++) {
	            right = right.next;
	        }
	        if (i < n) {
	            // n is bigger than the length of linkedlist
	            return head;
	        }
	        // left是头指针
	        if (right == null) {
	            return head.next;
	        }
	        ListNode parent = head;
	        ListNode left = head;
	        while (right != null) {
	            parent = left;
	            left = left.next;
	            right = right.next;
	        }
	        parent.next = left.next;
	        left = null; // 不必释放??
	        
	        return head;
	    }
	    public ListNode reverseBetween(ListNode head, int m , int n) {
	        // 确定i < m，i < n这些到底有没有等号，这个也是需要费一番功夫
	        // 特别注意我的程序中reverse = stash.next.next，已经跳了一格，所以相当于i是从m-1开始
	        if (head == null || n <= 1 || m == n) {
	            return head;
	        }
	        ListNode headWrap = new ListNode(0);
	        headWrap.next = head;
	        
	        ListNode stash = headWrap;
	        int i;
	        for (i = 1; stash.next != null && i < m; i++) {
	            stash = stash.next;
	        }
	        if (stash.next == null) {
	            return head;
	        }
	        ListNode prev = stash.next;
	        ListNode reverse = stash.next.next;
	        
	        while (reverse != null && i < n) {
	            prev.next = reverse.next;
	            reverse.next = stash.next;
	            stash.next = reverse;
	            reverse = prev.next;
	            i++;
	        }
	        return headWrap.next;
	    }
}
