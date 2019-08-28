import org.junit.jupiter.api.Test;

public class DecodeWays {

    @Test
    public void testNumDecodings() {
        System.out.println("17 -> " + numDecodings("17"));
    }

    public int numDecodings(String s) {

        int[] answers = new int[s.length() + 1];

        if (s.length() == 0 ) {
            return 0;
        }

        if (s.length() == 1 || s.charAt(0) == '0') {
            if (s.charAt(0) == '0') {
                return 0;
            } else {
                return 1;
            }
        }

        answers[0] = 1;
        answers[1] = 1;

        if (s.length() < 2) {
            return answers[s.length()];
        }

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2') {

                if (s.charAt(i) < '7') {
                    answers[i+1] += answers[i-1];
                }

            }

            if (s.charAt(i) != '0') {
                answers[i+1] += answers[i];
            }

        }

        return answers[s.length()];

    }

    /*
        Edge Cases:

        1. "xxx00xxx" -> 0
        2. "01xxxxxx" -> 0
        3. "17xxxxxx" -> 2
        4. "27xxxxxx" -> 1
     */

}
