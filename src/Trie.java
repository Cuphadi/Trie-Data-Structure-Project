////A class to represent the Trie data structure.

public class Trie {
    private TrieNode root;
	private int letter;
	
    public Trie() {
	this.root = new TrieNode(); //Always creating a trie will contain an initial root node.
    }
    
    //This method is to see if a given string is available inside the trie.
    public boolean contains(String s) {
    	s = s.toUpperCase();
    	TrieNode current = root; //we start traversing from root
    	for(int i = 0; i < s.length(); i++) {
    	    char c = s.charAt(i);
    	    int nodeLocation = c - 'A'; //acquire the index of the letter to decide which node to go next.
    	    if(current.children[nodeLocation] == null) //This condition if traversal stops because a node with a specific letter is not found.
    	    	return false;
    		current = current.children[nodeLocation]; //Traverse to next node.
    	   	}
    	if(current.getCompleteWord() == true) //If a node that completes the given word is in the trie, it returns true. Word is in trie
    		return true;
    	else
    		return false; //when the word is not found.
    }
    
    //This method is to see if a given prefix of any word is in the trie.
    public boolean isPrefix(String p) {
    	p = p.toUpperCase();
    	TrieNode current = root; //we start traversing from root
    	for(int i = 0; i < p.length(); i++) {
    	    char c = p.charAt(i);
    	    int nodeLocation = c - 'A'; //acquire the index of the letter to decide which node to go next.
    	    if(current.children[nodeLocation] == null) //This condition if traversal stops because a node with a specific letter is not found.
    	    	return false;
    		current = current.children[nodeLocation];  //Traverse to next node.
    	   	}
    	for(int i = 0; i < 26; i++) { //When we reach a node that completes the prefix, now we try and find a single word that has the given prefix.
    		if(current.children[i] != null)
    			return true; //returns true if a single word is found.
    	}
    	if(current.getCompleteWord()) //If a prefix is a word in itself.
    		return true;
    	return false; //if not word is found
    }
    
    //this method is to insert a given word in the trie.
    public void insert(String s) {
    	s = s.toUpperCase();
    	for (int i = 0; i < s.length(); i++) {
    		if(!(s.charAt(i) <= 'Z' && s.charAt(i) >= 'A')) //If the given string contains non-letters.
    			throw new IllegalArgumentException("Invalid input! Please provide a word with english letters only and without spaces.");
    	}
    		
    	TrieNode current = root; //we start traversing from root
    	for(int i = 0; i < s.length(); i++) {
    	    char c = s.charAt(i);
    	    int nodeLocation = c - 'A';  //acquire the index of the letter to decide which node to go next.
    	    if(current.children[nodeLocation] == null) {  //This condition is when the letter that continues the word isn't there, then we add a node for it.
    	    	TrieNode nextNode = new TrieNode();
    	    	current.children[nodeLocation] = nextNode; //This adds the node with the letter.
    	    	}
    		current = current.children[nodeLocation];  //Traverse to next node.
    	   	}
    	current.setCompleteWord(true); //To indicate that this node completes the given word.
    }
    
