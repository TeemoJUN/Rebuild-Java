package four;

import one.Queue;
import one.Stack;
/*
 * 
 * ��ȱ���
 * 
 * */
public class BreadthFirstPaths {
	private boolean[] marked;//����Ѿ�������
	private int[] edgeTo;//����·��
	private int s;//��ʼ�ĵ�
	
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
	//�Ƿ񾭹�v��
	public boolean hasPathTo(int v){
		return marked[v];
	}
	//��ô����v��
	public Iterable<Integer> pathTo(int v){
		Stack<Integer> stack=new Stack<Integer>();
		for(int w=v;w!=s;w=edgeTo[w]){
			stack.push(w);
		}
		stack.push(s);
		return stack;
	}
	
}
