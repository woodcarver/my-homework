package algorithms;

import java.util.*;

public class BinaryTree {
    public List<Integer> inorderTraversal(TreeNode root) {
    	LinkedList<Integer> inorder=new LinkedList<Integer>();
    	inorderTraversalInner(root,inorder);
    	System.out.println("#####"+inorder);
    	return inorder;
    }
    //没有append(LinkedList)方法吗？类似于node.next=new_node这样，不然就要传递丑陋的有副作用的参数inorder
    private void inorderTraversalInner(TreeNode root,LinkedList<Integer> inorder) {   	
        if(root==null){
        	return;
        }
        inorderTraversalInner(root.left,inorder);
        inorder.add(root.val);
        
        System.out.print(root.val+",");
        inorderTraversalInner(root.right,inorder);
    }
    public List<Integer> inorderTraversal2(TreeNode root) {
    	List<Integer> inorder=new LinkedList<Integer>();

        if(root==null){
        	return inorder;
        }
        
        inorder=inorderTraversal2(root.left);
    	inorder.add(root.val);
    	//这种方法效率有问题，是O(n)的
    	inorder.addAll((Collection<? extends Integer>)
    			inorderTraversal2(root.right));
    	return inorder;
    }

    //有副作用，必须拷贝——好像不用，传递过来的引用就是份拷贝
    private int maxSubtreeVal(final TreeNode root){
    	int maxVal=Integer.MIN_VALUE;//错误，是MIN_VALUE not MAX_VALUE
    	TreeNode node=root;
    	while(node!=null){
    		maxVal=node.val;
    		node=node.right;
    	}
//    	System.out.println("maxSubtreeVal:"+root.val+","+maxVal);
    	return maxVal;
    }
    private int minSubtreeVal(final TreeNode root){
    	int minVal=Integer.MAX_VALUE;
    	TreeNode node=root;
    	while(node!=null){
    		minVal=node.val;
    		node=node.left;
    	}
//    	System.out.println("minSubtreeVal:"+root.val+","+minVal);
    	return minVal;
    }
    public boolean isValidBST(TreeNode root){
    	if(root==null){
    		return true;
    	}
    	boolean leftRet=false;
    	boolean rightRet=false;
    	//precondition: !root.left || (root.left.val<=root.val&&isValidBST(root.left))
    	if(root.left==null){
    		leftRet=true;
    	}
    	else if(maxSubtreeVal(root.left)<root.val){//precondition: root.left is not null
    		//非常tricky，这个不是仅仅小于root.left.val,而是要大于max（leftsubtree）
    		leftRet=isValidBST(root.left);
    	}
    	
    	//下面代码已经被简化
//    	if(root.right==null){
//    		rightRet=true;
//    	}
    	if(root.val<minSubtreeVal(root.right)){
    		rightRet=isValidBST(root.right);
    	}
    	System.out.println(root.val+":"+leftRet+","+rightRet);//这次输出，表示的是后续遍历
    	return leftRet&&rightRet;
    }
    public static void main(String[] args){
//    	int[] arr={};
//    	int[] arr={1,1};//should be false, which means the node value can not be equal
//    	int[] arr={1,2};
    	int[] arr={1,2,3,4};
//    	int[] arr={1,-1,2,3};//132
//    	int[] arr={5,4,8,3,-1,6,9,2};
//    	int[] arr={5,4,8,3,11,-1,13,4,7,-1,-1,2,-1,-1,-1,1};
//    	int [] arr={10,5,15,-1,-1,6,20};//验证是否是二叉查找树，太好的例子了，我的方法果然有问题，只考虑了局部，但是没有考虑全局
//    	int[] arr={1,2,3,-1,-1,4,-1,-1,5};//21453
//    	int[] arr={1,-2,-3,1,3,-2,8888,-1};
    	TreeNode tree=TreeNode.buildTreeByArray(arr);
    	BinaryTree binaryTree=new BinaryTree();
    	List<Integer> inorder=binaryTree.inorderTraversal2(tree);
    	System.out.println("#####"+inorder);
//    	System.out.println(binaryTree.isValidBST(tree));
    }
}
