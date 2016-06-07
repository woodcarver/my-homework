package lintcode.graphSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import algorithms.UndirectedGraphNode;

public class CloneGraph {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // deep copy
        if (node == null) {
            return null;
        }
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);
        UndirectedGraphNode newNode = copyEdges(node, nodes);
        return newNode;
    }
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        // use bfs to iterator all nodes
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        ArrayList<UndirectedGraphNode> nodeCollector = new ArrayList<UndirectedGraphNode>();
        HashSet<UndirectedGraphNode> nodeSet = new HashSet<UndirectedGraphNode>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode currentNode = queue.poll();
            if (nodeSet.contains(currentNode)) {
                continue;
            }
            nodeSet.add(currentNode);
            nodeCollector.add(currentNode);
            for (UndirectedGraphNode neighbor : currentNode.neighbors) {
                queue.offer(neighbor);
            }
        }
        return nodeCollector;
    }
    private UndirectedGraphNode copyEdges(UndirectedGraphNode node,
        ArrayList<UndirectedGraphNode> nodes) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> nodeMap = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        for (UndirectedGraphNode item : nodes) {
            nodeMap.put(item, new UndirectedGraphNode(item.label));
        }
        
        for (UndirectedGraphNode item : nodes) {
            UndirectedGraphNode newItem = nodeMap.get(item);
            for (UndirectedGraphNode neighbor : item.neighbors) {
                newItem.neighbors.add(nodeMap.get(neighbor));
            }
        }
        return nodeMap.get(node);
    }
}
