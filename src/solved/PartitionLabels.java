package solved;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * https://leetcode.com/problems/partition-labels/
 */
public class PartitionLabels {

    public List<Integer> partitionLabels2(String S) {

        char[] cArr = S.toCharArray();
        int[] boundaryMap = new int[26];

        for (int i = 0; i < cArr.length; i++) {
            boundaryMap[cArr[i] - 'a'] = i;
        }

        List<Integer> answers = new ArrayList<>();
        int beg = 0;
        while (beg < cArr.length) {
            int end = boundaryMap[cArr[beg] - 'a'];
            int partition = 0;
            while (beg < end) {
                if (boundaryMap[cArr[beg] - 'a'] > end) {
                    end = boundaryMap[cArr[beg] - 'a'];
                }
                partition++;
                beg++;
            }
            answers.add(partition+1);
            beg++;
        }
        return answers;
    }

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

//        charToBoundaries.forEach((k, v) -> {
//            System.out.println(k + " -> " + Arrays.toString(v));
//        });

        Map<Integer, Integer> boundaries = new HashMap<>();

        for (int[] boundary : charToBoundaries.values()) {

            int end = boundaries.getOrDefault(boundary[0], -1);

            if (end < 0) {
                boundaries.put(boundary[0], boundary[1]);
            }

        }

        List<int[]> mergedBoundaries = new ArrayList<>();

        int beg = 0;
        int end;

        while(beg < cArr.length) {

            end = boundaries.get(beg);

            int[] partition = new int[]{beg, -1};

            while (beg < end) {
                if (boundaries.get(beg) != null) {
                    end = Math.max(boundaries.get(beg), end);
                }
                beg++;
            }

            partition[1] = end;
            mergedBoundaries.add(partition);

            beg = end + 1;

        }

//        for (int[] partition : mergedBoundaries) {
//            System.out.println(Arrays.toString(partition));
//        }

        List<Integer> answer = new ArrayList<>();

        for (int[] partition : mergedBoundaries) {
            answer.add(partition[1] - partition[0] + 1);
        }

        return answer;
    }

    @Test
    public void test() {
        System.out.println(partitionLabels2("abcabdeffed"));
        System.out.println(partitionLabels2("abcabdeffedx"));
    }


}
