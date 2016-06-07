package lintcode;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sun.nio.cs.Surrogate.Generator;
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
        
    	for(int[] array : arrays){
//    		System.out.println("result:" + fp.findPeak2(array));
    	}
    	System.out.println(91199999*99999999);
    	HashSet<Integer> visited = new HashSet<Integer>();
    	HashMap<String, List<Integer>> indexMap = new HashMap<String, List<Integer>>();
        for (Map.Entry<String, List<Integer>> entry : indexMap.entrySet()) {
            System.out.println(entry.getKey() + "," + entry.getValue());
        }
        
    	visited.add(1);
    	visited.remove(1);
    	for (int i : visited) {
    		System.out.println(i);
    	}
    	char[] arr = {'a','b'};
    	String s = new String(arr);
    	StringBuilder sb = new StringBuilder();
    	sb.append('a');
//    	throw new IllegalArgumentException();
//    	Iterator<Integer> values = visited;
//    	values.hasNext();
//    	values.next();
    	String str = "abcd";
    	str.split(" \\+");
    	byte by = 0xff >> 3;
    	System.out.println(by);
    	if (str.charAt(0) == 'a') {
    		System.out.println("equal:" + str.charAt(0));
    	}
    }
//	public class TrieNode {
//		 public NavigableMap<Character, TrieNode> children;
//		 public TrieNode() {
//		 children = new TreeMap<Character, TrieNode>();
//		 }
//	}
}
