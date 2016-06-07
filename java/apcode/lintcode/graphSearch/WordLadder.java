package lintcode.graphSearch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * test cases:
 * 1. "a", "c", ["a","b","c"]
 * 2. "hit", "cog", ["hot","dot","dog","lot","log","hog"] -- 4
 * 这个是隐式图，使用bfs，为什么？？？
 */

public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        // 思路：类似于层序遍历，实际使用到bfs
        if (start == null || end == null || dict == null) {
            return 0;
        }

        // for marking visited and the level
        HashMap<String, Integer> visited = new HashMap<String, Integer>();
        Queue<String> queue = new LinkedList<String>();
        queue.offer(start);
        visited.put(start, 1);
//        int level = 2;
        int shortest = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            int level = visited.get(curr) + 1;
            // 原来end和start可以不在set中，也可以在，所这里的代码不能
            // 少,当然循环内的条件可以减去了
            if (isNextWord(curr, end)) {
                shortest = Math.min(shortest, level);
                continue;
            }
            for (String word : dict) {
                // 不用回退，也防止打圈
                // if (visited.containsKey(word) && visited.get(word) < level) {
                if (visited.containsKey(word)) {
                    continue;
                }
                // if (word.equals(end)) {
                //     // System.out.println("find end:" + level);
                //     shortest = Math.min(shortest, level);
                //     continue;
                // }
                if (isNextWord(curr, word)) {
                    // System.out.println("curr:" + curr + ",word:" + word);
                    queue.offer(word);
                    visited.put(word, level);
                }
            }
        }
        return shortest == Integer.MAX_VALUE ? 0 : shortest;
    }
    private boolean isNextWord(String word, String item) {
        if (word == null || item == null) {
            return false;
        }
        int distance = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != item.charAt(i)) {
                distance++;
            }
        }
        return distance == 1 ? true : false;
    }
}
