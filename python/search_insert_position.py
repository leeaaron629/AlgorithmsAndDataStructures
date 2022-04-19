# https://leetcode.com/problems/search-insert-position/

import time
from typing import List

def searchInsert(nums: List[int], target: int) -> int:

    # When the target doesn't exists, return the position it should be in
    #   -> If it's equal, return current position
    #   -> If it's greater than the current, return current position + 1
    #   -> If it's less than the current, return current position

    # 5 -> 01234 -> 2
    # 6 -> 012345 -> 2
    beg = 0
    end = len(nums) - 1
    mid = None

    # print('Find:', target)
    # print('Nums:', nums)
    
    while beg <= end:
        mid = (beg + end) // 2
        # print('[Current] - Mid:', mid, 'Beg:', beg, 'End:', end)
        if target == nums[mid]:
            break;
        elif target > nums[mid]:
            beg = mid + 1
        elif target < nums[mid]:
            end = mid - 1
        else:
            raise Exception("Invalid state! mid:", mid, "nums[mid]", nums[mid], "target:", target) 
    
    # print('[After BST] - Mid:', mid)

    if target == nums[mid]:
        return mid
    elif target > nums[mid]:
        return mid + 1
    elif target < nums[mid]:
        return mid
    else:
        raise Exception("Invalidated state! mid", mid, "target:", target)

res = searchInsert([1,2,3,5,6,7], 5)
assert (res == 3), "Expected: 3 | Result:" + str(res) 
res = searchInsert([1,3,5,6], 5)
assert (res == 2), "Expected: 2 | Result:" + str(res) 
res = searchInsert([1,3,5,6], 0)
assert (res == 0), "Expected 0 | Result: " + str(res)
res = searchInsert([1,3,5,6], 2)
assert (res == 1), "Expected 1 | Result: " + str(res)
res = searchInsert([1,3,5,6], 7)
assert (res == 4), "Expected 4 | Result: " + str(res)