package four;

import one.Stack;

public class DepthFirstPaths {
	private int s;

	private boolean[] marked;

	private int[] edge;

	public DepthFirstPaths(Graph g, int s) {
		int N = g.V();
		this.marked = new boolean[N];
		this.s = s;
		this.edge = new int[N];
		dfs(g, s);
	}

	private void dfs(Graph g, int v) {
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				edge[w] = v;
				dfs(g, w);
			}
		}
	}


	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();

		for (int w = v; v != s; v = edge[v]) {
			path.push(w);
		}
		path.push(s);
		return path;
	}
}
