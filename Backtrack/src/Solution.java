import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.String;

public class Solution {
	private static final HashMap<String, String> MORSE = (HashMap)readFile(HashMap.class);
	private final static ArrayList<String> DICTIONARY = (ArrayList)readFile(ArrayList.class);

	// Loads Morse Code from machine
	public static Object readFile(Class<?> c) {
		String filename = "";
		Object records = null;
		try {
			if (c == HashMap.class) {
				records = new HashMap<String, String>();
				filename = "C:/Users/got2b/Documents/College/College-Sophomore/CISC 320/320Eclipse/Backtrack/src/morse.txt";
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String line;
				while ((line = reader.readLine()) != null) {
					String[] letters = line.split(" ");
					((HashMap<String, String>) records).put(letters[1], letters[0]);
				}
				reader.close();
			} else {
				records = new ArrayList<String>();
				filename = "C:/Users/got2b/Documents/College/College-Sophomore/CISC 320/320Eclipse/Backtrack/src/dictionary.txt";
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String line;
				while ((line = reader.readLine()) != null) {
					((ArrayList<String>) records).add(line);
				}
				reader.close();
			}
		} catch (Exception e) {
			System.err.format("Exception occurred trying to read '%s'.", filename);
			e.printStackTrace();
		}
		return records;
	}

	public static void handleSpacedLetters(String morsed) {
		String[] split = morsed.split(" ");
		String word = "";
		for (String letter : split) {
			word += MORSE.get(letter);
		}
		if (DICTIONARY.contains(word)) {
			System.out.println(word);
		}
	}

	public static void handleWord(String morsed) {
		System.out.println("INCOMPLETE PART 2");
	}

	public static void handleSpacedWords(String morsed) {
		System.out.println("INCOMPLETE PART 3");
	}

	public static void handleSentence(String morsed) {
		System.out.println("INCOMPLETE PART 4");
	}

	public static void main(String[] args) {
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