package four;

import one.Bag;

public class Graph {
	
	private int V;
	private Bag<Integer>[] adj;
	private int E;
	
	@SuppressWarnings("unchecked")
	public Graph(int V){
		adj=(Bag<Integer>[])new Bag[V];
		this.V=V;
		this.E=0;
		
		for(int i=0;i<V;i++){
			adj[i]=new Bag<Integer>();
		}
	}
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v,int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	//v与w是否联通
	public boolean contain(int v,int w){
		for(int k:adj[v]){
			if(k==w){
				return true;
			}
		}
		return false;
	}
	
	
	public Iterable<Integer>  adj(int v){
		return adj[v];
	}
	

}
