//A class to represent a single Trie node.

public class TrieNode {
	protected TrieNode[] children; //Structure used for Trie is an array.
	private boolean completeWord;
	
	public TrieNode() {
		this.children = new TrieNode[26]; //26 Because that's the number of alphabetical letters, each index representing a letter.
		this.completeWord = false; // stating that this node is the point that completes a word.
	}
	
	public void setCompleteWord(boolean completeWord) {
		this.completeWord = completeWord;
	}
	
	public boolean getCompleteWord() {
		return completeWord;
	}
}