    //this method is to delete a given word in the trie if it is in the trie.
    public void delete(String s) {
    	if(!contains(s)) //If the word is not in the trie
    		throw new IllegalArgumentException("Invalid input! Please provide a word available in the used trie.");
    	else {
    		int depth = s.length();
    		String trueDepths = "";
        	s = s.toUpperCase();
        	TrieNode current = root; //we start traversing from root
        	for(int i = 0; i < depth; i++) {
        	    char c = s.charAt(i);
        	    int nodeLocation = c - 'A';  //acquire the index of the letter to decide which node to go next.
        	    if(current.getCompleteWord()) //if we reached to a word, memorize it's level. we need so to know if there are more words shorter than the given word.
        	    	trueDepths = i + "";
        	    int moreThan1 = 0;
        	    for(int j = 0; j < 26; j++) { //inside the loop, if there are 2 or more subnodes, memorize it's level. we need so to know if there are more words shorter than the given word.
        	    	if(current.children[j] != null)
        	    		moreThan1++;
        	    	if(moreThan1 > 1) {
        	    		trueDepths = i + "";
        	    		break;
        	    	}
        	    }
        		current = current.children[nodeLocation]; //Traverse to next node.
        	   	}
        	current.setCompleteWord(false); //reaching the node that completes the word, we delete it now.
        	if(isPrefix(s)) //if the word is prefix, no need to severe the links of subnodes. The word is successfully deleted.
        		return;
        	if(trueDepths.isEmpty())
        		trueDepths = "0";
        	current = root; //we start traversing from root once more.
        	for(int i = 0; i < Integer.valueOf(trueDepths); i++) { //we loop to the number of times of trueDepths. which has the level of the last word before the given word available in a trie.
        	    char c = s.charAt(i);
        	    int nodeLocation = c - 'A';
        		current = current.children[nodeLocation];
        	   	}
        	//Knowing that no others words resides within the subnodes, we severe the link to them.
    	    char c = s.charAt(Integer.valueOf(trueDepths));
    	    int nodeLocation = c - 'A';
    		current.children[nodeLocation] = null;
    }
   }
    
    //This method checks if the trie is empty.
    public boolean isEmpty() {
    	for(int i = 0; i < 26; i++) {
    		if(root.children[i] != null) //if there is a single node in the trie aside from the root, it returns false.
    			return false;
    	}
    	return true;
    }
    
    //this method clears the trie completely and a root.
    public void clear() {
    	root = new TrieNode();
    }
    
    //this method returns an array of all words that contains the given prefix
    public String[] allWordsPrefix(String p) {
    	String s = ""; //used to add all the words
    	String builder = ""; //used to build the letters that completes each word with the prefix. it's significance is in the recursive method.
    	if(!isPrefix(p)) { //if the given string isn't a prefix of any word in the trie.
    		throw new IllegalArgumentException("No words available!");
    	}
    	if(p.isBlank()) { //If the given string is empty.
    		throw new IllegalArgumentException("Empty input! Please provide a prefix and do not leave it empty");
    	}
    	p = p.toUpperCase();
	    char c = p.charAt(0);
	    builder += c;
    	TrieNode current = root.children[c - 'A'];
    	for(int i = 1; i < p.length(); i++) { //loop until the node that completes a prefix is reached.
    	    c = p.charAt(i);
    	    builder += c;
    	    int nodeLocation = c - 'A';
    		current = current.children[nodeLocation]; //Traverse to next node.
    	   	}
    	if(current.getCompleteWord()) //if the prefix is a word in itself, add it to string "s"
    		s += builder + " ";
    	for(int i = 0; i < 26; i++) { //When we reach a node that completes the prefix, now we try and find all words that has the given prefix.
    		if(current.children[i] != null) {
    			letter = 'A' + i;
    			s += allWordsPrefix(builder, current.children[i], (char)letter); //add the word in string "s" recursively.
    		}
    	}
    	String[] listWords = s.split(" "); //split the string into an array.
    	return listWords;
    }
    
    //this method is only called recursively by the public allWordsPrefix method.
    private String allWordsPrefix(String builder, TrieNode current, char letter) {
    	String s = "";
	    builder += letter;
	    if(current.getCompleteWord()) //if we reached a complete word, add it to string "s"
	    	s += builder + " ";
    	for(int i = 0; i < 26; i++) { //try and find the next node that completes a word.
    		if(current.children[i] != null) {
    			letter = (char) ('A' + i);
    			s += allWordsPrefix(builder, current.children[i], letter); //add the word in string "s" recursively.
    		}
    	}
    	return s;
    }
    
    //this method returns the number of nodes in a trie.
    public int size() {
    	return 1 + size(root); //we find the size recursively
    }
    
    private int size(TrieNode current) {
    	int counter = 0;
    	for(int i = 0; i < 26; i++) { //if a node is found with a letter, we add 1 and then we traverse this node and see if it contains more nodes.
    		if(current.children[i] != null) {
    			counter++;
    			counter += size(current.children[i]);
    		}
    	}
    	return counter;
    }
   
}
