import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructures.ListNode;

public class Sandbox {

	String[] map = { "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

	public static void main(String[] args) {
		Sandbox sb = new Sandbox();
		System.out.println(Arrays.toString(sb.diStringMatch("IIID")));

	}

	public int trailingZeroes(int n) {
		BigInteger[] answers = new BigInteger[n + 1];

		if (n == 0)
			return 0;

		answers[1] = new BigInteger("1");

		for (int i = 2; i < answers.length; i++) {
			answers[i] = answers[i - 1].multiply(new BigInteger("" + i));
		}

		int trailingZeros = 0;
		String numStr = answers[n].toString();
		System.out.println(numStr);

		for (int i = numStr.length() - 1; numStr.charAt(i) == '0'; i--) {
			trailingZeros++;
		}

		return trailingZeros;
	}

	public int[] diStringMatch(String S) {

		int dCount = S.length();
		int iCount = 0;

		int[] answer = new int[S.length() + 1];

		for (int i = 0; i < answer.length - 1; ++i) {
			if (S.charAt(i) == 'I') {
				// Increasing
				answer[i] = iCount++;
			} else if (S.charAt(i) == 'D') {
				// Decreasing
				answer[i] = dCount--;
			}
		}

		answer[S.length()] = iCount;

		return answer;
	}

	public int minDeletionSize(String[] A) {

		if (A.length == 1)
			return 0;

		int answer = 0;

		for (int i = 0; i < A[0].length(); i++) {
			for (int j = 1; j < A.length; j++) {
				if (A[j].charAt(i) < A[j - 1].charAt(i)) {
					answer++;
					break;
				}
			}

		}

		return answer;
	}

	public boolean validMountainArray(int[] A) {
		if (A.length < 3)
			return false;

		boolean wentUp = false;
		boolean wentDown = false;

		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i - 1])
				return false;

			if (A[i] > A[i - 1]) {
				wentUp = true;
			} else if (wentUp && A[i] < A[i - 1]) {
				wentDown = true;
			}
		}

		if (wentUp == true && wentDown == true)
			return true;
		else
			return false;
	}

	public boolean isAnagram(String s, String t) {
		Map<Character, Integer> map1 = new HashMap<>();
		Map<Character, Integer> map2 = new HashMap<>();

		if (s.length() != t.length())
			return false;

		for (int i = 0; i < s.length(); i++) {
			if (map1.containsKey(s.charAt(i))) {
				int val = map1.get(s.charAt(i));
				map1.put(s.charAt(i), val + 1);
			} else {
				map1.put(s.charAt(i), 1);
			}

			if (map2.containsKey(t.charAt(i))) {
				int val = map2.get(t.charAt(i));
				map2.put(t.charAt(i), val + 1);
			} else {
				map2.put(t.charAt(i), 1);
			}
		}

		for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
			if (map2.get(entry.getKey()) != entry.getValue())
				return false;
		}

		return true;
	}

	public int minAreaRect(int[][] points) {

		// Find all valid rectangles

		// 1. Find a set of valid points
		// 2. Find a second set of valid points

		// [1,1] [1,3] [3,1] [3,3]
		// [3,1] [3,3] [4,1] [4,3]

		// Key is Y, where they're located on the Y axis
		HashMap<Integer, List<int[]>> horizontalLines = new HashMap<>();
		// Key is X, where they're located on the X axis
		HashMap<Integer, List<int[]>> verticalLines = new HashMap<>();

		for (int i = 0; i < points.length; i++) {

			if (!horizontalLines.containsKey(points[i][1])) {
				List<int[]> pointList = new ArrayList<>();
				pointList.add(points[i]);
				horizontalLines.put(points[i][1], pointList);
			} else {
				horizontalLines.get(points[i][1]).add(points[i]);
			}

			if (!verticalLines.containsKey(points[i][0])) {
				List<int[]> pointList = new ArrayList<>();
				pointList.add(points[i]);
				verticalLines.put(points[i][0], pointList);
			} else {
				verticalLines.get(points[i][0]).add(points[i]);
			}

		}
		// Then find the valid rectangles with the minimum area
		printMap(horizontalLines);
		printMap(verticalLines);

		return 0;
	}

	private void printMap(HashMap<Integer, List<int[]>> map) {
		for (Map.Entry<Integer, List<int[]>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":");
			for (int[] arr : entry.getValue()) {
				System.out.print(Arrays.toString(arr) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public List<String> letterCombinations(String digits) {
		return getCombinations(digits, new ArrayList<String>());
	}

	private List<String> getCombinations(String digits, ArrayList<String> combinations) {

		if (digits.length() == 0 || digits.equals(""))
			return combinations;

		String letters = map[digits.charAt(0) - '1'];

		if (combinations.size() == 0) {
			for (int i = 0; i < letters.length(); i++)
				combinations.add(letters.charAt(i) + "");

			return getCombinations(digits.substring(1), combinations);
		} else {
			ArrayList<String> newCombos = new ArrayList<>();

			for (int i = 0; i < combinations.size(); i++) {
				for (int j = 0; j < letters.length(); j++) {
					String combo = new String(combinations.get(i) + letters.charAt(j));
					newCombos.add(combo);
				}
			}

			return getCombinations(digits.substring(1), newCombos);
		}

	}

	public ListNode oddEvenList(ListNode head) {
		// The first node will always be odd
		// The plan is to iterate through the LinkedList
		// If it's odd move in between the first even node
		// and the last odd node

		if (head == null || head.next == null)
			return head;

		int index = 0;

		ListNode current = head;
		ListNode odd = head;

		while (current != null && current.next != null) {

			if (index % 2 == 1) {
				if (current.next == null) {
					// Tail case
					current.next = odd.next;
					odd.next = current;
					odd = odd.next;

					while (odd.next != current)
						odd = odd.next;

					odd.next = null;
				} else {
					// Remove node
					ListNode temp = current.next;
					current.next = current.next.next;
					// Attach node to the front
					temp.next = odd.next;
					odd.next = temp;
					odd = odd.next;
				}
			}

			current = current.next;
			index++;
		}

		return head;
	}

	public int integerBreak(int n) {
		int[] ans = new int[n + 1];

		if (n == 0)
			return 0;
		if (n == 1 || n == 2)
			return 1;
		if (n == 3)
			return 2;

		ans[1] = 1;
		ans[2] = 2;
		ans[3] = 3;

		for (int i = 4; i < n + 1; i++) {
			int halves = i / 2;

			if (i % 2 == 0) {
				int a = ans[halves] * ans[halves];
				int b = ans[halves - 1] * ans[halves + 1];
				ans[i] = Math.max(a, b);
			} else {
				int a = ans[halves] * ans[halves + 1];
				int b = ans[halves - 1] * ans[halves + 2];
				ans[i] = Math.max(a, b);
			}
		}

		System.out.println(Arrays.toString(ans));

		return ans[n];
	}

	public List<List<String>> findDuplicate(String[] paths) {

		HashMap<String, List<String>> cache = new HashMap<>();

		for (String path : paths) {
			String[] dirInfo = path.split(" ");
			for (int i = 1; i < dirInfo.length; i++) {
				int beg = dirInfo[i].indexOf('(');
				if (beg > 0) {
					String content = dirInfo[i].substring(beg + 1, dirInfo[i].length() - 1);

					if (!cache.containsKey(content)) {
						cache.put(content, new ArrayList<>());
					}

					cache.get(content).add(dirInfo[0] + dirInfo[i].substring(0, beg));
				}
			}
		}

		List<List<String>> answers = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : cache.entrySet()) {
			answers.add(entry.getValue());
		}

		return answers;
	}
}
