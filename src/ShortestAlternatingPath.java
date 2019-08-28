import java.util.LinkedList;
import java.util.Queue;

public class ShortestAlternatingPath {

    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {

        boolean[][] red_edge_map = new boolean[n][n];

        for (int[] red_edge : red_edges) {
            red_edge_map[red_edge[0]][red_edge[1]] = true;
        }

        boolean[][] blue_edge_map = new boolean[n][n];

        for (int[] blue_edge : blue_edges) {
            blue_edge_map[blue_edge[0]][blue_edge[1]] = true;
        }

        int[] answers = new int[n];

        for (int i = 1; i < answers.length; i++) {

            answers[i] = Math.min(
                    shortestPath(0, i, red_edge_map, blue_edge_map),
                    shortestPath(0, i, blue_edge_map, red_edge_map));

        }

        return answers;
    }

    private int shortestPath(int A, int B, boolean[][] first_edge_map, boolean[][] second_edge_map) {

        if (A == B) {
            return 0;
        }

        Queue<int[]> nodeQueue = new LinkedList<>();
        boolean[] visited = new boolean[first_edge_map.length];

        visited[0] = true;
        nodeQueue.offer(new int[]{0, 0});

        while (!nodeQueue.isEmpty()) {

            int[] current = nodeQueue.poll();

            if (current[0] == B) {
                return current[1];
            }

            if (current[1] % 2 == 0) {
                for (int next = 0; next < first_edge_map[current[0]].length; next++) {
                    if (first_edge_map[current[0]][next] && !visited[next]) {
                        visited[next] = true;
                        nodeQueue.offer(new int[]{next, current[1] + 1});
                    }
                }
            } else {
                for (int next = 0; next < second_edge_map[current[0]].length; next++) {
                    if (second_edge_map[current[0]][next] && !visited[next]) {
                        visited[next] = true;
                        nodeQueue.offer(new int[]{next, current[1] + 1});
                    }
                }
            }

        }

        return -1;
    }

}
