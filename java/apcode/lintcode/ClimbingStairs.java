package lintcode;
/**
 * 这道题的感觉就是直接求解斐波那契数列
 * @author xiedandan
 *
 */
public class ClimbingStairs {
	// 非常差的实现方法
    public int climbStairs(int n) {
        //怎么感觉就是feibonacci sequence
        if (n <= 1) {
//        	return n;
            return 1; // 注意这道题的对于n==0的定义是1
        }
        int[] ways = new int[n + 1]; //left way[0] be empty
        ways[0] = 1;
        ways[1] = 1;
        for (int i = 2; i <= n; i++) {
            ways[i] = ways[i - 1] + ways[i - 2];
            // System.out.println(i + "," + ways[i]);
        }
        return ways[n];
    }
    
    public int climbStairs2(int n) {
        //怎么感觉就是feibonacci sequence
        if (n <= 1) {
            return 1;
        }
        int seq0 = 1;
        int seq1 = 1;
        int seqN = 0; //还必须先初始化才能用！，否则报错：
        //Solution.java:20: error: variable seqN might not have been initialized, 貌似是判断for循环可以进不去
        for (int i = 2; i <= n; i++) {
            seqN = seq1 + seq0;
            seq0 = seq1;
            seq1 = seqN;
            // System.out.println(i + "," + seqN);
        }
        return seqN;
    }
}
