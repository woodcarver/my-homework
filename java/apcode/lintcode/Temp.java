package lintcode;

import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.util.Map.Entry;
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
    		System.out.println("result:" + fp.findPeak2(array));
    	}
    	TreeSet<Integer> t;
    	Math.abs(1);
        List<Integer> i;
        Integer s=null;
        Stack<Integer> sss = new Stack<Integer>();

//        Queue<Integer> queue = new LinkedList<Integer>();
        Stack<Integer> stack = new Stack<Integer>();
        Object ob = new Object();
        int[][] path = new int[1][];
        HashMap<Integer, String> hash = new HashMap<Integer, String>();
        hash.put(1, "value");
        String word2 = "sssss";
        word2.substring(0, 1);
        word2.equals("sss");
        StringBuilder builder = new StringBuilder();
//        builder.deleteCharAt(0);
        Process p;
//        Iterator i = ;
        Iterable j = new ArrayList();
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(1);
        
//        queue.offer(1);
//        queue.offer(10);
//        queue.offer(7);
//        queue.offer(3);
//        for (Integer item : queue) {
//        	queue.isEmpty();
//        	System.out.println(item + ",");
//        }
        
//        TreeMap<Integer, Integer> tm = new TreeMap<Integer, Integer>();
//        tm.put(3, 1);
//        tm.put(1, 1);
//        tm.put(2, 3);
//        Entry<Integer, Integer> prev = null;
//        for (Entry<Integer, Integer> item : tm.entrySet()) {
//        	System.out.println(item.getKey() + "," + item.getValue());
//        }
        IdentityHashMap<Integer, Integer> iden = new IdentityHashMap<Integer, Integer>();
//        iden.put(new Integer(1), 1);
//        iden.put(new Integer(1), 2);
        iden.put(1, 1);
        iden.put(1, 2);
	    for (Entry<Integer, Integer> item : iden.entrySet()) {
	    	System.out.println(item.getKey() + "," + item.getValue());
	    }
        int[] globalSum = {1, 1, 1};
        System.out.println(globalSum[2]);
        
        PriorityQueue<java.util.Map.Entry<Integer, Integer>> priQueue = 
        	new PriorityQueue<java.util.Map.Entry<Integer, Integer>>();
        ArrayList ll;
    }
}
