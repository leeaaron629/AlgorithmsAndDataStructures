import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountBits {
	public static void main(String[] args) {
		CountBits driver = new CountBits();
		driver.countBits(35);
		
		List<Integer> intList = Arrays.asList(1,2,3);
		List<Integer> intListTwo = Arrays.asList(4,5,6);
		intList.addAll(intListTwo);
		
		for (Integer i : intList) System.out.println(i);
	}
	
    public int[] countBits(int num) {
    	
    	int[] answers = new int[num];
    	
    	answers[0] = 0;
    	
    	int index = 1;
    	
    	for (int i = 1; index < num; i <<= 1)	 {
    		int j = 0;
    		while (j < i  && index < num) {
    			answers[index++] = answers[j++] + 1;
    		}
    	}
    	
    	return answers;
        
    }
}
