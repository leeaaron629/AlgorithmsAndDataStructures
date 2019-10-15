package unsolved;

import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/search-a-2d-matrix-ii/
 */
public class Search2DMatrixV2 {

    public boolean searchMatrix(int[][] matrix, int target) {

        if (matrix.length == 0) return false;

        for (int[] ints : matrix) {
            if (binarySearch(ints, target)) return true;
        }

        return false;
    }

    private boolean binarySearch(int[] arr, int target) {

        int beg = 0; int end = arr.length - 1;

        while (beg <= end) {

            int mid = (beg + end) / 2;

            if (target == arr[mid]) {
                return true;
            } else if (target < arr[mid]) {
                end = mid - 1;
            } else {
                beg = mid + 1;
            }

        }

        return false;
    }
    
    @Test
    public void tests() {
        int[][] matrix = new int[][] {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };

        assert searchMatrix(matrix, 14);
        assert searchMatrix(matrix, 5);
    }
}
