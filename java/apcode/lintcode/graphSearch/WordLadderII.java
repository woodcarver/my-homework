package lintcode.graphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* error case:
 * "hot", "dog", ["hot","cog","dog","tot","hog","hop","pot","dot"]
 * excepted -- [["hot","dot","dog"],["hot","hog","dog"]]
 * but -- [["hot","dot","dog"],["hot","dot","hog","dog"]]
 *
 */
public class WordLadderII {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // 乍一看，编辑的方法有无数种啊
        // 第一步：构建图 -- bfs
        // 第二步：搜索所有最短路径 -- dfs
        List<List<String>> shortestPaths = new ArrayList<List<String>>();
        if (start == null || end == null || dict == null) {
            return shortestPaths;
        }
        HashMap<String, ArrayList<String>> edges = 
            new HashMap<String, ArrayList<String>>();
        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        dict.add(start);
        dict.add(end);
        
        bfs(edges, distance, start, end, dict);
        dfs(edges, distance, start, end, shortestPaths, new ArrayList<String>());
        return shortestPaths;
    }
    private void bfs(HashMap<String, ArrayList<String>> edges,
        HashMap<String, Integer> distance, String start, String end,
        Set<String> dict) {
        // if (edges == null || distances == null || start == null || end == null || dict == null) {
        //     return;
        // }
        for (String word : dict) {
            edges.put(word, new ArrayList<String>());
        }
        Queue<String> queue = new LinkedList<String>();
        queue.add(start);
        distance.put(start, 0);
        
        while (!queue.isEmpty()) {
            String current = queue.poll();
            List<String> neighbors = getNeighbors(current, dict);
            // edges.get(current).addAll(neighbors); //浪费循环
            for (String neighbor : neighbors) {
                edges.get(current).add(neighbor);
                if (!distance.containsKey(neighbor)) {
                    // edges.get(current).add(neighbor); // 会遍历不完所有情况，why？
                    queue.offer(neighbor);
                    distance.put(neighbor, distance.get(current) + 1);
                }
                // System.out.println("##current:" + current + " ,neighbor:" + neighbor + ",level:" + distance.get(neighbor));
            }
        }
    }
    private List<String> getNeighbors(String current, Set<String> dict) {
        List<String> neighbors = new ArrayList<String>();
        if (current == null || dict == null) {
            return neighbors;
        }
        for (int i = 0, len = current.length(); i < len; i++) {
            for (char ch = 'a'; ch < 'z'; ch++) {
                if (ch != current.charAt(i)) {
                    String newString = current.substring(0, i) + ch + current.substring(i + 1, len);
                    if (dict.contains(newString)) {
                        neighbors.add(newString);
                    }
                }
            }
        }
        return neighbors;
    }
    private void dfs(HashMap<String, ArrayList<String>> edges,
        HashMap<String, Integer> distance, String current, String end,
        List<List<String>> shortestPaths, List<String> path) {
        // if (edges == null || distance == null || start == null || end == null || dict == null || shortestPaths == null || path == null) {
        //     return;
        // }
        path.add(current);
        if (current.equals(end)) {
            //  System.out.println("over");
            shortestPaths.add(new ArrayList<String>(path));
            // return;//错误居然是发生在这里，当这里过早return后，path最后的元素删除不了了
        } else {
            for (String neighbor : edges.get(current)) {
                // System.out.println("##current:" + current + ",level:" + distance.get(current) + " ,neighbor:" + neighbor + ",level:" + distance.get(neighbor));
                if (distance.get(neighbor) == distance.get(current) + 1) {
                    dfs(edges, distance, neighbor, end, shortestPaths, path);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}
