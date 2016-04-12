package algorithms;

public class UnionFindQuckFind implements UnionFind {
	private int id[];
	private int netSize;
	private int count;
	public UnionFindQuckFind(int size) throws UnionFindException{
		if(size>=0){
			netSize=size;
			id= new int[netSize];
		}else{
			throw new UnionFindException("the net size can not be negative");
		}
		count=netSize;
		// condition: netSize>=0
		for(int i=0; i<netSize; i++){
			id[i]=i;
		}
	}
	@Override
	public int find(int p) {
		return id[p];
	}

	@Override
	public void union(int p, int q) {
		int pId=find(p);
		int qId=find(q);
		if(pId==qId)
			return;
		for(int i=0; i<netSize; i++){
			if(id[i]==pId)
				id[i]=qId;
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
	
	@Override
	public int getComponentCount(){
		return count;
	}
	public String toString(){
		String str= "";
		for(int i=0; i<netSize; i++){
			str+=id[i]+",";
		}
		return str;
	}
	
	public static void main(String[] args){
		UnionFindQuckFind uf=new UnionFindQuckFind(5);
		System.out.println(uf);
		uf.unionVisible(1, 0);
		uf.unionVisible(2, 3);
		uf.unionVisible(3, 4);
		System.out.println(uf.getComponentCount());
		uf.unionVisible(1, 3);
		System.out.println(uf.getComponentCount());
		UnionFindQuckFind uf2=new UnionFindQuckFind(-5);
		uf2.getComponentCount();
	}
}
