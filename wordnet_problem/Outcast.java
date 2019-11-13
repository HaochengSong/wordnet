import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Outcast {
	private WordNet wordnet;
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}
	public String outcast(String[] nouns) {
		int sum;
		int max = Integer.MIN_VALUE;
		String maxNouns = null;
		for (int i = 0; i < nouns.length; i++) {
			sum = 0;
			for (int j = 0; j < nouns.length; j++)
				sum += wordnet.distance(nouns[i], nouns[j]);
			if (sum > max) {
				max = sum;
				maxNouns = nouns[i];
			}
		}
		return maxNouns;
	}
	public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}
