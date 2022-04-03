# https://leetcode.com/problems/longest-common-prefix/

from typing import List

def longestCommonPrefix(strs: List[str]) -> str:

    if not strs: return None
    if len(strs) == 1: return strs[0]

    longestCommonPrefix = '' 
    minStrLen = len(min(strs, key=len))

    for i in range(minStrLen):
        
        c = set()
        
        for str in strs:
            c.add(str[i])

        if longestCommonPrefix == None and len(c) == 1:
            longestCommonPrefix = c.pop()
        elif len(c) == 1: 
            longestCommonPrefix += c.pop()
        else:
            return longestCommonPrefix

    return longestCommonPrefix

def longestCommonPrefix(strs: List[str]) -> str:

    if not strs: return None
    if len(strs) == 1: return strs[0]
    
    longestCommonPrefix = ''
    flipped = zip(*strs)

    for word in flipped: 
        # Check that everything is equal to one another
        c = set(word)

        if len(c) == 1:
            longestCommonPrefix += c.pop()
        else:
            return longestCommonPrefix
        
    return longestCommonPrefix
        

res = longestCommonPrefix(['abc','abcdef', 'ab', 'abxyz'])
assert (res == 'ab'), 'Expected: def | Result: ' + res
res = longestCommonPrefix(["flower","flow","flight"])
assert (res == 'fl'), 'Exepcted: fl | Result: ' + res
res = longestCommonPrefix(['a'])
assert (res == 'a'), 'Expected: a | Result: ' + res
res = longestCommonPrefix([''])
assert (res == ''), 'Expected: | Result: ' + res
res = longestCommonPrefix([])
assert (res == None), 'Expected: None | Result: ' + res
