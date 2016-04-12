package algorithms;

public interface UnionFind {
	int find(int p);
	void union(int p,int q);
	boolean isConnected(int p, int q);
	int getComponentCount();
}
