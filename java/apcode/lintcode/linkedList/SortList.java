package lintcode.linkedList;

import algorithms.ListNode;

public class SortList {
	/**********
	 * 下面这个方法是一个错误的写发，而且很难以纠正过来，因为需要多很多标示链表分区结束的参数—— 打脸了，看了答案后我还是轻松改过来了。
	 */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        head = mergeSort(head, tail);
        return head;
    }
    private ListNode mergeSort(ListNode start, ListNode end) {
        if (start == null || end == null) {
            return null;
        }
        if (start == end) {
            return start;
        }
        ListNode middle = findMidleNode(start, end);
        ListNode right = mergeSort(middle.next, end);
        middle.next = null; //这个地方的写法太妙了
        ListNode left = mergeSort(start, middle);
        return megerTwoList(left, right);
    }
    private ListNode findMidleNode(ListNode start, ListNode end) {
        if (start == null || end == null) {
            return null;
        } 
        ListNode fast = start;
        ListNode slow = start;
        while (fast != end && fast.next != end) {
        	// 果然这里放了检查fast.next的范围了！
        	// fast.next.next有可能是空，一定要想清楚。
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    private ListNode megerTwoList(ListNode left, ListNode right) {
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                tail.next = left;
                left = left.next;
            } else {
                tail.next = right;
                right = right.next;
            }
            tail = tail.next;
        }
        if (left != null) {
            tail.next = left;
        } else if (right != null) {
            tail.next = right;
        }
        
        return dummy.next;
    }
}

/**
 * jiuzhang answer
 */

//version 1: Merge Sort
class JiuzhangSortList {            
 private ListNode findMiddle(ListNode head) {
     ListNode slow = head, fast = head.next;
     while (fast != null && fast.next != null) {
         fast = fast.next.next;
         slow = slow.next;
     }
     return slow;
 }    

 private ListNode merge(ListNode head1, ListNode head2) {
     ListNode dummy = new ListNode(0);
     ListNode tail = dummy;
     while (head1 != null && head2 != null) {
         if (head1.val < head2.val) {
             tail.next = head1;
             head1 = head1.next;
         } else {
             tail.next = head2;
             head2 = head2.next;
         }
         tail = tail.next;
     }
     if (head1 != null) {
         tail.next = head1;
     } else {
         tail.next = head2;
     }

     return dummy.next;
 }

 public ListNode sortList(ListNode head) {
     if (head == null || head.next == null) {
         return head;
     }

     ListNode mid = findMiddle(head);

     ListNode right = sortList(mid.next);
     mid.next = null;
     ListNode left = sortList(head);

     return merge(left, right);
 }
}

//version 2: Quick Sort 1
class Solution2 {
 public ListNode sortList(ListNode head) {
     if (head == null || head.next == null) {
         return head;
     }
     
     ListNode mid = findMedian(head); // O(n)
     
     ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
     ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
     ListNode middleDummy = new ListNode(0), middleTail = middleDummy;
     while (head != null) {
         if (head.val < mid.val) {
             leftTail.next = head;
             leftTail = head;
         } else if (head.val > mid.val) {
             rightTail.next = head;
             rightTail = head;
         } else {
             middleTail.next = head;
             middleTail = head;
         }
         head = head.next;
     }
     
     leftTail.next = null;
     middleTail.next = null;
     rightTail.next = null;
     
     ListNode left = sortList(leftDummy.next);
     ListNode right = sortList(rightDummy.next);
     
     return concat(left, middleDummy.next, right);
 }
 
 private ListNode findMedian(ListNode head) {
     ListNode slow = head, fast = head.next;
     while (fast != null && fast.next != null) {
         slow = slow.next;
         fast = fast.next.next;
     }
     return slow;
 }
 
 private ListNode concat(ListNode left, ListNode middle, ListNode right) {
     ListNode dummy = new ListNode(0), tail = dummy;
     
     tail.next = left; tail = getTail(tail);
     tail.next = middle; tail = getTail(tail);
     tail.next = right; tail = getTail(tail);
     return dummy.next;
 }
 
 private ListNode getTail(ListNode head) {
     if (head == null) {
        return null;
     } 
    
     while (head.next != null) {
         head = head.next;
     }
     return head;
 }
}

//version 3: Quick Sort 2
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
class Pair {
 public ListNode first, second; 
 public Pair(ListNode first, ListNode second) {
     this.first = first;
     this.second = second;
 }
}

class Solution3 {
 /**
  * @param head: The head of linked list.
  * @return: You should return the head of the sorted linked list,
                 using constant space complexity.
  */
 public ListNode sortList(ListNode head) {
     if (head == null || head.next == null) {
         return head;
     }
     
     ListNode mid = findMedian(head); // O(n)
     Pair pair = partition(head, mid.val); // O(n)
     
     ListNode left = sortList(pair.first);
     ListNode right = sortList(pair.second);
     
     getTail(left).next = right; // O(n)
     
     return left;
 }
 
 // 1->2->3 return 2
 // 1->2 return 1
 private ListNode findMedian(ListNode head) {
     ListNode slow = head, fast = head.next;
     while (fast != null && fast.next != null) {
         slow = slow.next;
         fast = fast.next.next;
     }
     return slow;
 }
 
 // < value in the left, > value in the right
 private Pair partition(ListNode head, int value) {
     ListNode leftDummy = new ListNode(0), leftTail = leftDummy;
     ListNode rightDummy = new ListNode(0), rightTail = rightDummy;
     ListNode equalDummy = new ListNode(0), equalTail = equalDummy;
     while (head != null) {
         if (head.val < value) {
             leftTail.next = head;
             leftTail = head;
         } else if (head.val > value) {
             rightTail.next = head;
             rightTail = head;
         } else {
             equalTail.next = head;
             equalTail = head;
         }
         head = head.next;
     }
     
     leftTail.next = null;
     rightTail.next = null;
     equalTail.next = null;
     
     if (leftDummy.next == null && rightDummy.next == null) {
         ListNode mid = findMedian(equalDummy.next);
         leftDummy.next = equalDummy.next;
         rightDummy.next = mid.next;
         mid.next = null;
     } else if (leftDummy.next == null) {
         leftTail.next = equalDummy.next;
     } else {
         rightTail.next = equalDummy.next;
     }
     
     return new Pair(leftDummy.next, rightDummy.next);
 }
 
 private ListNode getTail(ListNode head) {
     if (head == null) {
        return null;
     } 
    
     while (head.next != null) {
         head = head.next;
     }
     return head;
 }
}