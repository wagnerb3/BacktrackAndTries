package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.String;

public class Solution {
	private static HashMap<String, String> MORSEMAP = new HashMap<>();
	private static ArrayList<String> DICTIONARY = new ArrayList<>();
	private static ArrayList<String> MORSE = new ArrayList<>();
	private static int MAX = 6;
	private static ArrayList<String> WORD = new ArrayList<>();

	// Loads Morse Code and Dictionary from files
	public static void loadFiles() {
		String filename = "";
		try {
			filename = "C:/Users/got2b/git/BacktrackAndTries/Backtrack/src/morse.txt";
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] letters = line.split(" ");
				MORSEMAP.put(letters[1], letters[0]);
				MORSE.add(letters[1]);
			}
			reader.close();
			filename = "C:/Users/got2b/git/BacktrackAndTries/Backtrack/src/dictionary.txt";
			reader = new BufferedReader(new FileReader(filename));
			while ((line = reader.readLine()) != null) {
				DICTIONARY.add(line);
			}
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
	}

	// Finds word in Dictionary
	// Returns String "0" if not found
	public static String findWord(String morsed) {
		String[] split = morsed.split(" ");
		String word = "";
		for (String letter : split) {
			word += MORSEMAP.get(letter);
		}
		if (DICTIONARY.contains(word)) {
			return word;
		}
		return "0";
	}

	// Part 1
	// Checks to see if morsed word is in Dictionary
	public static void handleSpacedLetters(String morsed) {
		String result = findWord(morsed);
		if (!result.equals("0")) {
			System.out.println(result);
		}
	}

	/*
	 * // Creates disposable copy of given ArrayList public static ArrayList<String>
	 * copyArr(ArrayList<String> arr) { return new ArrayList<>(arr); }
	 */

	// Returns ArrayList starting with given string
	public static ArrayList<String> filter(ArrayList<String> w, String s) {
		w.removeIf(a -> !a.startsWith(s));
		return w;
	}

	// Part 2
	// Uses Backtracking to find words
	public static void findWords(String morse, String current) {
		ArrayList<String> possibilities = filter(new ArrayList<>(DICTIONARY), current);
		if (possibilities.size() == 0) {
			return;
		}
		if (morse.equals("") && DICTIONARY.contains(current)) {
			WORD.add(current);
		}
		for (String key : MORSE) {
			if (morse.startsWith(key)) {
				findWords(morse.substring(key.length()), current + MORSEMAP.get(key));
			}
		}
	}

	public static void handleWord(String morsed) {
		findWords(morsed, "");
		for (String word : WORD) {
			System.out.println(word);
		}
	}

	public static void handleSpacedWords(String morsed) {
		System.out.println("INCOMPLETE PART 3");
	}

	public static void handleSentence(String morsed) {
		System.out.println("INCOMPLETE PART 4");
	}

	public static void main(String[] args) {
		loadFiles();
		// Get input from stdin
		Scanner scanner = new Scanner(System.in);
		String command = scanner.nextLine();
		// Parse the style and morsed code value
		String[] parts = command.split(":");
		String style = parts[0].trim();
		String morsed = parts[1].trim();

		switch (style) {
		case "Spaced Letters":
			handleSpacedLetters(morsed);
			break;
		case "Word":
			handleWord(morsed);
			break;
		case "Spaced Words":
			handleSpacedWords(morsed);
			break;
		case "Sentence":
			handleSentence(morsed);
			break;
		}

	}

}