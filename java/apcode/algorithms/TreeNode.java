package algorithms;

import java.util.LinkedList;
import java.util.Queue;

//二叉查找树的节点key值能对应多个value吗？现在有点理解聚集所以，因为可以保证key唯一，但是非聚集索引呢？MyIsam是怎么做的？
public class TreeNode {
  public int val;
  public TreeNode left;
  public TreeNode right;
  public static int terminalMark=8888;
  public TreeNode(int x) { val = x; }
  public static TreeNode buildTreeByArray(int[] array){
  	if(array==null || array.length==0){
  		return null;
  	}
  	TreeNode root=new TreeNode(array[0]);
  	TreeNode actNode=root; 
  	Queue<TreeNode> treeList=new LinkedList<TreeNode>();
  	treeList.add(actNode);
  	int i,len=array.length;
  	for(i=1; i<len-1; i=i+2){
  		TreeNode left=null;
  		TreeNode right=null;
  		if(array[i]!=terminalMark){
  			left=new TreeNode(array[i]);
  			treeList.add(left);
  		}
  		if(array[i+1]!=terminalMark){
  			right=new TreeNode(array[i+1]);
  			treeList.add(right);
  		}
  		//怎么建立，看自己
  		actNode=treeList.poll();
//  		System.out.println(actNode.val);
  		if(actNode!=null){
  			actNode.left=left;
  			actNode.right=right;
  		}
  	}
  	//注意当不是最小步跳跃的时候，一定要考虑剩下的未量。遇到遍历，一定考虑是否能遍历全
  	if(i<len){
	    	actNode=treeList.poll();
//			System.out.println(actNode.val);
			if(actNode!=null){
				actNode.left=new TreeNode(array[i]);;
			}
  	}
  	return root;
  }
}
