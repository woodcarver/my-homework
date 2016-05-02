package lintcode.linkedList;

import algorithms.ListNode;
/**
 * 总结，要添加，删除都需要parent指针，所以在定位的时候注意是否需要向前挪一位
 * @author xiedandan
 * error case:
 * 1->2->0->3->1->2->1->0->2->2->2->1->0->2->null, 2
 *-1->2->0->1->2->1->0->2->2->2->1->0->2->3->null
 *-1->0->1->1->0->1->0->2->3->2->2->2->2->2->null
 * 这个错误够奇葩，是把x写成了3！！
 *
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null || head.next == null) {
            return head;
        }
        // x ~ (-infinit, + infinit)
        ListNode headWrap = new ListNode(0);
        headWrap.next = head;
        // 没有一个空的头指针好难处理，要判断在头部插入数据
        ListNode insertPoint = headWrap;
        while (insertPoint.next != null && insertPoint.next.val < x) {
            insertPoint = insertPoint.next;
        }
        ListNode p = insertPoint;
        while (p.next != null) {
            if (p.next.val < x) {
                //delete
                ListNode temp = p.next;
                p.next = p.next.next;
                //Insert
                temp.next = insertPoint.next;
                insertPoint.next = temp;
                insertPoint = insertPoint.next;
            } else {
                // 这里一定是else ，因为删除操作相当于做了向前跳转
                // 如果删除再多一次跳转，会漏掉检查的元素
                p = p.next; //这个继续条件丢了，真实醉了
            }
        }
        return headWrap.next;
    }
}
