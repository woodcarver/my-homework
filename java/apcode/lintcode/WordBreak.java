package lintcode;

import java.util.HashSet;
import java.util.Set;
/**
 * 暴力法的结果就是栈太深，导致StackOverflow了。第一得到这个错误，很有成就感啊
 * Exception in thread "main" java.lang.StackOverflowError at 
 * 接下来就是优化的动态规划方法了
 */
public class WordBreak {
    private int getMaxWordLen(Set<String> dict) {
        if (dict == null) {
            return 0;
        }
        int maxLen = 0;
        for (String word : dict) {
            maxLen = Math.max(maxLen, word.length());
        }
        return maxLen;
    }
    public boolean wordBreak(String s, Set<String> dict) {
        // 暴力方法dfs：每次都从dict中选取一个可以对比的word，
        //      - 如果能对比完则接着进行同样剩下的词
        //      - 如果不能，扔掉这个词，选取后面一个词从对比的起始点重新操作
        // 因为string能走到x点位置的时候有多种情况，而后面x~n是否能break是固定的，所以每次都计算一遍就发生了重复了。
        // 动态规划方法：
        // state: 有待纠结是一维还是二维呢? 
        //        f(x) 代表0~x能否break
        // function: f(x) = 
        if (s == null || dict == null) {
            return false;
        }
        int maxWordLen = getMaxWordLen(dict);
//        return wordBreakHelper(s, dict, 0);
        return wordBreakHelper2(s, dict, maxWordLen, 0);
    }
    private boolean wordBreakHelper(String s, Set<String> dict, int posS) {
        if (posS == s.length()) {
            return true;
        }
        
        for (String word : dict) {
            int wordLen = word.length();
            if (wordLen > s.length() - posS) {
                continue;
            }
            System.out.println("substring:" +s.substring(posS, posS + wordLen) + "," + "word:" + word);
            if (word.equals(s.substring(posS, posS + wordLen))) {//注意substring的end是不包含在内的
                if (wordBreakHelper(s, dict, posS + wordLen)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean wordBreakHelper2(String s, Set<String> dict, int maxWordLen, int posS) {
        if (posS == s.length()) {
            return true;
        }
        
        for (int i = 1; i < maxWordLen; i++) {
            System.out.println("substring:" +s.substring(posS, posS + i));
            if (dict.contains(s.substring(posS, posS + i))) {//注意substring的end是不包含在内的
                if (wordBreakHelper(s, dict, posS + i + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    //仍然会StackOverFlow
    public boolean wordBreakDP(String s, Set<String> dict) {
        // 因为string能走到x点位置的时候有多种情况，而后面x~n是否能break是固定的，所以每次都计算一遍就发生了重复了。
        // 动态规划方法：
        // state: 有待纠结是一维还是二维呢? 
        //        f(x) 代表0~x能否break
        // function: f(x) = 
        if (s == null || dict == null) {
            return false;
        }
        int[] dp = new int[s.length() + 1];
        return wordBreakHelper(s, dict, dp, 0);
    }
    private boolean wordBreakHelper(String s, Set<String> dict, int[] dp, int posS) {
        if (posS == s.length()) {
            return true;
        }
        
        if (dp[posS] != 0) {
        	return dp[posS] == 1;
        }
        
        dp[posS] = -1;
        for (String word : dict) {
            int wordLen = word.length();
            if (wordLen > s.length() - posS) {
                continue;
            }
//            System.out.println("substring:" +s.substring(posS, posS + wordLen) + "," + "word:" + word);
            if (word.equals(s.substring(posS, posS + wordLen))) {//注意substring的end是不包含在内的
                if (wordBreakHelper(s, dict, dp, posS + wordLen)) {
                	dp[posS] = 1;
                    return true;
                }
            }
        }
        return false;
    }
    public boolean wordBreak3(String s, Set<String> dict) {
        // 暴力方法dfs：每次都从dict中选取一个可以对比的word，
        //      - 如果能对比完则接着进行同样剩下的词
        //      - 如果不能，扔掉这个词，选取后面一个词从对比的起始点重新操作
        // 因为string能走到x点位置的时候有多种情况，而后面x~n是否能break是固定的，所以每次都计算一遍就发生了重复了。
        // 动态规划方法：
        // state: 有待纠结是一维还是二维呢? 
        //        f(x) 代表0~x区间能否break
        // function: f(x) = OR{f[j] && j+1~i is a word}, 其中 j < i
        // initialize: f[0] = true
        // answer: f[n]
        // 为啥给Set，就是为了使用contains这种hash映射
        
        if (s == null || dict == null) {
            return false;
        }
        int n = s.length();
        int maxWordLen = getMaxWordLen(dict);
        boolean[] canSegment = new boolean[n + 1];
        canSegment[0] = true;
        
        for (int i = 1; i <= n; i++) {// 仔细体会下区间型的state
            canSegment[i] = false;
            for (int j = 1; j <= maxWordLen && j <= i; j++) {//为什么都是<=？？
                if (!canSegment[i - j]) {
                        continue;
                }
                // System.out.println(s.substring(i - j, i));
                if (dict.contains(s.substring(i - j, i))) {
                    //注意substring的end是不包含在内的
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[n];
    }
    public static void main(String[] args) {
    	Set<String> dict = new HashSet<String>();
//    	dict.add("aa");
    	dict.add("a");
    	dict.add("ac");
//    	dict.add("c");
    	dict.add("b");
//    	dict.add("bbbb");
//    	dict.add("b");
    	String s = "aacb";
    	WordBreak wc = new WordBreak();
    	System.out.println("result: " + wc.wordBreak(s, dict));
//    	System.out.println("result: " + wc.wordBreakDP(s, dict));
    }
}
