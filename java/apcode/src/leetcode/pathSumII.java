package leetcode;

import algorithms.BinaryTree;
import algorithms.TreeNode;

import java.util.*;
/**
 * No. 113
 * @author xiedandan
 *
 */
public class pathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result=new ArrayList<>();
        ArrayList<Integer> stack=new ArrayList<>();
        pathSumInner(root,sum,0,stack,result);
        return result;
    }
    private void pathSumInner(TreeNode root,int rest,int level,ArrayList<Integer> stack,List<List<Integer>> result){
    	if(root==null)
    		return;
    	stack.add(level, root.val);
    	rest=rest-root.val;
    	if(rest==0 && root.left==null && root.right==null){
    		System.out.println("$$$stack:"+stack.subList(0, level+1)+",level"+level);
//    		result.add(stack.subList(0, level+1));//runtime error on leetcode
    		result.add(new ArrayList<Integer>(stack.subList(0, level+1)));//必须copy一份才行，不能向上面那样写，那样直接引用
    	}
    	pathSumInner(root.left,rest,level+1,stack,result);
    	pathSumInner(root.right,rest,level+1,stack,result);
    }
    public static void main(String[] args){
    	pathSumII pathSum=new pathSumII();
//    	int[] arr={};
//    	int[] arr={1,1};//should be false, which means the node value can not be equal
//    	int[] arr={1,2};
//    	int[] arr={1,2,3,4};
//    	int[] arr={5,4,8888,11,2};
    	int[] arr={1,-2,3};
//    	int[] arr={5,4,8,11,8888,13,4,7,8888,8888,2,8888,8888,5,1};
    	int sum=-1;
    	TreeNode tree=TreeNode.buildTreeByArray(arr);
    	BinaryTree binaryTree=new BinaryTree();
    	List<Integer> inorder=binaryTree.inorderTraversal2(tree);
    	System.out.println("#####"+inorder);
    	List<List<Integer>> res=pathSum.pathSum(tree, sum);
    	for(int i=0,len=res.size();i<len;i++){
    		System.out.println(res.get(i));//why java.util.ConcurrentModificationException，见resuld.add哪行的bug
    	}
    }
}
