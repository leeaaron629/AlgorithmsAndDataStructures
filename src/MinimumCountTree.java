import java.util.ArrayList;
import java.util.List;

public class MinimumCountTree {

//    int sum = 0;

    public static void main(String[] args) {
        MinimumCountTree driver = new MinimumCountTree();

        int[] arr = new int[]{1,2,3,4};
        int answer = driver.mctFromLeafValues(arr);
        System.out.println(answer);

    }

    public int mctFromLeafValues(int[] arr) {

        int answer = 0;

        List<Integer> arrList = new ArrayList<>();
        for (int i : arr) {
            arrList.add(i);
        }

        while (arrList.size() > 1) {

            int bestMultiple = Integer.MAX_VALUE;
            int bestInd = -1;

            for (int i = 0; i < arrList.size() - 1; i++) {

                int a = arrList.get(i);
                int b = arrList.get(i+1);

                if ( a*b < bestMultiple) {
                    bestInd = i;
                    bestMultiple = a*b;
                }

            }

            answer += bestMultiple;

            if (arrList.get(bestInd) > arrList.get(bestInd+1)) {
                arrList.remove(bestInd+1);
            } else {
                arrList.remove(bestInd);
            }

        }

        return answer;

    }

}
