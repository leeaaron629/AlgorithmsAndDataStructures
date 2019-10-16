package unsolved;

import org.junit.jupiter.api.Test;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        char[] cArr = s.toCharArray();
        String longest = "";

        for (int i = 0; i < cArr.length; i++) {

            String evenLengthPalindrome = palindrome(cArr, i, i + 1);
            String oddLengthPalindrome = palindrome(cArr, i, i);

            if (evenLengthPalindrome.length() > longest.length()) {
                longest = evenLengthPalindrome;
            } else if (oddLengthPalindrome.length() > longest.length()) {
                longest = oddLengthPalindrome;
            }

        }

        return longest;
    }

    String palindrome(char[] cArr, int left, int right) {

        if (left < 0) left = 0;
        if (right > cArr.length - 1) right = cArr.length - 1;
        if (cArr[left] != cArr[right]) return new String(new char[]{cArr[left]});

        while (left - 1 >= 0 && right + 1 < cArr.length) {
            if (cArr[left - 1] != cArr[right + 1]) {
                break;
            }
            right++;
            left--;
        }

        char[] palindrome = new char[right - left + 1];
        int i = 0;
        while (left <= right) {
            palindrome[i++] = cArr[left++];
        }

        return new String(palindrome);
    }

    @Test
    public void tests() {
        String test1 = "cbbd";
        int i1 = test1.indexOf("c");
        System.out.println(palindrome(test1.toCharArray(), i1, i1));
        System.out.println(palindrome(test1.toCharArray(), i1, i1 + 1));
    }

}
