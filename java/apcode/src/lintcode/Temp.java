package lintcode;
/**
 * 这道题的基本思路和最大连续子区间如出一辙
 * @author xiedandan
 * # 解题报告
 * ## 思路——一句概括这个题让你干什么？
 * ## 有哪些我做过的相关题目
 * ## 有哪些条件提示我想到解法
 * ## 模板
 */
public class Temp {
    public static void main(String[] args) {
    	FindPeak fp = new FindPeak();
    	int[][] arrays = {
    			null,
    			{},
    			{1, 2, 1},
    			{1, 2, 6, 3, 4, 5, 7, 6}
    	};
    	for(int[] array:arrays){
    		System.out.println("result:" + fp.findPeak2(array));
    	}
    }
}
