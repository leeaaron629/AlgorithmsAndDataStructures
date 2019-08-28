package algorithms;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
	
	public static void main(String[] args) {
		PowerSet ps = new PowerSet();
	}
	
	
    public List<List<Integer>> subsets(int[] nums) {
        
    	List<List<Integer>> powerSet = new ArrayList<>();
    	powerSet.add(new ArrayList<>());
    	
    	buildPowerSet(nums, 0, powerSet);
    	
    	return powerSet;
    }
    
    public void buildPowerSet(int[] nums, int i, List<List<Integer>> powerSet) {
    	
    	if (i >= nums.length) return;
    	
    	List<List<Integer>> newSet = new ArrayList<>();
    	
    	for (List<Integer> set : powerSet) {
    		List<Integer> currentSet = new ArrayList<>(set);
    		currentSet.add(nums[i]);
    		newSet.add(currentSet);
    	}
    	
    	powerSet.addAll(newSet);
    	
    	buildPowerSet(nums, i+1, powerSet);
    }
}
