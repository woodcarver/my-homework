package lintcode.graphSearch;

import algorithms.UndirectedGraphNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindTheConnectedComponentInTheUndirectedGraph {
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        // 1. undirected graph
        // 2. Connected Component
        // 使用bfs来做,一面dfs的过深而stackoverflow
        // 找下一个没有被访问的点该用什么数据结构？
        List<List<Integer>> components = new ArrayList<List<Integer>>();
        if (nodes == null || nodes.size() < 1) {
            return components;
        }
        Queue<UndirectedGraphNode> queue =
            new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, Boolean> visited =
            new HashMap<UndirectedGraphNode, Boolean>();
        for (UndirectedGraphNode node : nodes) {
            visited.put(node, false);
        }
        
        int counter = nodes.size(); // 结果还是不是 size - 1，以为需要最后一个判断把最后一个 component加入到components中
        queue.offer(nodes.get(0));
        visited.put(nodes.get(0), true);
        List<Integer> component = new ArrayList<Integer>();
        while (counter > 0) { // !!!!
            // System.out.println("counter:" + counter);
            if (queue.isEmpty()) {
                // System.out.println("queue is empty:" + component);
            	Collections.sort(component);
                components.add(component);
                component = new ArrayList<Integer>();
                for (UndirectedGraphNode node : visited.keySet()) {
                    if (visited.get(node)) {
                        continue;
                    }
                    queue.offer(node);
                    visited.put(node, true); // 这句遗漏了
                    break;
                }
            }
            UndirectedGraphNode current = queue.poll();
            // System.out.println("##current:" + current.label + "," + visited.get(current));
            component.add(current.label);
            for (UndirectedGraphNode neighbor : current.neighbors) {
                if (visited.get(neighbor)) {
                    continue;
                }
                queue.offer(neighbor);
                visited.put(neighbor, true);
            }
            counter--;
        }
        components.add(component);
        return components;
    }
}
