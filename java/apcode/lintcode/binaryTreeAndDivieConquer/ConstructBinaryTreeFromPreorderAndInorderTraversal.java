package lintcode.binaryTreeAndDivieConquer;

import algorithms.TreeNode;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    class PrePos{
        public int pos = 0;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 程序还是递归的, 分冶的方法
    	if (preorder == null || inorder ==null) {
    		return null;
    	}
    	if (preorder.length != inorder.length || preorder.length == 0) {
    		return null;
    	}
    // 	Integer prePos = 0;
        PrePos pos = new PrePos();
    	TreeNode root = buildTreeHelper(preorder, inorder, pos, 0, inorder.length - 1);
    	return root;
    }
    // prePos怎么全递归树种传递？
    // 难点，怎么对prePos进行引用传递，也就是怎么算prePos的位置？
    private TreeNode buildTreeHelper(int[] preorder, int[] inorder,
    	PrePos prePos, int left, int right) {
    	int pos =  prePos.pos;
    // 	System.out.println(pos + "," + left + "," + right);
    	if (left > right || pos >= preorder.length) {
    		return null;
    	}
    	if (left == right) {
    		return new TreeNode(preorder[pos]);
    	}
    	TreeNode root = new TreeNode(preorder[pos]);
    	int middle = 0;
    	for (int i = left; i <= right; i++) {
    		if (inorder[i] == preorder[pos]) {
    			middle = i;
    			break;
    		}
    	}
    // 	System.out.println("middle:" + middle + "," + preorder[pos]);
    	// case: [[1,2],[1,2]]
    	if (left <= middle - 1) {
    	    prePos.pos++;
    	    root.left = buildTreeHelper(preorder, inorder, prePos, left, middle - 1);
    	}
    	if (middle + 1 <= right) {
    	    prePos.pos++;
    	    root.right = buildTreeHelper(preorder, inorder, prePos, middle + 1, right);
    	}
    	return root;
    }
}