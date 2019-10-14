package solved;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LengthOfLongestSubstring {

    public int lengthOfLongestSubstring3(String s) {

        char[] cArr = s.toCharArray();
        int runningSum = 0, max = runningSum;
        int[] exists = new int[128];
        int i = 0;

        while (i < cArr.length) {
            if (exists[cArr[i]] == 0) {
                runningSum++;
                exists[cArr[i]] = i+1; // Account for edge case, will subtract back later
            } else {
                // Reset
                // Update max if need be
                if (runningSum > max) {
                    max = runningSum;
                }
                // Reset i to the first duplicate's position
                i = exists[cArr[i]] - 1;
                // Reset data
                exists = new int[128];
                runningSum = 0;
            }
            i++;
        }

        return runningSum > max ? runningSum : max;
    }

    public int lengthOfLongestSubstring(String s) {

        int runningMax = 0, max = 0;

        int[] positionMap = new int[512];

        int i = 0;

        while (i < s.length()) {


            if (positionMap[s.charAt(i)] == 0) {
                // not a duplicate
                runningMax++;
                positionMap[s.charAt(i)] = i + 1; // Store the original position + 1
                i++;
            } else {
                // is a duplicate
                max = Math.max(max, runningMax);
                i = positionMap[s.charAt(i)]; // Reset to the position after the duplicate

                runningMax = 0;
                positionMap = new int[512];
            }

        }

        return Math.max(max, runningMax);
    }

    public int lengthOfLongestSubstring2(String s) {

        if (s.length() == 0)  return 0;

        int answer = 1;
        HashMap<Character, Integer> hm;

        for (int i = 0; i < s.length(); i++) {

            hm = new HashMap<>();
            int counter = 0;
            for (int j = i; j < s.length(); j++) {
                if (!hm.containsKey(s.charAt(j))) {
                    hm.put(s.charAt(j), 1);
                    counter++;
                } else {
                    break;
                }
            }

            answer = Math.max(counter, answer);

        }

        return answer;
    }

    @Test
    public void tests() {

        int result = lengthOfLongestSubstring3("abcabcbb");
        System.out.println(result);
        assert(result == 3);

        result = lengthOfLongestSubstring3("bbbbb");
        System.out.println(result);
        assert(result == 1);

        result = lengthOfLongestSubstring3("dvxdf");
        System.out.println(result);
        assert(result == 4);

        result = lengthOfLongestSubstring3("");
        System.out.println(result);
        assert(result == 0);

    }

}
