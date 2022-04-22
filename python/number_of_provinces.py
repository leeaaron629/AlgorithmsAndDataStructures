# https://leetcode.com/problems/number-of-provinces/

from typing import List

def findCircleNum(isConnected: List[List[int]]) -> int:

    numOfProvinces = 0
    def traverseWithBfs(nodeN: int, graph: List[List[int]]):
        nodesToVisit = [nodeN]
        while nodesToVisit:                
            x = nodesToVisit.pop()                
            for y, isConnected in enumerate(graph[x]):
                if isConnected == 1:
                    nodesToVisit.append(y) # Append on to nodesToVisit
                    graph[x][y] = 0 # Disconnect the graph
                    
    # Run BFS on the graph
    for x, row in enumerate(isConnected):
        for connected in row:
            if connected == 1:                
                traverseWithBfs(x, isConnected)
                numOfProvinces += 1
            
    # On complete traversal of each province increment the counter
    
    return numOfProvinces    
