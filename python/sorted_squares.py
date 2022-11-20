from typing import List

def sortedSquares(nums: List[int]) -> List[int]:
    
    if len(nums) == 0: return []
    
    print(nums, len(nums))
    
    p1 = 0
    p2 = 0
    results = []
    # Move the pointer to the next positive integer if it exists
    while p1 < len(nums) and nums[p1] < 0:
        p1 += 1
    
    # Set p2 to p1
    p2 = p1 - 1
    
    while True:
        
        p1SideIsComplete = False
        if p1 >= len(nums):
            p1SideIsComplete = True
        
        p2SideIsComplete = False
        if p2 < 0:
            p2SideIsComplete = True
            
        if p1SideIsComplete and p2SideIsComplete:
            break
        
        print('Indexes', p1, p2)
    
        if p1SideIsComplete:
            results.append(nums[p2] ** 2)
            p2 -= 1
        elif p2SideIsComplete:
            results.append(nums[p1] ** 2)
            p1 += 1
        else:
            p1Squared = nums[p1] ** 2
            p2Squared = nums[p2] ** 2
            
            print('Value', p1Squared, p2Squared)
            if p1Squared <= p2Squared:
                results.append(p1Squared)
                p1 += 1
            else:
                results.append(p2Squared)
                p2 -= 1
        
        print(results)
        
    return results

result = sortedSquares([-1])
print(result)