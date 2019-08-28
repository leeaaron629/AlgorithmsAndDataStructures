import java.util.ArrayList;
import java.util.List;

public class ReOrderLogFiles {
	
	public static void main(String[] args) {
		String s1 = "off key dog";
		String s2 = "act zoo";
		
		System.out.println(s1.compareToIgnoreCase(s2));
	}
	
    public String[] reorderLogFiles(String[] logs) {
    	List<String> numericLogs = new ArrayList<>();
    	List<String> letterLogs = new ArrayList<>();
    	// Iterate through the array to and add it to either numericLogs or letterLogs
    	for (String log : logs) {
    		// Split the data
    		String[] data = log.split(" ");
    		// Check if the 2nd element is a number or characters
    		if (data[1].charAt(0) >= '0' && data[1].charAt(0) <= '9') {
    			// This is a digit log - goes in the end
    			numericLogs.add(log);
    		} else {
    			// This is a letter log - insert in order
    			int location = -1;
    			for (int i = 0; i < numericLogs.size(); i++) {
    				String current = numericLogs.get(i);
    				// Split the current first
    				int firstSpace = current.indexOf(' ');
    				String currentIdentifier = current.substring(0, firstSpace);
    				String currentData = current.substring(firstSpace+1);
    				// Find the correct location

    				String logData = log.substring(data[0].length()+1);
    				String logIdentifier = log.substring(0, data[0].length());
    				
    				System.out.println(logIdentifier + " :: " + logData);
    				System.out.println(currentIdentifier + " :: " + currentData);
    				if (logData.compareToIgnoreCase(currentData) < 0) {
    					// End here - this is the correct location
    					location = i;
    					break;
    				} else if (logData.compareToIgnoreCase(currentData) == 0){
    					if (logIdentifier.compareToIgnoreCase(currentIdentifier) <= 0) {
    						location = i;
    						break;
    					}
    				} 
    			}
    			// Add it to the end of the list
    			if (location == -1)
    				letterLogs.add(log);
    			else
    				letterLogs.add(location, log);
    		}
    	}
    	
    	letterLogs.addAll(numericLogs);
    	
    	String[] answer = new String[letterLogs.size()];
    	answer = letterLogs.toArray(answer);
    	
    	return answer;
    }
}
