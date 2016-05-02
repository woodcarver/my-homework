package lintcode;
/**
 * 这道题不就是经典的一个排列组合模型吗？罐子模型
 * 数据公式：
 * @author xiedandan
 * uniquePathsWithObstacles 错误case：
 * 1. [[0]] --》 各种越界问题
 * 2. [[0,0],[0,0],[0,0],[1,0],[0,0]] 3，但是我的结果是4 --
 * 	原因是我没有保证整条路都走完，m == 1 || n == 1 这个条件已经不能像uniquePaths这样判断，因为剩下的步数可能是有障碍物阻挡走不完，而且没有办法饶了。
 * 3. [[0,0,0,0,0,0,1,0,0,0],[1,0,0,0,0,0,0,0,0,1]]
 * 	看到这个case，我也是醉了，最然目的地给设置了障碍
 */

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        //数学公式：
        if (m  == 0 && n == 0) {
            return 0;
        }
        int[][] path = new int[m + 1][n + 1];
        return uniquePathsHelper(path, m, n);
    }
    private int uniquePathsHelper(int[][] path, int m, int n) {
//        if (m == 0 || n == 0) { //表示的是行或者列的数量，所以到1的时候已经没有选择了， 注意到底是沿着边走，还是跳入框中走
    	if (m == 1 || n == 1) {
            return 1;
        }
        if (path[m][n] == 0) {
            path[m][n] = uniquePathsHelper(path, m - 1, n) + uniquePathsHelper(path, m, n - 1);
        }
        return path[m][n];
    }
    // 这个答案超时
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];
        return uniquePathsWithObstaclesHelper(obstacleGrid, path, m - 1, n - 1);//还是要考虑到数据越界的问题
//        return path[m][n]; //坑是如此之多，应该是 m - 1, n - 1
    }
    private int uniquePathsWithObstaclesHelper(int[][] obstacleGri,
        int[][] path, int m, int n) {
//        if (m == 0 || n == 0) { // 到底是0 还是 1？Why？,看错误case2
        if (m == 0 && n == 0) { 
            path[0][0] = 1; // 这个赋值在这里可有可无，但是注意如果不赋值，path其实是不完整的，很容易留坑
            return 1;
        }
        if (obstacleGri[m][n] == 1) { //见case3
            return 0;
        }
        if (path[m][n] == 0) {
            int right = 0;
            int down = 0;
//            if (obstacleGri[m][n - 1] != 1) {
            if (n > 0 && obstacleGri[m][n - 1] != 1) {
            	right = uniquePathsWithObstaclesHelper(obstacleGri, path, m, n - 1);
            }
//            if (obstacleGri[m - 1][n] != 1) {
            if (m > 0 && obstacleGri[m - 1][n] != 1) {
            	down = uniquePathsWithObstaclesHelper(obstacleGri, path, m - 1, n);
            }
            path[m][n] = right + down;
        }
        return path[m][n];
    }
    /*
     * 这个题的教训就是初始化的时候一定要多考虑，一部小心就掉入坑里了，特别是case 2
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] path = new int[m][n];
        // initail path
        // for (int i = 0; i < m; i++) {
        //     if (obstacleGrid[i][0] != 1) {
        //         path[i][0] = 1;
        //     }
        // }
        // for (int j = 0; j < n; j++) {
        //     if (obstacleGrid[0][j] != 1) {
        //         path[0][j] = 1;
        //     }
        // }
        // 假如起点位置就是bar，那么直接没法走下去了，直接返回
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        path[0][0] = 1;
        // find the answer
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    path[i][j] = 0;
                    continue;
                }
                // j > 0, i > 0条件防止越界，但同时让这个遍历访问不到了path[0][0]
                if (j > 0 && obstacleGrid[i][j - 1] != 1) {
                    path[i][j] += path[i][j - 1];
                }
                if (i > 0 && obstacleGrid[i - 1][j] != 1) {
                    path[i][j] += path[i - 1][j];
                }
                // System.out.println(i + ","+ j + "--left:" + path[i][j - 1] + ",up:" + path[i - 1][j]);
            }
        }
        return path[m - 1][n - 1];
    }
}