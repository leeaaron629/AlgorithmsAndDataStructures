package solved;

import java.util.Arrays;

public class StockMarket {
	public static void main(String[] args) {
		int[] stocks = {6, 3, 10, 1, 7};
		StockMarket driver = new StockMarket();
		System.out.println(driver.maxProfitBetter(stocks));
	}
	
    public int maxProfit(int[] prices) {
    	int N = prices.length;
        
        int[] minArr = new int[N];
        minArr[0] = prices[0];
        for (int i = 1; i < N; i++) {
        	if (prices[i] < minArr[i-1]) {
        		minArr[i] = prices[i];
        	} else {
        		minArr[i] = minArr[i-1];
        	}
        }
        	
        	
        int[] maxArr = new int[N];
        maxArr[N-1] = prices[N-1];
        for (int i = N-2; i >= 0; i--) {
        	if (prices[i] > maxArr[i+1]) {
        		maxArr[i] = prices[i];
        	} else {
        		maxArr[i] = maxArr[i+1];
        	}
        }
        	
        int max = 0;
        for (int i = 1; i < N; i++) {
        	int val = maxArr[i] - minArr[i-1];
        	if (max < val) max = val;
        }
        
        System.out.println(Arrays.toString(minArr));
        System.out.println(Arrays.toString(maxArr));
        System.out.println(max);
        
        return max;
    }
    
    public int maxProfitBetter(int[] prices) {
        
        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
        	if (prices[i] < min) min = prices[i];
        	if (maxProfit < (prices[i] - min)) 
        		maxProfit = prices[i] - min;
        }
        
        return maxProfit;
        
    }
}
