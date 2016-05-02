package lintcode.linkedList;

class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
};
public class CopyListWithRandomPointer {
	public RandomListNode copyRandomList(RandomListNode head) {
        // 思路：如果一开始就单独复制一个list，那么copy list和raw
        // list的关系就失去了，也就是没有办法寻找到random的复制方法。
        // 所以为了让raw和copy之间有联系，所以做法是间隔复制发
        // 1. 间隔复制
        // 2. 复制random
        // 3. 拆分list -- 不知道原题是否要求保留对rawlist不改变
        // 问题：还是万年不变的边界检查问题，访问之前一定要检查！！！！
        if (head == null) {
            return null;
        }
        head = doubleNode(head);
        head = copyRandom(head);
        RandomListNode copyList = split(head);
        return copyList;
    }
    private RandomListNode doubleNode(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode iter = head;
        while (iter != null) {
            RandomListNode copyNode = new RandomListNode(iter.label);
            copyNode.next = iter.next;
            iter.next = copyNode;
            iter = copyNode.next;
        }
        // System.out.println("doubleNode");
        return head;
    }
    private RandomListNode copyRandom(RandomListNode head) {
        if (head == null) {
            return head;
        }
        RandomListNode iter = head;
        while (iter != null && iter.next != null) {
            if (iter.random == null) {// 居然还有random是null的情况
                iter = iter.next.next;
                continue;
            }
            iter.next.random = iter.random.next;
            iter = iter.next.next;
        }
        // System.out.println("copyRandom");
        return head;
    }
    private RandomListNode split(RandomListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        RandomListNode iter = head.next;
        while (iter != null && iter.next != null) {
            iter.next = iter.next.next;
            iter = iter.next;
        }
        return head.next;
    }
}
