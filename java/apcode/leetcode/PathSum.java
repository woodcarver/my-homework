package leetcode;
import algorithms.BinaryTree;
import algorithms.TreeNode;

public class PathSum {
    //焦点：sum的范围，可以是负数吗？ 二叉树节点可以是负数吗？
    //如果有负数的出现，那么必须要遇到rest==0或者root==null的时候才能停止，rest<0则不行
    //题还读错了，path必须是root-to-leaf，不到叶子节点是不行的。
    public boolean hasPathSum(TreeNode root, int sum) {
    //这个假设看来不成立，对于空树的情况和sum=0的情况
    // 	if(sum==0)
    // 		return true;
        return hashPathSumInter(root, sum);
    }
    private boolean hashPathSumInter(TreeNode root,int rest){
    	//看来这个查找树，基本不能裁剪
    	if(root==null)
    		return false;
    	System.out.println("root:"+root.val+",rest:"+rest);
    	rest=rest-root.val;
    	if(rest==0){
    		System.out.println("rest==0");
    		if(root.left==null && root.right==null)//保证leaf node
    			return true;
    		//这里任然不能结束因为就算这是时候已经是零了，但是其可能还有子树是零的情况
//    		else
//    			return false;
    	}
    	//果然有负数情况，我不应该做过多假设
    // 	else if(rest<0)
    // 		return false;
    	return hashPathSumInter(root.left,rest) || hashPathSumInter(root.right,rest);
    	
    }
}
