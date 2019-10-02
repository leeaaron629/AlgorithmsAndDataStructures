import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {

        char[] cArr = S.toCharArray();
        Map<Character, int[]> charToBoundaries = new HashMap<>();

        for (int i = 0; i < cArr.length; i++) {

            int[] boundary = charToBoundaries.getOrDefault(cArr[i], new int[]{-1, -1});

            if (boundary[0] == -1) {
                boundary[0] = i;
                boundary[1] = i;
            } else {
                boundary[1] = i;
            }

            charToBoundaries.put(cArr[i], boundary);

        }

        charToBoundaries.forEach((k, v) -> {
            System.out.println(k + " -> " + Arrays.toString(v));
        });

        int[] heatMap = new int[cArr.length];

        charToBoundaries.forEach((character, boundaries) -> {
            addToHeatMap(heatMap, boundaries);
        });

        System.out.println(Arrays.toString(heatMap));

        return Collections.emptyList();
    }

    private void addToHeatMap(int[] heatMap, int[] boundaries) {
        for (int i = boundaries[0]; i <= boundaries[1]; i++) {
            heatMap[i]++;
        }
    }

    @Test
    public void test() {
        partitionLabels("abcabdeffed");
    }


}
