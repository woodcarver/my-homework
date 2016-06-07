package lintcode.linkedList;

import algorithms.ListNode;
/*
 * 难点：引用
 */
public class ReverseLinkedList {
    /**
     * @param head: The head of linked list.
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // 采用头插入法，而不是jiuzhang讲的翻转指针
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        // 死循环 version, 结果4,3,2,1,1,1,1,1...
        // 这句的原因，为什么？
//        dummy.next = head;
        ListNode cursor = head;
        while (cursor != null) {
//            ListNode temp = cursor; 
//            cursor = cursor.next; //  这样写是错误的，因为在这里改变cursor，其实也改变了temp，相当于temp的保存是无用的
//            
//            // insert
//            temp.next = dummy.next;
//            dummy.next = temp;

            // System.out.println(cursor.val);
            // 居然会死循环，原因不在这里
            ListNode temp = cursor.next;
            // insert
            cursor.next = dummy.next;
            dummy.next = cursor;
            
            cursor = temp;
        }
        return dummy.next;
    }
}
