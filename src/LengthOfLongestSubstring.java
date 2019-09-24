import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class LengthOfLongestSubstring {

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

        int result = lengthOfLongestSubstring2("abcabcbb");
        System.out.println(result);
        assert(result == 3);

        result = lengthOfLongestSubstring2("bbbbb");
        System.out.println(result);
        assert(result == 1);

        result = lengthOfLongestSubstring2("dvdf");
        System.out.println(result);
        assert(result == 3);

    }

}
