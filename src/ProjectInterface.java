import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

//a class to manipulate any trie however you want.

public class ProjectInterface {

	public static void main(String[] args) throws FileNotFoundException {
		Trie trie = new Trie();
		boolean flag = true;
		Scanner inputUser = new Scanner(System.in);
		Scanner answer = new Scanner(System.in);
		java.io.File filein = new java.io.File("Dictionary.txt"); //Get all words from dictionary.
		while(flag) {
			try { //show the menu of options.
			System.out.println("TRIE PROJECT: Enter your choice?");
			System.out.println("1) Create an empty trie");
			System.out.println("2) Create a trie with initial letters");
			System.out.println("3) Insert a word");
			System.out.println("4) Delete a word");
			System.out.println("5) List all words that begin with a prefix");
			System.out.println("6) Size of the trie");
			System.out.println("7) End");
			int choice = inputUser.nextInt();
			String word = "";
			switch(choice) {
			  	case 1: //Clear the trie.
			  		trie.clear();
			  		System.out.println("\nAn empty trie has been created.\n");
			  		break;
			  	case 2: //create a new trie with initial letters.
			  		trie.clear();
			  		System.out.println("\nEnter your list of letters (White spaces and non-letters will be ignored): ");
			  		word = answer.nextLine().toUpperCase().replaceAll("\\s","").replaceAll("[^a-zA-Z]+", "");;
					Scanner fileInput = new Scanner(filein);
				    String[] combinations = getAllCombinations(word);  //gets all permutations of the given letters.
					while(fileInput.hasNext()) {
						String wordFromFile = fileInput.nextLine();
						for(String each : combinations) //if any of the permutations is in the dictionary, add it to the trie.
							if(each.equals(wordFromFile)) {
								trie.insert(wordFromFile);
							    System.out.print(wordFromFile + " ");
								break;
							}
					}
					fileInput.close();
					System.out.println("Trie Created.\n");
					
			  		break;
			  	case 3: //insert a word.
			  		System.out.print("\nProvide the desired word to be inserted: ");
			  		word = answer.nextLine().toUpperCase();
			  		trie.insert(word);
			  		System.out.println("Word inserted successfully.\n");
			  		break;
			  	case 4: //delete a word.
			  		System.out.print("\nProvide the desired word to be deleted: ");
			  		word = answer.nextLine().toUpperCase();
			  		trie.delete(word);
			  		System.out.println("Word deleted successfully.\n");
			  		break;
			  	case 5: //show all words from a given prefix.
			  		System.out.print("\nProvide the desired prefix: ");
			  		word = answer.nextLine().toUpperCase();
			  		String[] prefixes = trie.allWordsPrefix(word);
			  		System.out.print("Found the following words: ");
					for(String eachWord : prefixes)
						System.out.print(eachWord + " ");
					System.out.println("\n");
			  		break;
			  	case 6: //shows the size of the trie (with the root included).
			  		int size = trie.size();
			  		System.out.println("\nSize of the trie is: " + size);
			  		break;
			  	case 7: //close the program.
			  		flag = false;
			  		System.out.println("Thank you! Have a great day!");
			  		break;
			  	default: //if any invalid number is provided
			  		System.out.println("Please provide a valid choice");
				}
			}
			catch (IllegalArgumentException ex) { //if any invalid input is provided within the functionality of the options.
				System.out.println(ex.getMessage() + "\n");
			}
			catch (Exception ex) { //if an invalid input has been provided instead of any of the given numbers in the menu.
				System.out.println("Please provide a valid choice\n");
				inputUser.nextLine();
			}
		}
		inputUser.close();
		answer.close();
	}
	
	//this method returns all possible combinations from given letters.
	public static String[] getAllCombinations(String allChars){
        Set<String> set = new TreeSet<String>();
        if (allChars.length() == 1) //if only a single letter is provided, method ends.
            set.add(allChars);
        else {
        	//begin a string with all letters provided.
            for (int i=0; i<allChars.length(); i++) {
                String before = allChars.substring(0, i);
                String after = allChars.substring(i+1);
                String remaining = before+after;

                //recurse to find and add all possible combinations with the provided characters.
                for (String allComb : getAllCombinations(remaining)){
                    set.add(allChars.charAt(i) + allComb);
                    set.add(allComb);
                }
            }
        }
        return set.toArray((new String[set.size()])); //we need the return as an array.
    }
}
			