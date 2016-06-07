package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RateLimiter1 {
    // 先考虑存储
    // event, expire time,counter
    private HashMap<String, ArrayList<Integer>> eventLog
        = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, Integer> timeUnitConv = new HashMap<String, Integer>();
    public RateLimiter1() {
        timeUnitConv.put("s", 1);
        timeUnitConv.put("m", 60);
        timeUnitConv.put("h", 3600);
        timeUnitConv.put("d", 86400);
    }
    /**
     * @param timestamp the current timestamp
     * @param event the string to distinct different event
     * @param rate the format is [integer]/[s/m/h/d]
     * @param increment whether we should increase the counter
     * @return true or false to indicate the event is limited or not
     */
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        if (timestamp < 0 || event == null || rate == null) {
            return true;
        }
        
        boolean ret = false;
        if (increment == true &&
            eventLog.containsKey(event) &&
            timestamp < eventLog.get(event).get(0) &&
            eventLog.get(event).get(1) <= 0) {
            ret = true;
        }

        Pattern pattern = Pattern.compile("(\\d+)\\/([smhd]{1})");
        Matcher matcher = pattern.matcher(rate);
    
        int newExpireTime = 0;
        int newCounter = 0;
        if(matcher.matches()){
            newExpireTime = Integer.parseInt(matcher.group(1)) + timestamp;
            newCounter = timeUnitConv.get(matcher.group(2));
        } else {
            return true;
        }
        System.out.println(event + "," + newExpireTime + "," + newCounter);
        ArrayList<Integer> counter = new ArrayList<Integer>(2);
        counter.set(0, newExpireTime);
        counter.set(1, newCounter);
        eventLog.put(event, counter);
        
        return ret;
    }
}

public class RateLimiter {
    // 先考虑存储
    // 感觉和weblogger有相似处，只是多了一个event的key值
    // event, timestamp
    private HashMap<String, ArrayList<Integer>> eventLog
        = new HashMap<String, ArrayList<Integer>>();
    private HashMap<String, Integer> timeUnitConv = new HashMap<String, Integer>();
    private int lastRate = 0;
    public RateLimiter() {
        timeUnitConv.put("s", 1);
        timeUnitConv.put("m", 60);
        timeUnitConv.put("h", 3600);
        timeUnitConv.put("d", 86400);
    }
    /**
     * @param timestamp the current timestamp
     * @param event the string to distinct different event
     * @param rate the format is [integer]/[s/m/h/d]
     * @param increment whether we should increase the counter
     * @return true or false to indicate the event is limited or not
     */
    public boolean isRatelimited(int timestamp, String event, String rate, boolean increment) {
        if (timestamp < 0 || event == null || rate == null) {
            return true;
        }

        Pattern pattern = Pattern.compile("(\\d+)\\/([smhd]{1})");
        Matcher matcher = pattern.matcher(rate);
    
        int counter = 0;
        int interval = 0;
        if(matcher.matches()){
            counter = Integer.parseInt(matcher.group(1)) - 1; // 设置时候本身就算一次
            interval = timeUnitConv.get(matcher.group(2));
        }
        // System.out.println("$$" + counter + "," + interval);
        int startTime = (timestamp > interval) ? (timestamp - interval + 1) : 0;
        // int endTime = timestamp;
        
        if (!eventLog.containsKey(event)) {
            eventLog.put(event, new ArrayList<Integer>());
            // if (increment) {
            //     eventLog.get(event).add(timestamp);
            // }
            // return false;
        }
        
        boolean ret = false;
        if (findEventCount(event, startTime) > counter) {
            ret = true;
        }
        if (increment && !ret) {
            eventLog.get(event).add(timestamp);
        }
        return ret;
    }
    private int findEventCount(String event, int startTime) {
        // 这个其实是在模拟数据库
        ArrayList<Integer> logs = eventLog.get(event);
        if(logs == null || logs.size() == 0) {
            return 0;
        }
        // System.out.println(logs);
        //smallest number which is bigger than or equal to startTime
        int startPos = floorPos(logs, startTime);
        // int endPos = floorPos(logs, endTime);
        return logs.size() - 1 - startPos + 1;
    }
    private int floorPos(ArrayList<Integer> logs, int timestamp) {
        // 相当于二分法中的寻找最左边的一个大于或者等于timestamp的数
        if(logs == null || logs.size() == 0) {
            return 0;
        }
        int left = 0;
        int right = logs.size() - 1;
        // 结束条件：left + 1 == right, 那么意味有两个数留下没有被验证
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            // if (timestamp == logs.get(mid)) {
            //     return mid;
            // }
            // left subarray first
            if (timestamp <= logs.get(mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // System.out.println("###" + timestamp + "," + left + "," +right);
        if (logs.get(left) >= timestamp) {
            return left;
        }
        if (logs.get(right) >= timestamp) {
            return right;
        }
        return right + 1;
    }
}
