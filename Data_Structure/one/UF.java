package one;

public class UF {
	private int[] id;//连通分量
	private int count;//联通量
	
	private int[] size;
	
	public UF(int N){
		this.id=new int[N];
		this.count=N;
		for(int i=0;i<N;i++){
			id[i]=i;
		}
		this.size=new int[N];
		for(int i=0;i<N;i++){
			size[i]=1;
		}
	}
	
	//p的标识符
	public int find(int p){
		while(p!=id[p]){
			p=id[p];
		}
		return p;
	}
	//联通量
	public int count(){
		return count;
	}
	
	//联合p,q
	public void union(int p,int q){
		int pId=find(p);
		int qId=find(q);
		if(pId==qId){
			return ;
		}
		if(size[pId]>size[qId]){
			id[qId]=id[pId];
			size[pId]+=size[qId];
		}
		else{
			id[pId]=id[qId];
			size[qId]+=size[pId];
		}
		count--;
	}
	//p和q两点是否相连
	public boolean connected(int p,int q){
		int pId=find(p);
		int qId=find(q);
		return pId==qId;
	}	
}
