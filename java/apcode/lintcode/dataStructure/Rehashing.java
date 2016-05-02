package lintcode.dataStructure;

import algorithms.ListNode;
/**
 * 
 * @author xiedandan
 * error case:
 * [80->null,null,null,null,null,null,null,187->null,null,49->109->null,10->50->-10->null,null,12->null,53->133->153->93->null,null,15->null,36->null,-3->null,118->null,159->139->null]
 * output: [80->null,null,null,null,null,null,null,null,null,49->null,10->50->null,null,12->null,133->93->null,null,15->null,null,null,null,139->null,null,null,null,null,null,null,null,187->null,null,109->null,-10->null,null,null,153->null,null,null,36->null,-3->null,118->null,159->null]
 * 就差个53，怎么回事？
 */
public class Rehashing {
    public ListNode[] rehashing(ListNode[] hashTable) {
        // 思路：肯定需要重新开辟一个hashTable
        //       同时肯定不能直接把一个entry的listNode拷贝过去，
        //       因为本来key冲突的rehashing后可能不再冲突了。
        if (hashTable == null || hashTable.length == 0) {
            return null;
        }
        int capacity = hashTable.length;
        int newCapacity = 2 * capacity;
        ListNode headDummy = null;
        ListNode[] rehashTable = new ListNode[newCapacity];
        for (int i = 0; i < capacity; i++) {
            headDummy = new ListNode(0);
            headDummy.next = hashTable[i];
            ListNode entry = hashTable[i];
            if (entry == null) {
                continue;
            }
            while (entry != null) {
                headDummy.next = entry.next; // delete entry
                entry.next = null;
                int hashValue = hashCode(entry.val, newCapacity);
                System.out.println("#### Inner: entry - " + entry.val + ", slot - " + hashValue);
                rehashTable[hashValue] = listTailAdd(rehashTable[hashValue], entry);
                entry = headDummy.next;
            }
        }
        return rehashTable;
    }
    // 这个函数犯的错很多，基本错误都fan到这里了
    private ListNode listTailAdd(ListNode head, ListNode node) {
        if (head == null) {
            head = node;
            return head; // 居然是return忘了加！！！
        }
        ListNode iter = head;
        // 这里发生了死循环
//        while (head.next != null) {
//            head = head.next;
////            System.out.println("----" + head.val);
//        }
//        head.next = node;
        while (iter.next != null) {
        	iter = iter.next;
        }
        iter.next = node;
        return head;
    }
    private int hashCode(int key, int capacity) {
        int code = key % capacity;
        if (code < 0) {
            code += capacity;
        }
        return code;
    }
    private void printHashTable(ListNode[] htable) {
        System.out.println("----HashTable----");
        if (htable == null) {
            System.out.println("The table is null.");
        }
        for (int i = 0; i < htable.length; i++) {
            ListNode head = htable[i];
            if (head == null) {
                System.out.println("null");
                continue;
            }
            while (head != null) {
                System.out.print(head.val + "->");
                head = head.next;
            }
            System.out.print("->N\n");
        }
    }
    
    public static void main(String[] args) {
    	ListNode[] hashTable = new ListNode[3];
    	hashTable[2] = new ListNode(-9);
    	hashTable[2].next = new ListNode(5);
    	
    	Rehashing rhash = new Rehashing();
    	rhash.printHashTable(rhash.rehashing(hashTable));
    }
}
