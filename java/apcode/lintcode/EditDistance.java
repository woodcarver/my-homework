package lintcode;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        // 这个树该怎么画？重复计算发生在哪里？
        // 这不是坐标型动态规划，是区间型的
        // state: Edit(x,y)表示从word1 从x~m 子串 和word2 从y~n子串 的最小Edit distance
        // function: Edit(x,y) = if (A[x] != B[y]) {
        //              min{Edit(x,y+1)--add,
        //              Edit(x+1,y+1)--replace,
        //              Edit(x+1,y)--delete} + 1
        //          }
        //              if (A[x]==B[y]) {Edit(x+1,y+1)--直接前进
        //          }
        //      }
        // initialize: Edit(null,y) = word2.length(), Edit(x,null) = word1.length()
        //      -- 貌似不用
        // answer: Edit(0,0)
        // 非递归版本太难想了，先做递归版本
        
        if (word1 == null && word2 == null) {
            return 0;
        }
        if (word1 == null) {
            return word2.length();
        }
        if (word2 == null) {
            return word1.length();
        }
        int m = word1.length();
        int n = word2.length();
        int[][] edit = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                edit[i][j] = -1;
            }
        }
        return minDistanceHelper(edit, word1, word2, 0, 0);
    }
    private int minDistanceHelper(int[][] edit, String word1, String word2 ,int pos1 ,int pos2) {
        if (pos1 == word1.length()) {
            return word2.length() - pos2;
        }
        if (pos2 == word2.length()) {
            return word1.length() - pos1;
        }
        if (edit[pos1][pos2] == -1) {
            if (word1.charAt(pos1) != word2.charAt(pos2)) {
                int insert = minDistanceHelper(edit, word1, word2, pos1, pos2 + 1);
                int delete = minDistanceHelper(edit, word1, word2, pos1 + 1, pos2);
                int replace = minDistanceHelper(edit, word1, word2, pos1 + 1, pos2 + 1);
            
                edit[pos1][pos2] = Math.min(insert,delete);
                edit[pos1][pos2] = Math.min(edit[pos1][pos2],replace);
                edit[pos1][pos2] += 1;
            } else {
                edit[pos1][pos2] = minDistanceHelper(edit, word1, word2, pos1 + 1, pos2 + 1);
            }
        }
        return edit[pos1][pos2];
    }
}
