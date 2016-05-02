package lintcode;
/**
 * 模板题，鼻祖题！！！！
 * @author xiedandan
 *
 */
public class LongestCommonSubSequence {
    public int longestCommonSubsequence(String A, String B) {
        // 归纳法貌似行不通，因为前面的subsequence会死灰复燃
        // 这道题怎么感觉和Longest Increasing Subsequence (LIS)很像呢？对比的子串都是不可以往低处跳，从当前起点开始只能往高处跳。
    	// 这个方法的一个严重问题就是当字符出现重复的时候，其位置有多个。
    	// String A = "abca";
    	// String B = "abca";
    	// 比如这个case，因为a重复，导致结果是3， 其实应该是4
    	// 这种方法是错的，且没有修正的方案（至少我想不到）
    	//
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int ALen = A.length();
        int BLen = B.length();
        int[] posMap = new int[ALen];
        
        // initail posMap
        for (int i = 0; i < ALen; i++) {
            // Initial
            posMap[i] = -1;
            for (int j = 0; j < BLen; j++) {
                if (A.charAt(i) == B.charAt(j)) {
                    posMap[i] = j;
                    break;
                }
            }
        }
        printArray(posMap);
        // turn to Longest Increasing Subsequence
        int[] dp = new int[ALen];
        dp[0] = 1;
        int max = dp[0];
        for (int i = 1; i < ALen; i++) {
            for (int j = 0; j < i; j++) {
                if (posMap[i] > posMap[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    public int longestCommonSubsequence2(String A, String B) {
        // 基础思想居然和Edit Distance一样，大出我所料。 果然Edia Distance是所有双序列动态规划的鼻祖。
    	// 这个是模板，一定要记住。
    	// 目前我是清楚坐标型和序列型的intuition sense。
    	// ------------------------------------
    	// • state: f[i][j]代表了第一个sequence的前i个数字/字符，配上第二个sequence的前j个...
    	// • function: f[i][j] = 研究第i个和第j个的匹配关系
    	// • initialize: f[i][0] 和 f[0][i]
    	// • answer: f[n][m]
    	// • n = s1.length()
    	// • m = s2.length()
    	// -------------------------------------
    	// state: f[i][j]表示前i个字符配上前j个字符的LCS的长度
    	// • function: f[i][j] = MAX(f[i-1][j], f[i][j-1], f[i-1][j-1] + 1) // A[i - 1] == B[j - 1]
    	// • 				   = MAX(f[i-1][j], f[i][j-1]) // A[i - 1] != B[j - 1]
    	// • intialize: f[i][0] = 0 f[0][j] = 0
    	// • answer: f[n][m]
    	
        if (A == null || B == null || A.length() == 0 || B.length() == 0) {
            return 0;
        }
        int ALen = A.length();
        int BLen = B.length();
        int[][] dp = new int[ALen + 1][BLen + 1];
//        for (int i = 0; i < ALen; i++) {
//        	dp[i][0] = 0;
//        }
//        for (int j = 0; j < BLen; j++) {
//        	dp[0][j] = 0;
//        }
        
        for (int i = 1; i < ALen + 1; i++) {
        	for (int j = 1; j < BLen + 1; j++) {
        		if (A.charAt(i - 1) == B.charAt(j - 1)) {
        			dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        			dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
        		} else {
        			dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        		}
        	}
        }
        return dp[ALen][BLen];
    }
    private int findPos(String S, char C, int next) {
    	int SLen = S.length();
    	int round = 0;
    	int k;
    	for (k = 0; round < next && k < SLen; k++) {
    		if (S.charAt(k) == C) {
    			round++;
    		}
    	}
    	return k;
    }
    private void printArray(int[] array) {
    	for (int i = 0; i < array.length; i++) {
    		System.out.print(array[i] + ",");
    	}
    	System.out.println("over");
    }
    public static void main(String[] args) {
    	LongestCommonSubSequence lcs = new LongestCommonSubSequence();
    	String A = "abca";
    	String B = "agbcagg";
    	System.out.println("result:" + lcs.longestCommonSubsequence2(A, B));
    }
}
