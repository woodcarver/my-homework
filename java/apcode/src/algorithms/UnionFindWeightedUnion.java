package algorithms;

public class UnionFindWeightedUnion implements UnionFind{
	private int id[];
	private int subTreeSize[];//这个并不指树的深度，而是指树节点的个数
	private int netSize;
	private int count;
	public UnionFindWeightedUnion(int size) throws UnionFindException{
		if(size>=0){
			netSize=size;
			id= new int[netSize];
			subTreeSize= new int[netSize];
		}else{
			throw new UnionFindException("the net size can not be negative");
		}
		count=netSize;
		// condition: netSize>=0
		for(int i=0; i<netSize; i++){
			id[i]=i;
			subTreeSize[i]=1;
		}
	}
	@Override
	public int find(int p) {
		while(p!=id[p])
			p=id[p];
		return p;
	}

	@Override
	public void union(int p, int q) {
		int pRoot=find(p);
		int qRoot=find(q);
		if(pRoot==qRoot)
			return;
		if(subTreeSize[pRoot]<=subTreeSize[qRoot]){
			id[pRoot]=qRoot;
			subTreeSize[qRoot]+=subTreeSize[pRoot];
		}else{
			id[qRoot]=pRoot;
			subTreeSize[pRoot]+=subTreeSize[qRoot];
		}
		count--;
	}
	public void unionVisible(int p, int q){
		union(p,q);
		System.out.println(this);
	}
	@Override
	public boolean isConnected(int p, int q) {
		return find(p)==find(q);
	}
	public String toString(){
		String str= "";
		for(int i=0; i<netSize; i++){
//			str+=id[i]+"("+subTreeSize[i]+"),";
			str+=id[i]+",";
		}
		return str;
	}
	@Override
	public int getComponentCount() {
		return count;
	}
	public static void main(String[] args){
		UnionFindWeightedUnion uf=new UnionFindWeightedUnion(5);
		System.out.println(uf);
		uf.unionVisible(1, 0);
		uf.unionVisible(2, 3);
		uf.unionVisible(3, 4);
		System.out.println(uf.getComponentCount());
		uf.unionVisible(1, 3);
		System.out.println(uf.getComponentCount());
	}
}
