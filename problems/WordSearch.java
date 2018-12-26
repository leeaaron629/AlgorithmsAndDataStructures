import java.util.Arrays;

public class WordSearch {

	public static void main(String[] args) {
		WordSearch ws = new WordSearch();
		char[][] board = {{'a', 'a'}};
		String word = "aaa";

//		System.out.println(ws.exist(board, word));

		board = new char[][] {{'a'}};
		word = "a";

//		System.out.println(ws.exist(board, word));

		board = new char[][] {{'a','b'}, {'c','d'}};
		word = "cdba";

		System.out.println(ws.exist(board, word));


	}

	public boolean exist(char[][] board, String word) {

		// Check for edge cases
		if (board.length == 0)
			return false;

		if (board.length == 1 && board[0].length == 1) {
			if (word.length() == 1 && word.charAt(0) == board[0][0])
				return true;
		}

		// Create a new visited array same size as board
		boolean[][] visited = new boolean[board.length][board[0].length];
		initializeVisitedArray(visited);
		// Iterate through the board and find the first matching character
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (word.charAt(0) == board[i][j]) {
					// If first letter in word matches do a DFS traversal to check
					if (check(i, j, board, word, visited))
						return true;
				}
			}
		}

		return false;
	}

	private boolean check(int i, int j, char[][] board, String word, boolean[][] visited) {

		if (word.length() <= 0)
			return true;

		if (word.charAt(0) != board[i][j])
			return false;

		if (visited[i][j] == true)
			return false;
		else
			visited[i][j] = true;

		if (canVisit(i - 1, j, board)) {
			if (check(i - 1, j, board, word.substring(1), visited))
				return true;
		}
		if (canVisit(i + 1, j, board)) {
			if (check(i + 1, j, board, word.substring(1), visited))
				return true;
		}
		if (canVisit(i, j - 1, board)) {
			if (check(i, j - 1, board, word.substring(1), visited))
				return true;
		}
		if (canVisit(i, j + 1, board)) {
			if (check(i, j + 1, board, word.substring(1), visited))
				return true;
		}

		visited[i][j] = false;

		return false;
	}

	private boolean canVisit(int i, int j, char[][] board) {

		// Check if board is valid
		if (i < board.length && j < board[0].length && i >= 0 && j >= 0)
			return true;
		else
			return false;
	}

	private void initializeVisitedArray(boolean[][] visited) {
		for (int i = 0; i < visited.length; ++i) {
			Arrays.fill(visited[i], false);
		}
	}

}
