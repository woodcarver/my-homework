package lintcode.dataStructure;

import java.util.HashMap;
/**
 * [4,3,2,-1,-1,2,3,-1,5]
 * @author xiedandan
 *
 */
public class LRU {
    // 内部类和外部类有什么区别，怎么分场景使用？
    private class LRUNode {
        int key;
        int val;
        LRUNode next;
        LRUNode prev; // 为了tail被删除后，能找到下一个tail
        LRUNode(int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
    }
    // 类的成员变量初始化在定义的时候，还是在构造函数中比较好？
    private HashMap<Integer, LRUNode> map;
    private final int MAX_CAPACITITY = Integer.MAX_VALUE;
    private int capacity;
    private int size;
    private LRUNode head;
    private LRUNode tail;
    
    // @param capacity, an integer
    public LRU(int capacity) {
        // version1: linkedlist - get: O(n), set: O(1), remove: O(1)
        // version2: hashmap - get: O(1), set: O(n), remove: O(n)
        // version3: linkedhashmap -  get: O(1), set: O(1), remove: O(1)
        if (capacity < 0 || capacity > MAX_CAPACITITY) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                capacity); 
        }
        this.capacity = capacity;
        map = new HashMap<Integer, LRUNode>(capacity);
        size = 0;
        head = null;
        tail = null;
    }

    // @return an integer
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 每访问一次，都要调整节点的顺序
        reorder(key);
        return map.get(key).val;
    }
    private void reorder(int key) {
        // System.out.println("reorder:" + key);
        LRUNode orderNode = map.get(key);
        if (orderNode.prev != null) {
            orderNode.prev.next = orderNode.next;
        } else {
            // orderNode is head, no need to do anython
            return;
        }
        if (orderNode.next != null) {
            orderNode.next.prev = orderNode.prev;
        } else {
            // orderNode is tail
            orderNode.prev.next = null;
            tail = orderNode.prev;
        }
        orderNode.next = head;
        if (head != null) {
            head.prev = orderNode;
        }
        head = orderNode;
        // printList(head);
    }
    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // System.out.println("get:" + key + "," + value + "," + size);
        if (size >= capacity) {
            removeTail();
            size--;
        }
        addHead(key, value);
        size++;
    }
    private void addHead(int key, int value) {
        // System.out.println("add:" + key + "," + value);
        LRUNode newNode = new LRUNode(key, value);
        newNode.next = head;
        newNode.prev = null;
        if (head != null) { // 这里没有检查，一定要注意这种没有dummyheader的链表
            head.prev = newNode;
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        
        map.put(key, newNode);
        // printList(head);
    }
    private void removeTail() {
        // invalidate the least recently
        map.remove(tail.key);
        // System.out.println("remove:" + tail.key + "," + tail.val);
        LRUNode prevNode = tail.prev;
        if (prevNode != null) {
            prevNode.next = null;
        }
        tail.prev = null;
        tail = prevNode;
    }
    private void printList(LRUNode node) {
        while (node != null) {
            System.out.print("(" + node.key + "," + node.val + "),");
            node = node.next;
        }
        System.out.println("##end");
    }
}
class LRU2 {
    // 内部类和外部类有什么区别，怎么分场景使用？
    private class LRUNode {
        int key;
        int val;
        // 为什么要用双链表？
        LRUNode next;
        LRUNode prev;
        LRUNode(int key, int val) {
            this.key = key;
            this.val = val;
            next = null;
            prev = null;
        }
        void printNode() {
            System.out.println("Node:" + key + "," + val);
        }
    }
    // 类的成员变量初始化在定义的时候，还是在构造函数中比较好？
    private HashMap<Integer, LRUNode> map;
    private final int MAX_CAPACITITY = Integer.MAX_VALUE;
    private int capacity;
    private int size = 0;
    private LRUNode head = new LRUNode(-1, -1);
    private LRUNode tail = new LRUNode(-1, -1);
    
    // @param capacity, an integer
    public LRU2(int capacity) {
        // version1: linkedlist - get: O(n), set: O(1), remove: O(1)
        // version2: hashmap - get: O(1), set: O(n), remove: O(n)
        // version3: linkedhashmap -  get: O(1), set: O(1), remove: O(1)
        if (capacity < 0 || capacity > MAX_CAPACITITY) {
            throw new IllegalArgumentException("Illegal Capacity: "+
                capacity); 
        }
        this.capacity = capacity;
        map = new HashMap<Integer, LRUNode>(capacity);
        head.next = tail;
        tail.prev = head;
    }

    // @return an integer
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        // 每访问一次，都要调整节点的顺序
        reorder(key);
        return map.get(key).val;
    }
    private void reorder(int key) {
        // System.out.println("reorder:" + key);
        LRUNode orderNode = map.get(key);
        orderNode.prev.next = orderNode.next;
        orderNode.next.prev = orderNode.prev;
        
        orderNode.prev = tail.prev;
        tail.prev.next = orderNode;
        orderNode.next = tail;
        tail.prev = orderNode;
        // printList(head);
    }
    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // System.out.println("get:" + key + "," + value + "," + size);
        if (get(key) != -1) { // 这里一定要调用get方法，因为set也需要调整位置
            map.get(key).val = value;
            return;
        }
        if (size >= capacity) {
            removeHead();
            size--;
        }
        addTail(key, value);
        size++;
    }
    private void addTail(int key, int value) {
        // System.out.println("add:" + key + "," + value);
        LRUNode newNode = new LRUNode(key, value);
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        newNode.next = tail;
        tail.prev = newNode;
        
        map.put(key, newNode);
        // printList(head);
    }
    private void removeHead() {
        // invalidate the least recently
        if (head.next != tail) {
            // System.out.println("remove:" + head.next.key + "," + head.next.val);
            map.remove(head.next.key);
            head.next = head.next.next;
            // head.next.next.prev = head;
            head.next.prev = head;//???,因为副作用！！！，因为上一步已经改变了节点的指向
        }
    }
    private void printList(LRUNode node) {
        while (node != null) {
            System.out.print("(" + node.key + "," + node.val + "),");
            node = node.next;
        }
        System.out.println("##end");
    }
}

class LRUCache {
    private class Node{
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> hs = new HashMap<Integer, Node>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    public LRUCache(int capacity) {
        this.capacity = capacity;
        tail.prev = head;
        head.next = tail;
    }

    public int get(int key) {
        if( !hs.containsKey(key)) {
            return -1;
        }

        // remove current
        Node current = hs.get(key);
        current.prev.next = current.next;
        current.next.prev = current.prev;

        // move current to tail
        move_to_tail(current);

        return hs.get(key).value;
    }

    public void set(int key, int value) {
        if( get(key) != -1) {
            hs.get(key).value = value;
            return;
        }

        if (hs.size() == capacity) {
            hs.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        Node insert = new Node(key, value);
        hs.put(key, insert);
        move_to_tail(insert);
    }

    private void move_to_tail(Node current) {
        current.prev = tail.prev;
        tail.prev = current;
        current.prev.next = current;
        current.next = tail;
    }
}
