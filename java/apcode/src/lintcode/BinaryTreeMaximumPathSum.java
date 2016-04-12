package lintcode;

import algorithms.TreeNode;
import java.util.ArrayList;

/**
 * 这道题的基本思路和最大连续子区间如出一辙：
 * 	在这道题中需要维护一个MaxSub，和一个MaxLocalSub
 * 
 * 思路搞清楚了，但是发现代码实现却无从下手，前面两个例子还是没有做熟悉。对于返回path这种题目要多加注意！
 * 如果这道题加上求path，难度会大大增加，我还需要写写不要path信息的版本，应该会简洁很多。
 * @author xiedandan
 * # 解题报告
 * ## 思路——一句概括这个题让你干什么？
 * ## 有哪些我做过的相关题目
 * - MaximumPathSum ii
 * ## 有哪些条件提示我想到解法
 * ## 模板
 * 测试：{6,4,-10,1,5,#,20,-15,30}
 * 未通过的case：
 * {-1} —— 一个点的时候，结果集不可以为空
 */
public class BinaryTreeMaximumPathSum {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxPathSum(TreeNode root) {
        ReturnType ret = maxPathSumHelper(root);
        return ret.any2any.sum;
    }
    private ReturnType maxPathSumHelper(TreeNode root) {
        ReturnType ret = new ReturnType();
        if (root == null) {
            return ret;
        }
        ReturnType retLeft = maxPathSumHelper(root.left);
        ReturnType retRight = maxPathSumHelper(root.right);
        System.out.println("left--" + retLeft);
        System.out.println("right--" + retRight);
        
        ret.any2any.sum = root.val;//一定要初始化这里，因为默认是MIN
        if (retLeft.root2any.sum > 0) {
            ret.any2any.path.addAll(retLeft.root2any.path);
            ret.any2any.sum += retLeft.root2any.sum;
        }
        ret.any2any.path.add(root.val);
        if (retRight.root2any.sum > 0) {
            ret.any2any.path.addAll(retRight.root2any.path);
            ret.any2any.sum += retRight.root2any.sum;
        }
        
        ret.root2any.path.add(root.val);
        ret.root2any.sum = root.val;
        ReturnType biggerRet = retLeft;
        if (retLeft.root2any.sum < retRight.root2any.sum) {
            biggerRet = retRight;
        }
        if (biggerRet.root2any.sum > 0) {
            ret.root2any.path.addAll(biggerRet.root2any.path);
            ret.root2any.sum += biggerRet.root2any.sum;
        }
        System.out.println("cur--" + ret);
        biggerRet = retLeft;
        if (retLeft.any2any.sum < retRight.any2any.sum) {
            biggerRet = retRight;
        }
        if (biggerRet.any2any.sum < ret.any2any.sum) {
            biggerRet = ret;
        }
        ret.any2any = biggerRet.any2any;
        System.out.println("curFinal--" + ret);
        return ret;
    }
}
class SumPath {
    ArrayList<Integer> path = new ArrayList<Integer>();
    int sum = Integer.MIN_VALUE;
    public String toString() {
        return sum + "," + path.toString();
    }
}
class ReturnType {
    SumPath root2any = new SumPath();
    SumPath any2any = new SumPath();
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("root2any:" + root2any);
        sb.append("  ");
        sb.append("any2any:" + any2any);
        return sb.toString();
    }
}
