package lintcode;

public class DistinctSubsequences {
    public int numDistinct(String S, String T) {
        // brute force: generating all the subsequences of S, and finding those which equal to T. O(2^n)
        // double sequences
        // first brute force opt, 裁剪一下subset树
    	// 一开始还真的找不到哪里重复了，其实写裁剪树已经耗了我很大的力气了，足足有一小时
    	// 在递归上做动规还可以，但是要真的转化成非递归有点难度啊~~
        if (S == null || T == null) {
            return 0; //maybe not
        }
        // ArrayList<Char> subSequence = new ArrayList<Char>();//subset没有必要被记录下来，在遍历的过程中比较完，就直接扔了
        return numDistinctHelper(S, T, 0, 0);
    }
    private int numDistinctHelper(String S, String T, int posS , int posT) {
        if (posT == T.length()) {
            return 1;
        }
        if (posS == S.length() || (S.length() - posS) < (T.length() - posT)) {
            return 0;
        }
        int m = S.length();
        // int n = T.length() - posT;
        int result = 0;
        for (int i = posS; i < m; i++) {
            if (S.charAt(i) == T.charAt(posT)) {
                result += numDistinctHelper(S, T, i + 1, posT + 1);
            }
        }
        return result;
    }
    // wrong case: "ddd", "dd" -- expect 3, but 2 初始化不正确导致的，少了对S.length() + 1和T.length() + 1 的初始化
    public int numDistinct2(String S, String T) {
        // brute force: generating all the subsequences of S, and finding those which equal to T. O(2^n)
        // double sequences
        // first brute force opt, 裁剪一下subset树
        if (S == null || T == null) {
            return 0; //maybe not
        }
        // ArrayList<Char> subSequence = new ArrayList<Char>(); // subset没有必要被记录下来，在遍历的过程中比较完，就直接扔了
        // 接下来开始动规
        // state: f(x,y)表示从x~s.len和y~t.len两个串的distinct subsequence数量
        // function: f(x,y) = sum{1<=j<s.len && S[x]==T[y] &&f(x+j,y+1)}
        // initailize: f(x,y) = -1;
        // answer: f(0,0)
        // int[][] distNum = new int[S.length()][T.length()]; //果然是要多分配一位，令狐老师太牛了！
        // 果然一定要注意初始化，漏掉一个都会造成结果不正确
        int[][] distNum = new int[S.length() + 1][T.length() + 1];
        for (int i = 0, SLen = S.length(); i < SLen + 1; i++) {
            for (int j = 0, TLen = T.length(); j < TLen + 1; j++) {
                distNum[i][j] = -1;
            }
        }

        return numDistinctHelper(S, T, distNum, 0, 0);
    }
    private int numDistinctHelper(String S, String T, int[][] distNum, int posS , int posT) {
        if (posT == T.length()) {
            // distNum[posS - 1 ][posT - 1] = 1;
            return 1;
        }
        if (posS == S.length() || (S.length() - posS) < (T.length() - posT)) {
            // distNum[posS][posT] = 0;
            return 0;
        }
        if (distNum[posS][posT] != -1) {
            return distNum[posS][posT];
        }
        
        distNum[posS][posT] = 0;
        for (int i = posS, SLen = S.length(); i < SLen; i++) {
            if (S.charAt(i) != T.charAt(posT)) {
                continue;
            }
            // System.out.println(posS + "," + i + "," + posT + "--" + distNum[i + 1][posT + 1]);
            if (i + 1 == SLen || distNum[i + 1][posT + 1] == -1) {
                distNum[i + 1][posT + 1] = numDistinctHelper(S, T, distNum, i + 1, posT + 1);
            }
            // System.out.println(posS + "," + i + "," + posT + "--" + distNum[i + 1][posT + 1]);
            distNum[posS][posT] += distNum[i + 1][posT + 1];
            // System.out.println(posS + "," + i + "," + posT + "-----" + distNum[posS][posT]);
        }
        return distNum[posS][posT];
    }
}
