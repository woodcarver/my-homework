package lintcode;
/**
 * 我注意一个现象就是到动规这章，会有部分case过，部分case不过，为什么？？怎么会有这么多的边界情况，要特注意。
 * @author xiedandan
 * error cases:
 * "abccccccccccde", "dbccccccabccde"
 */
public class LongestCommonSubstring {
    public int longestCommonSubstring(String A, String B) {
        // 暴力法：算出A的所有子串，算出B的所有子串，然后比对找出最长的。m^2 * n^2
        // 归纳法：这是一个最后连续子串，和记录全局最大子串。每当遍历一个子串的时候看看当前最后连续子串是否长于了全局子串，是的话就替换。
        // 时间复杂度：ALen * (BLen - MaxAsub) * MaxAsub ~ O(m*n*l) 
        //             是否还可以优化？？
        // 空间复杂度：O(l)
        // 相似的题：
        // 方法标签：归纳法，动态规划法？
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int ALen = A.length();
        int BLen = B.length();
        int globalSubLen = 0;
        int localSubLen = 0;
        int localStart = 0;
        for (int i = 0; i < ALen; i++) {
            String ASub = A.substring(localStart, i + 1);
            // System.out.println("Asub:" + ASub);
            boolean hasFound = false;
            for (int j = 0; j < BLen - ASub.length() + 1; j++) {
                if (ASub.equals(B.substring(j, j + ASub.length()))) {
                    globalSubLen = Math.max(globalSubLen, i - localStart + 1);
                    hasFound = true;
                }
            }
            if (!hasFound) {
//            	localStart = i;
                localStart = localStart + 1;// 这里应该是localStart + 1，而不是i。注意不要多跳位置，不然有些情况会被漏掉。
            }
        }
        return globalSubLen;
    }
}
