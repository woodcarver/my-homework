package lintcode.systemDesign;

import java.util.Map;
import java.util.TreeMap;

public class WebLogger {
    /*
     *
get_hit_count_in_last_5_minutes(10)
hit(10)
hit(10)
hit(10)
get_hit_count_in_last_5_minutes(10)
get_hit_count_in_last_5_minutes(10)
hit(10)
hit(10)
hit(10)
hit(10)
get_hit_count_in_last_5_minutes(10)
get_hit_count_in_last_5_minutes(11)
hit(13)
hit(13)
hit(13)
hit(16)
hit(16)
hit(19)
get_hit_count_in_last_5_minutes(20)
get_hit_count_in_last_5_minutes(20)
对验证null还是没有做足功夫
     */
    private TreeMap<Integer, Integer> hitMap = new TreeMap<Integer, Integer>();
    public WebLogger() {
        // 简单的一个arraylist？储存以这个点往前推移5min的总点击数
        // hit是稀疏的，还是稠密的？稀疏的用hashmap也许更好
        // 第一，慢，每次都需要for一遍，计数
        // 第二，容量可能会不够供，随着时间的推移
    	// 第三，counter一个int会不会不够用？
    }

    /**
     * @param timestamp an integer
     * @return void
     */
    public void hit(int timestamp) {
        Map.Entry<Integer, Integer> lastEntry= hitMap.floorEntry(timestamp);
        int lastCount = 0;
        if (lastEntry != null) {
            lastCount = lastEntry.getValue();
        }
        // 考虑并发吗？TreeMap应该是线程不安全的
        hitMap.put(timestamp, lastCount + 1);
        // printMap(hitMap);
    }

    /**
     * @param timestamp an integer
     * @return an integer
     */
    public int get_hit_count_in_last_5_minutes(int timestamp) {
        int startTime = timestamp - 5 * 60;
        int endTime = timestamp;
        Map.Entry<Integer, Integer> startCount = hitMap.floorEntry(startTime);
        Map.Entry<Integer, Integer>  endCount = hitMap.floorEntry(endTime);
        
        if (startCount == null && endCount != null) {
            return endCount.getValue();
        } else if (startCount != null && endCount == null) {
            return startCount.getValue();
        } else if (startCount == null && endCount == null) {
            return 0;
        } else {
            return endCount.getValue() - startCount.getValue();
        }
    }
    private void printMap(TreeMap<Integer, Integer> map) {
        if (map == null) {
            return;
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print("(" + entry.getKey() + "," + entry.getValue() + "),");
        }
        System.out.println("over");
    }
}
