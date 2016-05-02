package lintcode;

import algorithms.TreeNode;
import java.util.ArrayList;

/**
 *   
  4
 / \
3   7
   / \
  5   6
 * LCA(3, 5) = 4
 * LCA(5, 6) = 7
 * LCA(6, 7) = 7
 *
 * 写的非常困难！！！这块要好好补补
 * @author xiedandan
 * 设计的测试用例：
 * {4,3,7,#,#,5,6}, 4, 6
 * {4,3,7,#,#,5,6}, 3, 6
 * 
 * {},1,1
 * {1},1,1
 * {4,1}, 4, 6   ---> 居然返回4，树种都没有6节点
 * 
 * 看了答案，我简直是要崩溃了！为何答案如此简单，而我的写法如此是之复杂！！
 */
public class LowestCommonAncestor {
	// 这个思路很简单，就是找一个A，B两个点的path，然后核对一遍path
	// 时间复杂度 2n + n ==> O(n)， 空间O(2n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B){
        if (root == null || A == null || B == null) {
            return null;
        }
        ArrayList<TreeNode> pathA = new ArrayList<TreeNode>();
        findPath(root, A, pathA);
        ArrayList<TreeNode> pathB = new ArrayList<TreeNode>();
        findPath(root, B, pathB);
        int i = 0;
        while (i < pathA.size() && i< pathB.size() && pathA.get(i) == pathB.get(i)) {
            i++;
        }
        if (i < 1) {
            return null;
        }
        return pathA.get(i - 1);
    }
    private boolean findPath(TreeNode root, TreeNode target, ArrayList<TreeNode> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        //if (target.val == root.val) { //是否应该是下面这种方式进行判等？毕竟val可以重复
        if (target == root) {
            return true;
        }
        if (findPath(root.left, target, path)) {
            return true;
        }
        if (findPath(root.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }
    // 这个方法些的非常困难，不知道问题出在哪里？为什么把简单的思路转化成代码有时候会这么难？同样的问题发生在
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode A, TreeNode B){
        TreeNode ancestor = null;
        lowestCommonAncestorHelper(root, A, B, ancestor);
        return ancestor;
    }
    private int lowestCommonAncestorHelper(TreeNode root, TreeNode A, TreeNode B, TreeNode ancestor) {
        if (root == null) {
            return 0;
        }
        if (root == A || root == B) {
            if(1 == lowestCommonAncestorHelper(root.left, A, B, ancestor) ||
                1 == lowestCommonAncestorHelper(root.right, A, B, ancestor)) {
                ancestor = root;
                return 2;
            } else {
                return 1;
            }
        } else {
            int leftRet = lowestCommonAncestorHelper(root.left, A, B, ancestor);
            if (2 == leftRet) {
                return 2;
            }
            if (1 == leftRet) {
                int rightRet = lowestCommonAncestorHelper(root.right, A, B, ancestor);
                if ( 1 == rightRet) {
                   ancestor = root;
                   return 2;
               } else {
                   return 1;
               }
            }
            // 可以判断leftRet == 0 && rightRet == 0了
            return 0;
        }
    }
}
