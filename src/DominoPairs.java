import java.util.HashMap;
import java.util.Map;

public class DominoPairs {

    public int numEquivDominoPairs(int[][] dominoes) {

        Map<String, Integer> dominoesFrequencies = new HashMap<>();

        for (int[] domino : dominoes) {

            String key;

            if (domino[0] < domino[1]) {
                key = "" + domino[0] + domino[1];
            } else {
                key = "" + domino[1] + domino[0];
            }

            dominoesFrequencies.put(key, dominoesFrequencies.getOrDefault(key, 0) + 1);
        }

        int answer = 0;

        for (Map.Entry<String, Integer> entry : dominoesFrequencies.entrySet()) {
            if (entry.getValue() > 0) {
                answer += getCombinations(entry.getValue());
            }

        }

        return answer;

    }

    private int getCombinations(int n) {

        int result = 0;

        for (int i = n-1; i >= 0; i--) {
            result += i;
        }

        return result;

    }

    public static void main(String[] args) {
        Integer answer = new DominoPairs().numEquivDominoPairs(new int[][]{{1,2}, {1,2}, {2,1}, {2,1}, {4,5}, {3,5}, {5,4}});
        System.out.println(answer);
        answer = new DominoPairs().numEquivDominoPairs(new int[][]{{1,2},{1,2},{1,1},{1,2},{2,2}});
        System.out.println(answer);
    }

}
