import java.util.Arrays;

public class KnightDialer {
    
	
	public static void main(String[] args) {
		KnightDialer kd = new KnightDialer();
		
		System.out.println(kd.knightDialer(3));
	}
	
	public int knightDialer(int N) {
		
	    int[][] moves = new int[][]{
	        {4,6},{6,8},{7,9},{4,8},{3,9,0},
	        {},{1,7,0},{2,6},{1,3},{2,4}};
		
		int[][] ans = new int[2][10];
		
		// Initialize the array
		Arrays.fill(ans[0], 1);
		// Iterate N times
		for (int i = 1; i <= N; i++) {
			// Figure out which row to use
			int row = i % 2;
			Arrays.fill(ans[row], 0);

			// Populate the row with new answer
			for (int j = 0; j < moves.length; j++) {
				for (int k = 0; k < moves[j].length; k++) {

					if (row == 0) {
						ans[row][j] += ans[1][moves[j][k]];
					} else {
						ans[row][j] += ans[0][moves[j][k]];
					}
				}
			}
		}
		
		// Sum up row with the correct answers
		int sum = 0;
		
		for (int i = 0; i < ans[(N-1) % 2].length; i++) {
			sum += ans[(N-1) % 2][i];
		}
		
        return sum;
    }

}
