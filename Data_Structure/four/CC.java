package four;

/*
 * ��ͨͼ
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
	//��ȱ���
	private void dfs(Graph g,int s){
		marked[s]=true;
		id[s]=count;
		for(int w:g.adj(s)){
			if(!marked[w]){
				dfs(g,w);
			}
		}
	}
	//v��w�Ƿ���ͨ
	public boolean connected(int v,int w){
		return id[v]==id[w];
	}
	//��ǰv���ڵڼ�����ͨͼ
	public int id(int v){
		return id[v];
	}
	//�ж�����ͨ��ͼ
	public int count(){
		return count;
	}
	
}








