import java.util.*;

public class RemoveInvalidParentheses {

    public static void main(String[] args) {
        RemoveInvalidParentheses driver = new RemoveInvalidParentheses();

        String str1 = "()())()";
        System.out.println(str1);
//        System.out.println(driver.removeInvalidParentheses(str1));

        String str2 = ")o(v(";
        System.out.println(str2);
//        System.out.println(driver.removeInvalidParentheses(str2));

        String str3 = "(r(()()(";
        System.out.println(str3);
        System.out.println(driver.removeInvalidParentheses(str3));
        System.out.println("Answer: " + "[r()(), r(()), (r)(), (r())]");

    }

    public List<String> removeInvalidParentheses(String s) {

        int parentheses = 0;

        List<Character> charList = new ArrayList<>();

        List<Set<String>> subStrFixList = new ArrayList<>();

        for (char c : s.toCharArray()) {

            if (c == '(') {
                parentheses++;
            }

            if (c == ')') {
                parentheses--;
            }

            if (parentheses < 0) {

                charList.add(c);
                // Return a list of possible substr fixes
                // If not possible return an empty list
                Set<String> subStrFixes = removeParentheses(charList, ')');

                if (!subStrFixes.isEmpty()) {
                    subStrFixList.add(subStrFixes);
                }

                parentheses = 0;
                charList.clear();

            } else {
                charList.add(c);
            }

        }

        System.out.println("Initial SubStrFixList: " + subStrFixList);

        if (!charList.isEmpty()) {

            parentheses = 0;

            List<Character> reversedCharList = new ArrayList<>();

            for (int i = charList.size() - 1; i >= 0; i--) {
                reversedCharList.add(charList.get(i));
            }

            charList = new ArrayList<>();
            Stack<Set<String>> subStrFixStack = new Stack<>();

            System.out.println("Reversed: " + reversedCharList);

            for (char c : reversedCharList) {

                if (c == ')') {
                    parentheses++;
                }

                if (c == '(') {
                    parentheses--;
                }

                if (parentheses < 0) {

                    charList.add(c);
                    // Return a list of possible substr fixes
                    // If not possible return an empty list
                    Set<String> subStrFixes = removeParentheses(charList, '(');

                    if (!subStrFixes.isEmpty()) {
                        subStrFixStack.push(subStrFixes);
                    }

                    parentheses = 0;
                    charList.clear();

                } else {
                    charList.add(c);
                }

            }

            if (!charList.isEmpty()) {

                StringBuilder sb = new StringBuilder();

                for (char c : charList) {
                    sb.append(c);
                }

                Set<String> correctOnes = new HashSet<>();
                correctOnes.add(sb.reverse().toString());

                subStrFixStack.push(correctOnes);
            }

            System.out.println("Final SubStrFixStack: "  + subStrFixStack);

            while (!subStrFixStack.isEmpty()) {
                subStrFixList.add(subStrFixStack.pop());
            }

        }

        System.out.println("Final SubStrFixList: " + subStrFixList);

        List<String> answers = new ArrayList<>();

        answers.add("");

        for (Set<String> subStrFixes : subStrFixList) {

            List<String> newAnswers = new ArrayList<>();

            for (String subStr : subStrFixes) {
                for (String answer : answers) {
                    newAnswers.add(answer + subStr);
                }
            }

            answers = newAnswers;
        }

        return answers;

    }

    public Set<String> removeParentheses(List<Character> charList, char parenthesis) {

        LinkedList<Integer> toRemove = new LinkedList<>();

        for (int i = 0; i < charList.size(); i++) {
            if (charList.get(i) == parenthesis) {
                toRemove.add(i);
            }
        }

        Set<String> substrFixes = new HashSet<>();

        while (!toRemove.isEmpty()) {
            // Clone charList, but skip the toRemove Index
            int toRemoveIndex = toRemove.poll();

            StringBuilder sb = new StringBuilder();

            if (parenthesis == ')') {
                for (int i = 0; i < charList.size(); i++) {
                    if (toRemoveIndex != i) {
                        sb.append(charList.get(i));
                    }
                }
            } else {
                for (int i = charList.size() - 1; i >= 0; i--) {
                    if (toRemoveIndex != i) {
                        sb.append(charList.get(i));
                    }
                }
            }

            substrFixes.add(sb.toString());
        }

        return substrFixes;

    }


}
