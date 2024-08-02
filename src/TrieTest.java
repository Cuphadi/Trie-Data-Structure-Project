import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//This class is just testing the methods. It is not the menu interface class.
//This is just ScratchBook to try stuff out for the project. It is not a significant part of the project.
public class TrieTest {

	public static void main(String[] args) throws FileNotFoundException {
		Trie trie = new Trie();
		System.out.println("Enter your list of letters> ");
		Scanner inputUser = new Scanner(System.in);
		String chosen = inputUser.nextLine().toUpperCase();  
		java.io.File filein = new java.io.File("Dictionary.txt");
		Scanner input = new Scanner(filein);
	        
	    String[] ans = getAllCombinations(chosen); 
	    for(String i : ans)
	    	System.out.print(i + " ");
	    
		while(input.hasNext()) {
			String word = input.nextLine();
			for(String each : ans)
				if(each.equals(word)) {
					trie.insert(word);
					break;
				}
		}
		
		System.out.println("Trie Created.");
		System.out.println("Enter a word to search for> ");
		String search = inputUser.next();
		boolean a = trie.contains(search);
		System.out.println("Word Found: " + search + " " + a);
		System.out.println("count is: " + trie.size());
		System.out.println("Enter a prefix> ");
		String prefix = inputUser.next();
		String[] l = trie.allWordsPrefix(prefix);
		System.out.print("Found the following words: ");
		for(String z : l)
			System.out.print(z + " ");
		System.out.print("Enter a word to insert> ");
		String insert = inputUser.next();
		trie.insert(insert);
		System.out.println("count is: " + trie.size());
		System.out.print("Enter a word to delete> ");
		String delete = inputUser.next();
		trie.delete(delete);
		System.out.println("count is: " + trie.size());
		input.close();
		inputUser.close();
		

		
		
		
		/*Trie trie = new Trie();
		trie.insert("Babawowe");
		trie.insert("Babawowo");
		trie.insert("yes");
		System.out.println(trie.size());
		boolean a = trie.contains("babawowe");
		System.out.println(a);
		trie.delete("babawowe");
		System.out.println(trie.size());
		a = trie.contains("babawowe");
		System.out.println(a);
		a = trie.contains("babawowo");
		System.out.println(a);
		String[] z = trie.allWordsPrefix("babawow");
		for(String k : z)
			System.out.println(k);
		trie.delete("babawowo");
		System.out.println(trie.size());
		a = trie.contains("babawowo");
		System.out.println(a);
		boolean a = trie.contains("YES");
		System.out.println(a);
		boolean b = trie.isPrefix("baba");
		System.out.println(b);
		System.out.println(trie.size());
		System.out.println(trie.isEmpty());
		String[] words = trie.allWordsPrefix("b");
		for(String l : words)
			System.out.println(l);
		
		trie.clear();
		a = trie.contains("yes");
		System.out.println(a);
		System.out.println(trie.size());
		b = trie.isPrefix("");
		System.out.println(b);
		System.out.println(trie.isEmpty());*/
		
	}
	
	
	public static String[] getAllCombinations(String allChars){
        Set<String> set = new TreeSet<String>();
        if (allChars.length() == 1)
            set.add(allChars);
        else {
            for (int i=0; i<allChars.length(); i++) {
                String before = allChars.substring(0, i);
                String after = allChars.substring(i+1);
                String remaining = before+after;

                for (String allComb : getAllCombinations(remaining)){
                    set.add(allChars.charAt(i) + allComb);
                    set.add(allComb);
                    //set.add(allComb + allComb.charAt(0));
                    //set.add(allComb.charAt(0) + allComb);
                }
            }
        }
        return set.toArray((new String[set.size()]));
    }

}


