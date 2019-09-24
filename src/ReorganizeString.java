import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ReorganizeString {


//    public String reorganizeString(String S) {
//
//        Map<Character, Integer> charFrequencies = new HashMap<>();
//
//        for (char c : S.toCharArray()) {
//            charFrequencies.put(c, charFrequencies.getOrDefault(c, 0) + 1);
//        }
//
//        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);
//
//        for (Map.Entry<Character, Integer> entry : charFrequencies.entrySet()) {
//            pq.add(new Pair(entry.getKey(), entry.getValue()));
//        }
//
//        if (pq.isEmpty()) return "";
//
//        int maxCount = pq.peek().count;
//
//        // For there to be a solution maxCount cannot exceed more than half of the total + 1.
//        if (maxCount > (S.length() + 1) / 2) {
//            return "";
//        }
//
//        StringBuilder answer = new StringBuilder();
//
//        while (!pq.isEmpty()) {
//
//            Pair first = pq.poll();
//
//            while (first.count > 0) {
//
//                answer.append(first.letter);
//                first.count--;
//
//                if (pq.peek() != null) {
//
//                    Pair second = pq.poll();
//
//                    if (second.count > 0) {
//                        answer.append(second.letter);
//                        second.count--;
//                    }
//
//                    if (second.count > 0) {
//                        pq.offer(second);
//                    }
//
//                }
//
//            }
//
//        }
//
//        printFrequencies(S);
//        printFrequencies(answer.toString());
//
//        return answer.toString();
//
//    }

    public String reorganizeString(String S) {
        int N = S.length();
        int[] counts = new int[26];
        for (char c: S.toCharArray()) counts[c-'a'] += 100;
        for (int i = 0; i < 26; ++i) counts[i] += i;
        //Encoded counts[i] = 100*(actual count) + (i)
        Arrays.sort(counts);

        char[] ans = new char[N];
        int t = 1;
        for (int code: counts) {
            int ct = code / 100;
            char ch = (char) ('a' + (code % 100));
            if (ct > (N+1) / 2) return "";
            for (int i = 0; i < ct; ++i) {
                if (t >= N) t = 0;
                ans[t] = ch;
                t += 2;
            }
        }

        return String.valueOf(ans);
    }

    class Pair {

        private char letter;
        private int count;

        public Pair(char letter, int count) {
            this.letter = letter;
            this.count = count;
        }

    }

    private void printFrequencies(String s) {

        Map<Character, Integer> frequencies = new HashMap<>();

        for (char c : s.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        System.out.println(frequencies);
    }

    public static void main(String[] args) {

        String answer;

//        answer = new ReorganizeString().reorganizeString("eyunyjremkzgblsfsrtmomdydeshldqxmwikjtfnupbcwhfcipzvuciehpelkmtnuttectqzeaeswritrfrrchkuqswcgsuoshkxvthzjjcxfgtcezgxblhkdgubhempnaoossyypewihccbzbkdjjxbqvnzqycdlwmrjjfykuitkzfhchuambdagictmjatwnttpcenraowhzmlgfvxcyamfonupldrrnvnebtzqxdjongapktgmiytqiqseizonpitnknfzwuendmvxhbsobidnqwhplolahpijafzjistxtnfdcxxkrruxbphmjqzanebmioyqmyqwayunokwbvmckgpmcxoeqtadafkbxnjvknhmjtlzkiqxirobjcpsikcyhvmoehsompftkxxfkmneqtpjntrcatlwgvgmrrvaraytvhpbidajyqolnzqchxwvpdvchgfnhohypbkzohgdchxspsylhxaefbpzaomwgxghpniy");
//        System.out.println(answer);
        answer = new ReorganizeString().reorganizeString("aaaabbbbccccdddd");
        System.out.println(answer);

    }

}
