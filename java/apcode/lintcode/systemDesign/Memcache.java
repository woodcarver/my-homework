package lintcode.systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Memcached 中的亮点：
 * 1. consistent hash
 * 2. LRU
 * @author xiedandan
 *
 */
public class Memcache {
    private HashMap<Integer, ArrayList<Integer>> data;
    public Memcache() {
        // Initialize your data structure here.
        // LRU ==> HashMap + LinkedList
        // 需要积累优雅的代码风格
        // 我现在写的都是单线程版本，要是多线程呢？
        // version1 --> HashMap only
        // version2 --> LRU
        // version3 --> LFU
        // version4 --> mutil-thread
        // map: key, [value,time,ttl]
        data = new HashMap<Integer, ArrayList<Integer>>();
    }

    public int get(int curtTime, int key) {
        expir(curtTime, key);
        if (data.containsKey(key)) {
            return data.get(key).get(0);
        }
        return 2147483647;
    }
    // how to expir key?
    public void set(int curtTime, int key, int value, int ttl) {
        // set 会更新key的时间戳吗？memcache是会的吧
        ArrayList<Integer> tuple = new ArrayList<Integer>();
        tuple.add(value);
        tuple.add(curtTime);
        tuple.add(ttl);
        data.put(key, tuple);
    }

    public void delete(int curtTime, int key) {
        // Write your code here
        if (!data.containsKey(key)) {
            return;
        }
        data.remove(key);
    }

    public int incr(int curtTime, int key, int delta) {
        // 不更新key的时间戳
        expir(curtTime, key);
        if (data.containsKey(key)) {
            ArrayList<Integer> tuple = data.get(key);
            int value = tuple.get(0) + delta;
            tuple.set(0, value);
            // tuple.set(1, curtTime);
            return value;
        }
        return 2147483647;
    }

    public int decr(int curtTime, int key, int delta) {
        // 不更新key的时间戳
        expir(curtTime, key);
        if (data.containsKey(key)) {
            ArrayList<Integer> tuple = data.get(key);
            int value = tuple.get(0) - delta;
            tuple.set(0, value);
            // tuple.set(1, curtTime);
            return value;
        }
        return 2147483647;
    }
    private void expir(int curtTime, int key) {
        if (!data.containsKey(key)) {
            return;
        }
        ArrayList<Integer> tuple = data.get(key);
        int setTime = tuple.get(1);
        int ttl = tuple.get(2);
        if (ttl == 0) {
            return;
        }
        if (setTime + ttl - 1 < curtTime) {
            delete(curtTime, key);
        }
    }
}
