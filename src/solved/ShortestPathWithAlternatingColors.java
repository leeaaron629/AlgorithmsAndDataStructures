package solved;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathWithAlternatingColors {

    public static void main(String[] args) {
        int[] answers;
        ShortestPathWithAlternatingColors driver = new ShortestPathWithAlternatingColors();

        answers = driver.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{});
        System.out.println(Arrays.toString(answers));
        answers = driver.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{2, 1}});
        System.out.println(Arrays.toString(answers));
        answers = driver.shortestAlternatingPaths(3, new int[][]{{1, 0}}, new int[][]{{2, 1}});
        System.out.println(Arrays.toString(answers));
        answers = driver.shortestAlternatingPaths(3, new int[][]{{0, 1}}, new int[][]{{1, 2}});
        System.out.println(Arrays.toString(answers));
        answers = driver.shortestAlternatingPaths(4, new int[][]{{0, 1}, {0, 2}}, new int[][]{{1, 0}});
        System.out.println(Arrays.toString(answers));
        answers = driver.shortestAlternatingPaths(5, new int[][]{
                {0,1}, {1,2}, {2,3}, {3,4}
        }, new int[][]{
                {1,2}, {2,3}, {3,1}
        });
        System.out.println(Arrays.toString(answers));
    }

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        boolean[][] red_edge_map = new boolean[n][n];

        for (int[] red_edge : red_edges) {
            red_edge_map[red_edge[0]][red_edge[1]] = true;
        }

        boolean[][] blue_edge_map = new boolean[n][n];

        for (int[] blue_edge : blue_edges) {
            blue_edge_map[blue_edge[0]][blue_edge[1]] = true;
        }

        int[] shortestPathsWithRedFirst = shortestPath(n, blue_edge_map, red_edge_map);
        int[] shortestPathsWithBlueFirst = shortestPath(n, red_edge_map, blue_edge_map);

        int[] answers = new int[n];

        for (int i = 0; i < answers.length; i++) {

            answers[i] = Math.min(shortestPathsWithBlueFirst[i], shortestPathsWithRedFirst[i]);

            if (answers[i] == Integer.MAX_VALUE) {
                answers[i] = -1;
            }
        }

        return answers;
    }

    private int[] shortestPath(int n, boolean[][] first_edge_map, boolean[][] second_edge_map) {

        int[] shortestPaths = new int[n];
        Arrays.fill(shortestPaths, Integer.MAX_VALUE);

        Queue<int[]> nodeQueue = new LinkedList<>();
        boolean[] visitedFirst = new boolean[n];
        boolean[] visitedSecond = new boolean[n];

        visitedFirst[0] = true;
        nodeQueue.offer(new int[]{0, 0});

        while (!nodeQueue.isEmpty()) {

            int[] current = nodeQueue.poll();

            shortestPaths[current[0]] = Math.min(shortestPaths[current[0]], current[1]);

            if (current[1] % 2 == 0) {
                for (int next = 0; next < n; next++) {
                    if (first_edge_map[current[0]][next] && !visitedFirst[next]) {
                        visitedFirst[next] = true;
                        nodeQueue.offer(new int[]{next, current[1] + 1});
                    }
                }
            } else {
                for (int next = 0; next < n; next++) {
                    if (second_edge_map[current[0]][next] && !visitedSecond[next]) {
                        visitedSecond[next] = true;
                        nodeQueue.offer(new int[]{next, current[1] + 1});
                    }
                }
            }

        }

        return shortestPaths;
    }

}
