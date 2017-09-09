package four;

/*
 * 联通图
 * 
 * 
 * */


public class CC {
	private boolean[] marked;
	private int count;
	private int[] id;
	
	public CC(Graph g){
		
		int N=g.V();
		this.id=new int[N];
		this.marked=new boolean[N];
		for(int i=0;i<N;i++){
			if(!marked[i]){
				dfs(g,i);
				count++;
			}
		}
	}
	//深度遍历
	private void dfs(Graph g,int s){
		marked[s]=true;
		id[s]=count;
		for(int w:g.adj(s)){
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	//v和w是否联通
	public boolean connected(int v,int w){
		return id[v]==id[w];
	}
	//当前v属于第几个联通图
	public int id(int v){
		return id[v];
	}
	//有多少联通的图
	public int count(){
		return count;
	}
	
}








