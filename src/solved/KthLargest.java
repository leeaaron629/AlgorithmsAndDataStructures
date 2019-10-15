package solved;

import java.util.PriorityQueue;

public class KthLargest {
	
	int capacity;
	PriorityQueue<Integer> minQ;
	
	/**
	 * This can be solved with a min priority queue that holds
	 * a certain capacity (k). Peek will return the kth element.
	 * If a new element that is added and is larger than the 
	 * kth element add it to the back of the queue.
	 * @param k
	 * @param nums
	 */
    public KthLargest(int k, int[] nums) {
        this.capacity = k;
        minQ = new PriorityQueue<>();
        
        for (int n : nums) add(n);
    }
    
    public int add(int val) {
        
    	if (minQ.size() < capacity) {
    		minQ.offer(val);
    	} else {
    		if (val > minQ.peek()) {
    			minQ.poll();
    			minQ.offer(val);
    		}
    	}
    	return minQ.peek();
    }
}
