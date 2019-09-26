import org.junit.jupiter.api.Test;

/**
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class FindMinInSortedRotatedArray {

    public int findMin(int[] nums) {

        int beg = 0, end = nums.length - 1;

        while (beg < end) {

            int mid = (end + beg) / 2;

            if (mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            if (mid + 1 < nums.length && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // Since it is in ascending order, the right most number should be greater than the current
            // If the right most number is less than the current than the pivot is to the right
            // If the left most number is greater than the current than the pivot is to the left
            if (nums[end] < nums[mid]) {
                // Yes go right
                beg = mid + 1;
            } else {
                // No go left
                end = mid - 1;
            }

        }

        return nums[0];
    }

    @Test
    public void test() {
        System.out.println(findMin(new int[]{11, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})); // -1
        System.out.println(findMin(new int[]{-1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -3, -2})); // -3
        System.out.println(findMin(new int[]{1, 2, 3})); // 1
        System.out.println(findMin(new int[]{1, 0})); // 0
        System.out.println(findMin(new int[]{1})); // 1
        System.out.println(findMin(new int[]{7, 8, 1, 2, 3, 4, 5, 6})); // 1
        System.out.println(findMin(new int[]{6, 7, 8, 1, 2, 3, 4, 5})); // 1
    }

}
