package unsolved;

import java.util.*;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {

        char[] cArr = s.toCharArray();

        StringBuilder res = new StringBuilder();

        Stack<Character> charStack = new Stack<>();

        for (int i = 0; i < cArr.length; i++) {

            if (charStack.peek() != cArr[i]) {
                charStack.push(cArr[i]);
            } else if (charStack.peek() == cArr[i]) {
                charStack.pop();

            }
        }


        return "";
    }

}
