package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TrieNode {
	HashMap<Character, TrieNode> children;
	String word;
	
	public void addWord(TrieNode root, String newWord) {
		TrieNode current = root;
		for (char letter : newWord.toCharArray()) {
			if (!current.children.containsKey(letter)){
				current.children.put(letter, new TrieNode());
			}
			current = current.children.get(letter);
		}
		current.word = newWord;
	}
	
	public boolean hasWord(TrieNode root, String goal) {
		TrieNode current = root;
		for (char letter : goal.toCharArray()) {
			if (current.children.containsKey(letter)) {
				current = current.children.get(letter);
			}
			else {
				return false;
			}
		}
		return true;
	}
	
	public ArrayList<String> getAllWords(TrieNode root){
		ArrayList<String> result = new ArrayList<>();
		if (root.word != null) {
			result.add(root.word);
		}
		for(char letter : root.children.keySet()) {
			ArrayList<String> childWords = getAllWords(root.children.get(letter));
			result.addAll(childWords);
		}
		Collections.sort(result);
		return result;
	}
	
	public ArrayList<String> autocomplete(TrieNode root, String start){
		TrieNode current = root;
		for (char letter : start.toCharArray()) {
			if (current.children.containsKey(letter)) {
				current = current.children.get(letter);
			}
			else {
				return new ArrayList<String>();
			}
		}
		return current.getAllWords(current);
	}
}
