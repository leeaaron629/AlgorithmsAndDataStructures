import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LRUCache {
	
    private int capacity;
//    private int counter;
    private LinkedHashMap<Integer, Integer> cache;
//    private TreeMap<Integer, Integer> timeRecords;

    public LRUCache(int n) {
        capacity = n;
//        counter = 0;
        cache = new LinkedHashMap<>();
//        timeRecords = new TreeMap<>((x, y) -> x - y);
    }
    
    public int get(int key) {
    	
    	System.out.print("Getting " + key + ": ");
    	if (cache.get(key) == null) {
    		System.out.println("Not Found");
    		return -1;
    	} else {
    		int keyValue = cache.get(key);
    		// Remove the original entry
    		cache.remove(key);
    		// Adding new time record for entry 
    		cache.put(key, keyValue);
    		return keyValue;
    	}
    }
    
    public void put(int key, int value) {
    	
    	if (cache.containsKey(key)) {
    		// Remove old key value
    		cache.remove(key);
    		// Replace entry old values
    		cache.put(key, value);
    	} else if (cache.size() < capacity) {
    		// Put entry
    		cache.put(key, value);
    	} else {
    		// Evict Least Recently Used Entry
    		// Get earliest entry 
    		Entry<Integer, Integer> firstEntry = null;
    		for (Entry<Integer, Integer> entry : cache.entrySet()) {
    			firstEntry = entry;
    			break;
    		}
    		// Remove earliest entry
    		if (cache != null ) cache.remove(firstEntry.getKey());

    		// Putting new entry
    		if (cache.size() < capacity) cache.put(key, value);
    	}
    }
}