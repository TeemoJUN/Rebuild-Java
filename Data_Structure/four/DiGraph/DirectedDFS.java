package four.digraph;

public class DirectedDFS {
	private boolean[] marked;
	
	public DirectedDFS(Digraph g,int s){
		marked=new boolean[g.V()];
		dfs(g,s);
	}
	
	private void dfs(Digraph g,int s){
		marked[s]=true;
		
		for(int w:g.adj(s)){
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	
}
