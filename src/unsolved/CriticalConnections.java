package unsolved;

import java.util.*;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 */
public class CriticalConnections {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        boolean[][] adjMatrix = new boolean[n][n];
        for (List<Integer> connection : connections) {
            adjMatrix[connection.get(0)][connection.get(1)] = true;
            adjMatrix[connection.get(1)][connection.get(0)] = true;
        }

        List<List<Integer>> criticalConnections = new ArrayList<>();

        for (List<Integer> connection : connections) {
            adjMatrix[connection.get(0)][connection.get(1)] = false;
            adjMatrix[connection.get(1)][connection.get(0)] = false;

            if (dfs(adjMatrix) < n) {
                criticalConnections.add(connection);
            }

            adjMatrix[connection.get(0)][connection.get(1)] = true;
            adjMatrix[connection.get(1)][connection.get(0)] = true;
        }

        return criticalConnections;

    }

    private int dfs(boolean[][] adjMatrix) {

        int numOfNodeVisited = 0;

        Set<Integer> nodeVisited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        nodeVisited.add(0);
        numOfNodeVisited++;

        while (!stack.isEmpty()) {
            Integer node = stack.pop();
            // Add children
            for (int i = 0; i < adjMatrix[node].length; i++) {
                if (adjMatrix[node][i] && !nodeVisited.contains(i)) {
                    stack.push(i);
                    numOfNodeVisited++;
                    nodeVisited.add(i);
                }
            }
        }

        return numOfNodeVisited;
    }

}
