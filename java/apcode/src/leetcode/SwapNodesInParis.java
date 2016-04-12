package leetcode;
/**
 * No. 24
 * @author xiedandan
 * Problem analysis: pairs means even,when the list node number is odd, how to do with the last node ?
 * Is The head the first node with value or just a mark node?
 * -- has no mark node.
 */
class ListNode{
	int val;
	ListNode next;
	public ListNode(int x){
		val=x;
	}
	public static void printList(ListNode l){
		System.out.print("###List:");
		while(l!=null){
			System.out.printf("%d,",l.val);
			l=l.next;
		}
		System.out.print(" ###List End###\n");
	}
}
public class SwapNodesInParis {
    public ListNode swapPairs(ListNode head) {
        ListNode headWraper=new ListNode(-1);
        headWraper.next=head;//for add a head
        ListNode node=headWraper;
        
        //precondition: at least has two node
        // step two node every round
        while(node.next!=null && node.next.next!=null){
        	ListNode node1=node.next;
        	ListNode node2=node.next.next;
//        	System.out.println("Node1:"+node1.val+",Node2:"+node2.val);
        	node.next=node2;
        	node1.next=node2.next;
        	node2.next=node1;
        	
//        	node=node2;//错误，bug，没有注意到node2值已经被改变
        	node=node1;
        }
//        ListNode.printList(headWraper);
        return headWraper.next;
    }
    public static void main(String[] args){
    	SwapNodesInParis swapPairs=new SwapNodesInParis();
    	ListNode l=new ListNode(0);
    	ListNode l1=new ListNode(1);
    	l.next=l1;
    	ListNode l2=new ListNode(2);
    	l1.next=l2;
    	ListNode l3=new ListNode(3);
    	l2.next=l3;
    	ListNode l4=new ListNode(4);
    	l3.next=l4;
    	ListNode l5=new ListNode(5);
    	l4.next=l5;
    	
    	ListNode list=l1;
    	ListNode.printList(list);
    	ListNode.printList(swapPairs.swapPairs(list));
    }
}
