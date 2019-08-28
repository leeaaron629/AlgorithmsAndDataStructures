
public class NewSandBox {
	public static void main(String[] args) {
		
	}
	
    public String largestTimeFromDigits(int[] A) {
        
        int[] digitMap = new int[10];
        
        for (int i = 0; i < A.length; i++) {
            System.out.println(A[i]);
            digitMap[A[i]]++;
        }
    
        StringBuilder time = new StringBuilder();
        
        // Get Hour
        
        for (int i = 2; i >= 0; i--) {
            if (A[i] > 0) {
                A[i]--;
                
                break;
            }
        }
        
        for (int i = 4; i >= 0; i--) {
            if (A[i] > 0) {
                A[i]--;
                time.append(i);
                break;
            }
        }

        time.append(":");
        
        for (int i = 5; i >= 0; i--) {
            if (A[i] > 0) {
                A[i]--;
                time.append(i);
                break;
            }
        }
        
        for (int i = 9; i >= 0; i--) {
            if (A[i] > 0) {
                A[i]--;
                time.append(i);
                break;
            }
        }
        
        return time.toString();
    }
}
