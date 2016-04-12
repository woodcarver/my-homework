package leetcode;
/**
 * No. 257
 * @author xiedandan
 * 开始考虑时间问题了，要保证easy的题能在30min中内做完。
 * 这道题：44min
 * 运行时长28ms，证明使用addAll不是个好主意，还是使用全局变量比较好
 */
import algorithms.*;
import java.util.*;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
//        List<Integer> =new ArrayList<>();//it's no need to do this anymore.
        return binaryTreePathsInner(root,"");
    }
    private List<String> binaryTreePathsInner(TreeNode root,String path){
    	List<String> list=new ArrayList<String>();
    	if(root==null){
    		return list;
    	}
    	path=path+root.val;
    	System.out.println("--"+path);
    	if(root.left==null && root.right==null){
//    		String path="";
//    		for(Integer i:stack)
//    			path+=i+"->";
//    		list.add(path.substring(0,path.length()-2));//ugly!!!
    		list.add(path);
    		return list;
    	}
    	list.addAll(binaryTreePathsInner(root.left,path+"->"));
    	list.addAll(binaryTreePathsInner(root.right,path+"->"));
    	return list;
    }
    public static void main(String[] args){
//    	int[] arr={};
//    	int[] arr={1,1};//should be false, which means the node value can not be equal
//    	int[] arr={1,2};
    	int[] arr={1,2,3,4};
    	TreeNode tree=TreeNode.buildTreeByArray(arr);
    	BinaryTree binaryTree=new BinaryTree();
    	List<Integer> inorder=binaryTree.inorderTraversal2(tree);
    	System.out.println("#####"+inorder);
    	
    	BinaryTreePaths path=new BinaryTreePaths();
    	System.out.println(path.binaryTreePaths(tree));
    }
}
