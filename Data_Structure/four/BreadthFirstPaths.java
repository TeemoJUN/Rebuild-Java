package four;

import one.Queue;
import one.Stack;
/*
 * 
 * 广度遍历
 * 
 * */
public class BreadthFirstPaths {
	private boolean[] marked;//标记已经经过的
	private int[] edgeTo;//来的路径
	private int s;//开始的点
	
	public BreadthFirstPaths(Graph g,int s) throws Exception{
		this.s=s;
		int N=g.V();
		this.marked=new boolean[N];
		this.edgeTo=new int[N];
		bfs(g,s);
	}
	
	private void bfs(Graph g,int s) throws Exception{
		Queue<Integer> q=new Queue<Integer>();
		q.enqueue(s);
		
		while(!q.isEmpty()){
			int v=q.dequeue();
			for(int w:g.adj(v)){
				if(!marked[w]){
					edgeTo[w]=v;
					marked[w]=true;
					bfs(g,w);
				}
			}
		}
	}
	//是否经过v点
	public boolean hasPathTo(int v){
		return marked[v];
	}
	//怎么到达v点
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> stack=new Stack<Integer>();
		for(int w=v;w!=s;w=edgeTo[w]){
			stack.push(w);
		}
		stack.push(s);
		return stack;
	}
	
}
