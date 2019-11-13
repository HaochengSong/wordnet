import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedCycle;
import java.util.ArrayList;
public class WordNet {
	private SeparateChainingHashST<String, Bag<Integer>> set;
	private SAP sap;
	private int vertex;
	private ArrayList<String> sysnets;
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new java.lang.IllegalArgumentException("IllegalArgumentException!");
		In in = new In(synsets);
		set = new SeparateChainingHashST<String, Bag<Integer>>();
		Bag<Integer> bag;
		vertex = 0;
		//int edge = 0;
		sysnets = new ArrayList<String>();
		String[] text;
		String[] noun;
		while (!in.isEmpty()) {
			text = in.readLine().split(",");
			noun = text[1].split(" ");
			for (int i = 0; i < noun.length; i++) {
				if (set.contains(noun[i]))
					set.get(noun[i]).add(Integer.parseInt(text[0]));
				else {
					bag = new Bag<Integer>();
					bag.add(Integer.parseInt(text[0]));
					set.put(noun[i], bag);
				}
			}
			vertex++;
			sysnets.add(text[1]);
		}
		Digraph graph = new Digraph(vertex);
		int notRoot = 0;
		in = new In(hypernyms);
		while (!in.isEmpty()) {
			text = in.readLine().split(",");
			if (text.length > 1)
				notRoot++;
			/*for (int i = 0; i < text.length; i++)
				StdOut.print(Integer.parseInt(text[i]) + ", ");
			StdOut.println();*/
			for (int i = 1; i < text.length; i++) {
				graph.addEdge(Integer.parseInt(text[0]), Integer.parseInt(text[i]));
				//edge++;
			}
		}
		DirectedCycle d = new DirectedCycle(graph);
		//StdOut.println("vertex: " + vertex + " notRoot: " + notRoot);
		if (vertex - notRoot > 1)
			throw new java.lang.IllegalArgumentException("Have more than one root");
		if (d.hasCycle())
			throw new java.lang.IllegalArgumentException("Not a directedCycle");
		sap = new SAP(graph);
	}
	public Iterable<String> nouns() {
		return set.keys();
	}
	public boolean isNoun(String word) {
		if (word == null)
			throw new java.lang.IllegalArgumentException("IllegalArgumentException!");
		return set.contains(word);
	}
	public int distance(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("IllegalArgumentException!");
		Bag<Integer> a = set.get(nounA);
		Bag<Integer> b = set.get(nounB);
		return sap.length(a, b);
	}
	public String sap(String nounA, String nounB) {
		if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("IllegalArgumentException!");
		Iterable<Integer> a = set.get(nounA);
		Iterable<Integer> b = set.get(nounB);
		int ancestor = sap.ancestor(a, b);
		return sysnets.get(ancestor);
	}

	public static void main(String[] args) {
		WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
		StdOut.println(w.sap("worm", "bird"));
		StdOut.println(w.distance("bird", "bird"));
		StdOut.println("vertex: " + w.vertex);
	}
}
