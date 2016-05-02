package lintcode.dataStructure;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    public String[] topKFrequentWords(String[] words, int k) {
        // 思路： 1. 用hashtable记数，但是要找到TopK，还需要一遍遍历后，再排序。
        //           Time complexity：O(n+n+nlogk)
        //        2. 直接使用堆，堆最适合这种Top k的场景了，但是堆不好查找
        //        3. 结合hashTable和heap, O(n+nlogk)？？
        if (words == null || words.length == 0 || k < 1) {
            return new String[0];
        }
        // if (words.length < k) {
        //     return null;
        // }
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        for (int i = 0, len = words.length; i < len; i++){
            if (counter.containsKey(words[i])) {
                // System.out.println("update:" + words[i] + "," + counter.get(words[i]));
                counter.put(words[i], counter.get(words[i]) + 1); // 笔误
            } else {
                // System.out.println("add:" + words[i]);
                counter.put(words[i], 1);
            }
        }
        
        PriorityQueue<java.util.Map.Entry<String, Integer>> priQueue = new PriorityQueue<java.util.Map.Entry<String, Integer>>
        	(counter.size(), 
            new Comparator<java.util.Map.Entry<String, Integer>>(){
                public int compare(java.util.Map.Entry<String, Integer> a,
                    java.util.Map.Entry<String, Integer> b) {
                        if (a == null) {
                            return -1;
                        }
                        if (b == null) {
                            return 1;
                        }
                        if (a.getValue() == b.getValue()) {
                            return a.getKey().compareTo(b.getKey()); //逻辑错误
                        } else {
                            return b.getValue() - a.getValue();
                        }
                }
        });
        
        for (java.util.Map.Entry<String, Integer> item : counter.entrySet()) {
            if (item.getKey() == null) {
                continue;
            }
            // System.out.println(item.getKey() + "," + item.getValue());
            priQueue.offer(item);
        }
        
        String[] result = new String[k];
        int pos = 0;
        while (pos < k && !priQueue.isEmpty()) {// 边界检查错误
            result[pos++] = priQueue.poll().getKey();
        }
        // 这个边界情况没有考虑到
        while (pos < k) {
            result[pos++] = null;
        }
        return result;
    }
}
