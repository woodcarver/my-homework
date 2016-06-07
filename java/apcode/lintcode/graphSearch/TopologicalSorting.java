package lintcode.graphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.DirectedGraphNode;
import algorithms.DirectedGraph;

public class TopologicalSorting {
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // 1. 借助MST的思想，使用一个优先队列来存储node：indegree， 
    	//    每次取最小的node（即indegree=0，如果最小的node的indegree>0, 那么就是检测到环了），
    	//    Time complexity: O(VlogV +  E)
        // 2. 使用一个 zeroQueue只存储zero indegree node。
    	//    一个marked来标记那些node被访问过（貌似可以不用），一个indegree map记录并更新indegree。
    	//    Time complexity: O(V + E)。果断这个更优！
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        if (graph == null) {
            return result;
        }
        HashMap<DirectedGraphNode, Integer> indegreeMap = new HashMap<DirectedGraphNode, Integer>();
        for (DirectedGraphNode node : graph) {
            indegreeMap.put(node, 0);
        }
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
            }
        }
        
        Queue<DirectedGraphNode> zeroQueue = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (indegreeMap.get(node) == 0) {
                zeroQueue.offer(node);
            }
        }
        
        while (!zeroQueue.isEmpty()) {
            DirectedGraphNode current = zeroQueue.poll();
            result.add(current);
            //更新领节点的indegree值
            for (DirectedGraphNode neighbor : current.neighbors) {
                if (indegreeMap.get(neighbor) -1 == 0) {
                    zeroQueue.offer(neighbor);
                    continue;
                }
                indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1);
            }
        }
        return result;
    }
    public static void main(String[] args) {
    	//{0,1,2,3,4#1,3,4#2,1,4#3,4#4}
    	int[][] arrays = {
    			{0,1,2,3,4},
    			{1,3,4},
    			{2,1,4},
    			{3,4},
    			{4}
    	};
    	ArrayList<DirectedGraphNode> graph = DirectedGraph.parse(arrays);
    	TopologicalSorting topsorting = new TopologicalSorting();
    	for (DirectedGraphNode node : topsorting.topSort(graph)) {
    		System.out.print(node.label + ",");
    	}
    }
}
