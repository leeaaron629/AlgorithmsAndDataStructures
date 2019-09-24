import java.util.HashMap;
import java.util.Map;

public class MaxPoints {

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;
    private static final int FORWARD_SLASH = 2;
    private static final int BACKWARD_SLASH = 3;

    public int maxPoints(int[][] points) {

        if (points.length == 0) {
            return 0;
        }

        Map<String, Integer> answers = new HashMap<>();

        int answer = 0;

        for (int[] point : points) {

            point[0] = Math.abs(point[0]);
            point[1] = Math.abs(point[1]);

            String pointKey = "" + point[0] + point[1];
            answers.put(pointKey, answers.getOrDefault(pointKey, 0) + 1);

        }

        for (int[] point : points) {

            // Get Horizontal
            answer = Math.max(getHorizontalPoints(point, answers), answer);

            // Get Vertical
            answer = Math.max(getVerticalPoints(point, answers), answer);

            // Get Forward Slash
            answer = Math.max(getForwardSlash(point, answers), answer);

            // Get Backward Slash
            answer = Math.max(getBackwardSlash(point, answers), answer);

        }

        return answer;
    }

    private int getVerticalPoints(int[] point, Map<String, Integer> answers) {

        int x = point[0];
        int y = point[1];

        int up = 0;

        while(answers.containsKey("" + ++x + y)) {
            up += answers.get("" + x + y);
        }

        x = point[0];

        int down = 0;

        while(answers.containsKey("" + --x + y)) {
            down += answers.get("" + x + y);
        }

        return up + down + answers.get("" + point[0] + point[1]);

    }

    private int getHorizontalPoints(int[] point, Map<String, Integer> answers) {

        int x = point[0];
        int y = point[1];

        int right = 0;

        while(answers.containsKey("" + x + ++y)) {
            right += answers.get("" + x + y);
        }

        y = point[1];

        int left = 0;

        while (answers.containsKey("" + x + --y)) {
            left += answers.get("" + x + y);
        }

        return left + right + answers.get("" + point[0] + point[1]);

    }


    private int getForwardSlash(int[] point, Map<String, Integer> answers) {

        int x = point[0];
        int y = point[1];

        int topRight = 0;

        while(answers.containsKey("" + ++x + ++y)) {
            topRight += answers.get("" + x + y);
        }

        x = point[0];
        y = point[1];

        int downLeft = 0;

        while(answers.containsKey("" + --x + --y)) {
            downLeft += answers.get("" + x + y);
        }

        return downLeft + topRight + answers.get("" + point[0] + point[1]);
    }

    private int getBackwardSlash(int[] point, Map<String, Integer> answers) {

        int x = point[0];
        int y = point[1];

        int upLeft = 0;

        while(answers.containsKey("" + ++x + --y)) {
            upLeft++;
        }

        x = point[0];
        y = point[1];

        int downRight = 0;

        while(answers.containsKey("" + --x + ++y)) {
            downRight += answers.get("" + x + y);
        }

        return downRight + upLeft + answers.get("" + point[0] + point[1]);

    }

    public static void main(String[] args) {
        Integer answer = new MaxPoints().maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}});
        System.out.println(answer);
        answer = new MaxPoints().maxPoints(new int[][]{{0, 0}, {0, 0}});
        System.out.println(answer);
        answer = new MaxPoints().maxPoints(new int[][]{{0,0},{-1,-1},{2,2}});
        System.out.println(answer);
    }



}
