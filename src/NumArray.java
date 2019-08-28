import java.util.Arrays;

/**
 */
public class NumArray {
    int[] sums;
    
    public static void main(String args[]) {
    	int[] arr = new int[] {1,2,3,2,1};
    	NumArray na = new NumArray(arr);
    }
    
    /**
     * The way sum array work is by creating a sum array.
     * sum[i] is the sum from 0 to i. So sum[j + 1] - sums[i],
     * is equivalent to sum from 0 to j+1 - sum from 0 to i.
     * Example: i = 2, j = 3
     * arr[0] + arr[1] + arr[2] + arr[3] = 8
     * arr[0] + arr[1] = 3
     * @param nums
     */
    public NumArray(int[] nums) {
        sums = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(sums));
    }

    public int sumRange(int i, int j) {
        return sums[j + 1] - sums[i];
    }
}
