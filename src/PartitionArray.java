import java.util.Arrays;

public class PartitionArray {
	
	public static void main(String[] args) {
		PartitionArray driver = new PartitionArray();
		int[] A = {5,0,3,8,6};
		System.out.println(driver.partitionDisjoint(A));
		
	}
	
    public int partitionDisjoint(int[] A) {
    	
    	
    	int[] maxLeftArr = new int[A.length];
    	int[] minRightArr = new int[A.length];
    	
    	int max = A[0];
    	for (int i = 0; i < maxLeftArr.length; i++) {
    		max = Math.max(max, A[i]);
    		maxLeftArr[i] = max;
    	}
    	
    	int min = A[A.length-1];
    	for (int i = A.length-1; i >= 0; i--) {
    		min = Math.min(min, A[i]);
    		minRightArr[i] = min;
    	}
    	System.out.println(Arrays.toString(minRightArr));
        System.out.println(Arrays.toString(maxLeftArr));
    	for (int i = 1; i < A.length; i++) {
    		if (maxLeftArr[i-1] <= minRightArr[i]) return i;
    	}
    	
        throw null;
    }
    
    private int findMax(int[] arr) {
    	return 0;
    }
    
    private int findMin(int[] arr) {
    	return 0;
    }
    
}
