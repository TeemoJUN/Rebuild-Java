package four;

public class DepthFirstSearch {
	
	private boolean[] marked;
	private int count;
	
	public DepthFirstSearch(Graph g,int s){
		marked=new boolean[g.V()];
		this.count=0;
		dfs(g,s);
	}
	
	private void dfs(Graph g,int v){
		marked[v]=true;
		count++;
		for(int w:g.adj(v)){
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	
	public int count(){
		return count;
	}
}
