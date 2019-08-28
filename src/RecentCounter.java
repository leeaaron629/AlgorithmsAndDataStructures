import java.util.PriorityQueue;

public class RecentCounter {
	
	PriorityQueue<Integer> minQ;
	
    public RecentCounter() {
        minQ = new PriorityQueue<>();
    }
    
    public int ping(int t) {
    	
    	while(!minQ.isEmpty() && minQ.peek() < t - 3000) {
    		minQ.poll();
    	}
    	
    	minQ.offer(t);
    	
        return minQ.size();
    }
}
