package lintcode.linkedList;

import algorithms.ListNode;
/**
 * 看答案对fast和slow的初始化时0，1。这样真的不会破坏其数学性质？
 * @author xiedandan
 *
 */
public class LinkedListCycle {
    public ListNode detectCycle(ListNode head) {  
        // 思路：fast和slow 双指针来做，并利用数学方法证明
        // 假设，柄长l，环长c。
        // 1. fast和slow 相遇在环的第x点（从入口处开始计算），
        //    其中slow走了s步，fast走了2s步
        // 2. 因为fast能和slow相遇，那么fast 的步数 2s=nc+s --> s=nc
        // 又假设s=l+x
        // 所以 l+x=nc
        // l=nc-x = (n-1)c+(c-x)=kc+(c-x)
        // 那么要找l的话，需要相撞后，slow从头开始开启走起，
        // fast从相撞点开始走，当他们再次相撞的时候，就是入口点了。
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head.next;
        // ListNode fast = head;
        ListNode fast = head.next.next;
        //一个大问题，就是fast != slow的初始值必须是fast!=slow，但是我的初始值是一样的。
        while (fast != null && fast.next != null && fast != slow) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //have no cycle
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
