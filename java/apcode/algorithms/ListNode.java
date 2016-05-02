package algorithms;

public class ListNode {
	   public int val;
	   public ListNode next;
	   public ListNode(int val) {
	       this.val = val;
	       this.next = null;
	   }

	   public String toString() {
		   return ((Integer)val).toString();
	   }
}
