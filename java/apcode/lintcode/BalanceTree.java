package lintcode;

import algorithms.TreeNode;

/**
 * 犯了大错误：
 * case 如下：{1,2,3,4,#,5,6,7,8,9,10,11,12}
 * @author xiedandan
 * 这道题还有重点就是需要一个复合返回值，结合MaximumPathSum体会复合返回值的用法
 * 还有什么时候用参数，什么时候用返回值，这个也需要体会。什么时候用遍历，什么时候用分冶。。。It's question.
 */
public class BalanceTree {
    public boolean isBalanced(TreeNode root) {
        ReturnType ret = isBalancedHelper(root);
        return ret.isBalanced;
    }
    private ReturnType isBalancedHelper(TreeNode root) {
        ReturnType ret = new ReturnType();
        if (root == null) {
            return ret;
        }
        ReturnType retLeft = isBalancedHelper(root.left);
        ReturnType retRight = isBalancedHelper(root.right);
        ret.height = Math.max(retLeft.height, retRight.height) + 1;
        //这里这么简单的判断是不对了的，一定要验证子树是否是平衡树，并且自己也是平衡的，两个条件缺一不可
        if (retLeft.isBalanced && retRight.isBalanced) {
        	ret.isBalanced = Math.abs(retLeft.height - retRight.height) > 1 ? false : true;
        } else {
        	ret.isBalanced = false;
        }
        return ret;
    }
}
class ReturnType {
    int height = 0;
    boolean isBalanced = true;
}
