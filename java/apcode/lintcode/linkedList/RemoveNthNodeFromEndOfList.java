package lintcode.linkedList;

import algorithms.ListNode;
/**
 * 重点：
 * 	1. dummy
 *  2. left right node
 * 难点：
 * 	1. 边界检查，特别left/right中间的间隔计算，很容易算错，需要不停的用用例检查。例如 1->null
 *     现在看来最有效的检查错误的方法就是small case/ test case
 * 错点：
 *  2. right or right.next??
 */
public class RemoveNthNodeFromEndOfList {
    /**
     * @param head: The first node of linked list.
     * @param n: An integer.
     * @return: The head of linked list.
     */
    ListNode removeNthFromEnd(ListNode head, int n) {
        // 指针的问题：1. 头指针的问题，总是要特殊处理。
        // 2. 对于删除一定要定位到其parent，至于要不是要用这个显示的指针，我倾向于用，不容易出错。
        // 3. 当然还有永远的边界情况考虑，比如此题的n=0的时候
        if (head == null || n < 1) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode right = head;
        for (int i = 0; i < n; i++) {
            if (right == null) {
                // n is bigger than list length
                return head;
            }
            right = right.next;
        }
        
        ListNode left = dummy;
        while (right != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        
        return dummy.next;
    }
}
