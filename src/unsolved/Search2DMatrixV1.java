package unsolved;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/search-a-2d-matrix/
 */
public class Search2DMatrixV1 {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) return false;

        int X = matrix[0].length; int Y = matrix.length;
        int beg = 0, end = (X * Y) - 1;

        while (beg <= end) {

            int mid = (beg + end) / 2;

            int i = mid / X;
            int j = mid % X;

            if (target == matrix[i][j]) {
                return true;
            } else if (target < matrix[i][j]) {
                end = mid - 1;
            } else {
                beg = mid + 1;
            }

        }

        return false;
    }

    @Test
    public void tests() {
        int[][] matrix = new int[][]{
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}
        };

        assert searchMatrix(matrix, 16);

        matrix = new int[][]{
                {1,3}
        };

        assert searchMatrix(matrix, 3);
    }


}
