package lintcode;

public class longestIncreasingSubsequence {
    public int longestIncreasingSubsequenceF(int[] nums) {
        // 这道题可以用divide conquer & memories 方法吗？-- 可以的
        // 每当扫描到一个大于自己的数的时候，有两个决策，要还是不要。要的原因很简单会直接正长现有的子序列，不要的是因为这个数可能很大会阻碍后面更长可拼接的子序列
        // 重复出现在扫描到一个可选数的时候不能直接得到结果，而是从可选数还需要继续向后探测
        // 重复的探测
        // 这是个正三角形的递归树(准确的说应该是森林)，所以用自底向上
        // state: f(x) 表示x位置最长后续子序列
        // function: f(x) = (f(j) + 1 && j>x && A[j]>A[x]) or 1
        // Initialize: f(n - 1) = 1
        // answer: max{f(0) ~ f(n - 1)}
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[n - 1] = 1;
        // for (int i = 0; i < n; i++) {
            // dp[i] = nums[i];// 这里的初始化时错误的，没有弄清楚dp的含义
        // }
        
        for (int i = n - 2; i >= 0; i--) {
            int max = 0;
            for (int j = 1; i + j < n; j++) {
                if (nums[i + j] >= nums[i]) {
                    max = Math.max(max, dp[i + j]);
                }
            }
            dp[i] = max + 1;
        }
        
        int max = dp[0];
        for (int i = 1; i < n; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    public static void main(String[] args) {
    	longestIncreasingSubsequence instance = new longestIncreasingSubsequence();
    	int[][] arrays = {
    			null,
    			{},
    			{1, 2, 1},
    			{1, 2, 6, 3, 4, 5, 7, 6},
    			{5,1,6,2,7,3,4,5},
    			{7,6,3,2,1}
    	};
    	for(int[] array:arrays){
    		System.out.println("result:" + instance.longestIncreasingSubsequenceF(array));
    	}
    }
}
