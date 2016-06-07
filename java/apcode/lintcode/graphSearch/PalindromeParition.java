package lintcode.graphSearch;

import java.util.List;
import java.util.ArrayList;

/**
 * 最莫名其妙的一道题，我都不怎么明白，然后一个遍程序就过了，写的过程明明是抱着猜测的态度！！！
 * @author xiedandan
 * 递归图还是没有画出来
 */
public class PalindromeParition {
    public List<List<String>> partition(String s) {
        // 思路：实质上就是一个subset的问题，而subset的原数据集合是切刀的位置
        // aabbaa ==> a|a|b|b|a|a ===> 有5刀的位置种类有：[1,2,3,4,5],[1,5],[3]...
        List<List<String>> resultSets = new ArrayList<List<String>>();
        if (s == null || s.length() == 0) {
            return resultSets;
        }
        ArrayList<String> list = new ArrayList<String>();
        partitionHelper(s, resultSets, list, 0);
        return resultSets;
    }
    private void partitionHelper(String s, List<List<String>> resultSets,
        ArrayList<String> list, int pos) {
        if (pos == s.length()) {
            resultSets.add(new ArrayList<String>(list));
            return; //??遗留没问题，也能过
        }
        for (int i = pos, len = s.length(); i < len; i++) {
            String sub = s.substring(pos, i + 1);
            if (isPalindrome(sub)) {
                list.add(sub);
                partitionHelper(s, resultSets, list, i + 1); //递归一次相当于切一刀
                list.remove(list.size() - 1);
            }
        }
    }
    private boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return true; //???
        }
        for (int i = 0, len = str.length(); i < len / 2 ; i++) {
            if (str.charAt(i) != str.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
