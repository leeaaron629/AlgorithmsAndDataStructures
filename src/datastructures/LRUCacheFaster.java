package datastructures;

import java.util.HashMap;

public class LRUCacheFaster {
	
	private int capacity;
	private Node root;
	private HashMap<Integer, Integer> cache;
	
    public LRUCacheFaster(int n) {
        capacity = n;
        cache = new HashMap<>();
    }
    
    public int get(int key) {
    	
    	if (cache.get(key) == null) {
    		// Not in cache
    		return -1;
    	} else {
    		return cache.get(key);
    	}
        
    }
    
    public void put(int key, int value) {
    	
    	if (cache.containsKey(key)) {
    		// Replace entries value
    	} else if (cache.size() < capacity) {
    		// Put new entry
    		
    	} else {
    		// Evict Least Recently Used Entry and add new Entry
    	}
        
    }
}
