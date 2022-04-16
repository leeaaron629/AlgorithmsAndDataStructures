from typing import List

def maxProfit(prices: List[int]) -> int:
    # [7,1,5,3,6,4,2,10]
    maxProfit = 0
    runningMin = prices[0]
    
    for p in prices:
        
        if p < runningMin:
            runningMin = p
            
        currentProfit = p - runningMin
        
        if currentProfit > maxProfit:
            maxProfit = currentProfit
            
    return maxProfit