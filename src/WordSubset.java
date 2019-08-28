import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordSubset {

	public static void main(String[] args) {
		WordSubset driver = new WordSubset();
		String[] A = { "leetcode"};
		String[] B = { "e", "oo"};
	
		List<String> answers = driver.wordSubsets(A, B);
		for (String answer : answers) {
			System.out.print(answer + " ");
		}
	}
	
	/**
	 * The approach is to reduce the words in B to a single word
	 * and then see if the singleB word is a subset of each word in A
	 * @param A
	 * @param B
	 * @return
	 */
	public List<String> wordSubsets(String[] A, String[] B) {
		List<String> answers = new ArrayList<>();
		
		int[] reducedMap = new int[26];
		for (String s : B) {
			int[] map = countLetters(s);
			
			for (int i = 0; i < 26; i++)
				reducedMap[i] = Math.max(reducedMap[i], map[i]);
		}		
		
		for (String word : A) {
			if (checkWord(word, reducedMap)) {
				answers.add(word);
			}
		}
		
		return answers;
	}

	
	public int[] countLetters(String s) {
		int[] map = new int[26];
		
		for (char c : s.toCharArray()) {
			map[c - 'a']++;
		}
		
		return map;
	}

	public boolean checkWord(String word, int[] reducedMap) {
		
		int[] wordMap = countLetters(word);
		for (int i = 0; i < wordMap.length; i++) {
			if (wordMap[i] < reducedMap[i])
				return false;
		}
		
		return true;
	}

		


}
