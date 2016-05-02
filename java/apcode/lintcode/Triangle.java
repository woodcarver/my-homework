package lintcode;
/**
 * 
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]应该是下面这样子的，leetcode上的参数接口更合理些：List<List<Integer>> triangle
[
	[2],
	[3,4],
	[6,5,7],
	[4,1,8,3]
]
 * @author xiedandan
 * error case：
 * [[1],[2,3]] —— java.lang.ArrayIndexOutOfBoundsException ，path[i][j] = triangle[i][j] + Math.min(path[i - 1][j - 1], path[i - 1][j]);
 */
public class Triangle {
    public int minimumTotal(int[][] triangle) {
        if (triangle == null || triangle.length == 0 || triangle[0].length == 0) {
            return -1; //这个题目没有规定
        }
        // 这道题给的tranagle的是个方矩阵，还是三角形矩阵？是三角的
        // adjacent numbers 这个到底怎么定义？需要例子
        // 坐标(x,y) 的adjacent numbers 是(x+1,y),(x+1,y+1)
        // 自顶向下的话，需要反推，所以关系成为(x,y) 的来由是(x-1,y-1),(x-1,y)
        int m = triangle.length;
        int[][] path = new int[m][];
        path[0] = new int[1]; //大失误，居然没有分配变量
        path[0][0] = triangle[0][0];
        for (int i = 1; i < m; i++) {//every layer
            int n = triangle[i].length;
            if (n < i) {
                return -1; //第i层元素给的不够
            }
            path[i] = new int[n];
            // 单独拎出来第一个和最后一个，因为这两个元素只有一条路可走
            path[i][0] = triangle[i][0] + path[i - 1][0];
            path[i][n - 1] = triangle[i][n - 1] + path[i - 1][n - 2];
            for (int j = 1; j < n - 1; j++) {// every element
                // System.out.println("i,j:"+ i + "," + j + "--" + triangle[i][j]);
                // 还是越界，到第二层就越界了，是最后一个元素的问题
                // 因为当 j = lary[i].length的时候，第 i-1层是没有第lary[i].length 这个元素的。
                path[i][j] = triangle[i][j]
                    + Math.min(path[i - 1][j - 1], path[i - 1][j]);
            }
        }
        
        int minSum = path[m - 1][0];
        for (int j = 1, len = path[m - 1].length; j < len; j++) {
            minSum = Math.min(minSum, path[m - 1][j]);
        }
        return minSum;
    }
    public static void main(String[] args) {
    	Triangle instance = new Triangle();
    	int[][] arrays = {
    			null,
    			{},
    			{1, 2, 1},
    			{1, 2, 6, 3, 4, 5, 7, 6}
    	};
    	System.out.println("result:" + instance.minimumTotal(arrays));
    }
}
