package lintcode.graphSearch;

import java.util.ArrayList;
/**
 * 错误的地方，忽略了题目要求的输出所有方案，导致开始设置信息的时候少了。
 * 信息的损失还是很重要的，什么时候用boolean，什么时候用int也要非常注意，
 * 一般来说如果不是很确定的话就用int，因为boolean是没有办法扩展的。
 * @author xiedandan
 *
 */
public class NQuees {
    class queenStatus {
        int n;
        boolean[] row;
        boolean[] col;
        boolean[] crossClick;
        boolean[] crossAntiClick;
        boolean[][] board;
        public queenStatus(int n) {
            this.n = n;
            this.row = new boolean[n];
            this.col = new boolean[n];
            this.crossClick = new boolean[2 * n - 1];
            this.crossAntiClick = new boolean[2 * n - 1];
            this.board = new boolean[n][n];
        }
        public void updateStatus(int rowIndex, int colIndex) {
            row[rowIndex] = true;
            col[colIndex] = true;
            crossClick[rowIndex - colIndex + n - 1] = true;
            crossAntiClick[rowIndex + colIndex] = true;
            board[rowIndex][colIndex] = true;
        }
        public void restoreStatus(int rowIndex, int colIndex){
            row[rowIndex] = false;
            col[colIndex] = false;
            crossClick[rowIndex - colIndex + n - 1] = false;
            crossAntiClick[rowIndex + colIndex] = false;
            board[rowIndex][colIndex] = false;
        }
        public boolean isValid(int rowIndex, int colIndex) {
            if (row[rowIndex] || col[colIndex] || 
                crossClick[rowIndex - colIndex + n - 1] || crossAntiClick[rowIndex + colIndex]) {
                return false;
            }
            return true;
        }
        public ArrayList<String> drawBoard() {
            ArrayList<String> picture = new ArrayList<String>();
            for (int i = 0; i < n; i++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == true) {
                        line.append("Q");
                    } else {
                        line.append(".");
                    }
                }
                picture.add(line.toString());
            }
            return picture;
        }
    }
    ArrayList<ArrayList<String>> solveNQueens(int n) {
        // 需要使用bitmap来记录行、列、斜边吗？—— 
        // 貌似好像没有用，判断的时候还是需要遍历一遍。
        // 暴力法——一个一个尝试
        // 能用动态规划吗？但是重复在哪里呢？
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (n <= 0) {
            return result;
        }

        solveNQueensHelper(n, result, new queenStatus(n), 0);
        return result;
    }
    private void solveNQueensHelper(int n, ArrayList<ArrayList<String>> result,
        queenStatus status, int level){
        // System.out.println("level:" + level);
        if (level == n) {
            result.add(status.drawBoard());
            return; // 忘记加了，大错！！
        }
        for (int i = 0; i < n; i++) {
            if (status.isValid(level, i)) {
                // System.out.println("col:" + i);
                status.updateStatus(level, i);
                solveNQueensHelper(n, result, status, level + 1);
                status.restoreStatus(level, i);
            }
        }
    }
}
