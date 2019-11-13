import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
public class SAP {
	private Digraph graph;
	public SAP(Digraph G) {
		if (G == null)
			throw new java.lang.IllegalArgumentException("Illegal argument");
		graph = new Digraph(G.V());
		for (int i = 0; i < graph.V(); i++) {
			for (int j : G.adj(i))
				graph.addEdge(i, j);
		}
	}
	public int length(int v, int w) {
		if (v < 0 || v >= graph.V() || w < 0 || w >= graph.V())
			throw new java.lang.IllegalArgumentException("Illegal argument");
		int minPath = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);
		for (int i = 0; i <  graph.V(); i++)
			if (b1.hasPathTo(i) && b2.hasPathTo(i))
				minPath = Math.min(b1.distTo(i) + b2.distTo(i), minPath);
		if (minPath == Integer.MAX_VALUE)
			return -1;
		return minPath;
	}
	public int ancestor(int v, int w) {
		if (v < 0 || v >= graph.V() || w < 0 || w >= graph.V())
			throw new java.lang.IllegalArgumentException("Illegal argument");
		int minPath = Integer.MAX_VALUE;
		int minAncestor = 0;
		BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);
		for (int i = 0; i <  graph.V(); i++)
			if (b1.hasPathTo(i) && b2.hasPathTo(i)) {
				if (minPath > b1.distTo(i) + b2.distTo(i)) {
					minPath = b1.distTo(i) + b2.distTo(i);
					minAncestor = i;
				}
			}
		if (minPath == Integer.MAX_VALUE)
			return -1;
		return minAncestor;
	}
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new java.lang.IllegalArgumentException("Illegal argument");
		for (Integer i : v)
			if (i == null || i < 0 || i >= graph.V())
				throw new java.lang.IllegalArgumentException("Illegal argument");
		for (Integer i : w)
			if (i == null || i < 0 || i >= graph.V())
				throw new java.lang.IllegalArgumentException("Illegal argument");
		int minPath = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);
		for (int i = 0; i <  graph.V(); i++)
			if (b1.hasPathTo(i) && b2.hasPathTo(i))
				minPath = Math.min(b1.distTo(i) + b2.distTo(i), minPath);
		if (minPath == Integer.MAX_VALUE)
			return -1;
		return minPath;
		
	}
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new java.lang.IllegalArgumentException("Illegal argument");
		for (Integer i : v)
			if (i == null || i < 0 || i >= graph.V())
				throw new java.lang.IllegalArgumentException("Illegal argument");
		for (Integer i : w)
			if (i == null || i < 0 || i >= graph.V())
				throw new java.lang.IllegalArgumentException("Illegal argument");
		int minPath = Integer.MAX_VALUE;
		int minAncestor = 0;
		BreadthFirstDirectedPaths b1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths b2 = new BreadthFirstDirectedPaths(graph, w);
		for (int i = 0; i <  graph.V(); i++)
			if (b1.hasPathTo(i) && b2.hasPathTo(i)) {
				if (minPath > b1.distTo(i) + b2.distTo(i)) {
					minPath = b1.distTo(i) + b2.distTo(i);
					minAncestor = i;
				}
			}
		if (minPath == Integer.MAX_VALUE)
			return -1;
		return minAncestor;
	}
	public static void main(String[] args) {
	    In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    Queue<Integer> v = new Queue<Integer>();
	    v.enqueue(13);
	    v.enqueue(14);
	    v.enqueue(10);
	    Queue<Integer> w = new Queue<Integer>();
	    w.enqueue(15);
	    w.enqueue(6);
	    w.enqueue(24);
	    int length   = sap.length(1, 3);
	    int ancestor = sap.ancestor(1, 3);
	    StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    /*while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }*/

	}
}
