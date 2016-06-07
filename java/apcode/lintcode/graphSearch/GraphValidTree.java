package lintcode.graphSearch;

import java.util.LinkedList;
import java.util.Queue;
/*
 * edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
 * 2, []
 * 1, []
 * 起始的边界情况真的很难弄
 * 
 * 这个题居然是union find的题，哎~~~
 * 图的联通问题，记住使用并查集
 */
public class GraphValidTree {
    public boolean validTree2(int n, int[][] edges) {
        // 无向
        // 给的是边
    	// dfs Vs bfs?
    	// 使用bfs吧, 对环要考虑考虑, 而且还有图不连通的情况
//    	if (n < 1 || edges == null || edges.length == 0) {
    	if (n <= 1 || edges == null) {
    		return true;
    	}
    	// 吃亏在了edges的表示方式上了，不是标准给的，没办法快速获得一个节点的领边
    	return bfs(n, edges);
    }
    private boolean bfs(int n, int[][] edges) {
//    	Queue<Integer> queue = new LinkedList<Integer>();
    	boolean[] visited = new boolean[n];
    	for (int i = 0; i < n; i++) {
    		visited[i] = false;
    	}
//    	int count = 0;
//    	queue.offer(0);
//    	visited[0] = true;
    	
//    	while (!queue.isEmpty()) {
//    		int current = queue.poll();
//    		for (int i = 0; i < edges[current].length; i++) {
//    			// has cycle
//    			if (visited[i]) {
//    				return false;
//    			}
//    			queue.offer(i);
//    			visited[i] = true;
//    		}
//    		count++;
//    	}
    	// 这个方法不对，比如 3->4, 5->6, 3->5 这样会被判为cycle
    	for (int i = 0; i < edges.length; i++) {
    		if (visited[edges[i][0]] && visited[edges[i][1]]) {
        		// has cycle
    			return false;
    		}
    		visited[edges[i][0]] = true;
    		visited[edges[i][1]] = true;
    	}
    	// more than one component
    	for (int i = 0; i < n; i++) {
    		if (visited[i] == false) {
    			return false;
    		}
    	}
    	return true;
    }
    /********** union find ********/
    class UnionFind {
        private int ids[];
        private int size;
        private int sz[]; // for balance union
        private int count;
        public UnionFind(int n) {
            size = n;
            count = n;
            ids = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++) {
                ids[i] = i;
                sz[i] = 1;
            }
        }
        public int find(int node) {
            // using find with path compression
            // node != ids[node] ==> node is component root
            // 0 0 0 2 3 --> 
            //   0
            //  / \
            // 1   2
            //     |
            //     3
            //     |
            //     4
            // 4 != 3; 3 != 2; 2 != 0; 0 != 0
            int parent = node;
            while (parent != ids[parent]) {
                parent = ids[parent];
            }
            // compress
            //     0
            //  / / \ \
            // 1  2  3 4

            int node2 = node;
            int temp = -1;
            while (node2 != ids[node2]) {
                temp = ids[node2];
                ids[node2] = parent;
                node2 = temp;
            }
            return parent;
        }
        public void union(int node1, int node2) {
            int node1Id = find(node1);
            int node2Id = find(node2);
            if (node1Id == node2Id) {
                return;
            }
            // weighted union for balance the path
            if (sz[node1Id] > sz[node2Id]) {
                ids[node2Id] = node1Id;
                sz[node1Id] += sz[node2Id];
            } else {
                ids[node1Id] = node2Id;
                sz[node2Id] += sz[node2Id];
            }
        }
    }
    public boolean validTree(int n, int[][] edges) {
    	if (n <= 1 || edges == null) {
    		return true;
    	}
        // tree should have n nodes with n-1 edges
        if (n - 1 != edges.length) {
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        int len = edges.length;
        for (int i = 0; i < len; i++) {
        	if (uf.find(edges[i][0]) == uf.find(edges[i][1])) {
        		return false;
        	}
        	uf.union(edges[i][0], edges[i][1]);
        }
    	return true;
    }
}
