package datastructures;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LRUCache {
	
    private int capacity;
    private int counter;
    private HashMap<Integer, Integer> cache;
    private TreeMap<Integer, Integer> timeRecords;

    public LRUCache(int n) {
        capacity = n;
        counter = 0;
        cache = new HashMap<>();
        timeRecords = new TreeMap<>((x, y) -> x - y);
    }
    
    public int get(int key) {
    	counter++;
    	
    	System.out.print("Getting " + key + ": ");
    	if (cache.get(key) == null) {
    		System.out.println("Not Found");
    		return -1;
    	} else {	
    		System.out.println(cache.get(key));
    		
    		// Remove the original entry
    		int keyToRemove = 0;
    		for (Map.Entry<Integer, Integer> entry : timeRecords.entrySet()) {
    			if (key == entry.getValue()) {
    				keyToRemove = entry.getKey();
    			}
    		}
    		timeRecords.remove(keyToRemove);
    		// Adding new time record for entry 
    		timeRecords.put(counter, key);
    	
    		return cache.get(key);
    	}
    }
    
    public void put(int key, int value) {
    	counter++;
    	
    	if (cache.containsKey(key)) {
    		// Replace entry old values
    		System.out.print("Putting { " + key + ", " + value + " }");
    		cache.put(key, value);
    		// Update the time records
    		int keyToRemove = 0;
    		for (Map.Entry<Integer, Integer> entry : timeRecords.entrySet()) {
    			if (key == entry.getValue()) {
    				keyToRemove = entry.getKey();
    			}
    		}
    		timeRecords.remove(keyToRemove);
    		
    		// Adding new time record for entry
    		timeRecords.put(counter, key);
    	} else if (cache.size() < capacity) {
    		// Put entry
    		System.out.print("Putting { " + key + ", " + value + " }");
    		cache.put(key, value);
    		// Add time record
    		timeRecords.put(counter, key);
    	} else {
    		// Evict Least Recently Used Entry
    		// Get earliest entry 
    		Map.Entry<Integer, Integer> entry = timeRecords.pollFirstEntry();
    		
    		// Remove earliest entry
    		cache.remove(entry.getValue());
    		
    		// Putting new entry
    		System.out.print("Putting { " + key + ", " + value + " }");
    		cache.put(key, value);
    		
    		// Adding new time record for entry
    		timeRecords.put(counter, key);
    		cache.put(key, value);    		
    	}
    }
}
