# Trie Data Structure Project

## Overview
This project implements a Trie data structure as part of the ICS202 (Data Structures and Algorithms) course at KFUPM in 2021. The Trie is a tree-like data structure used for efficient retrieval of keys in a dataset of strings.

## Features
- Create an empty Trie
- Create a Trie with initial letters
- Insert words into the Trie
- Delete words from the Trie
- List all words that begin with a given prefix
- Get the size of the Trie

## Project Structure
- `src/`
  - `ProjectInterface.java`: Main class with the user interface
  - `Trie.java`: Implementation of the Trie data structure
  - `TrieNode.java`: Class representing a single node in the Trie
  - `TrieTest.java`: This is just for testing the methods
- `Dictionary.txt`: A text file containing a list of words for the Trie (in root directory)

## How to Run
1. Ensure you have Java installed on your system
2. Navigate to the project root directory
3. Compile the Java files: javac src/*.java
4. Run the program: java -cp src ProjectInterface


## Requirements
- Java SE Development Kit (JDK) 8 or higher
- `Dictionary.txt` file in the root directory of the project

## Usage
The program presents a menu-driven interface where you can:
1. Create an empty Trie
2. Create a Trie with initial letters
3. Insert a word
4. Delete a word
5. List all words that begin with a prefix
6. Get the size of the Trie
7. End the program

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
