# https://leetcode.com/problems/smallest-string-with-swaps/

from typing import List

def smallestStringWithSwaps(s: str, pairs: List[List[int]]) -> str:
        
        if not pairs: return s

        # Build Graph - Use LinkedList
        
        graph = collections.defaultdict(set)
        n = len(s)
        for moves in pairs:
            graph[moves[0]].add(moves[1])
            graph[moves[1]].add(moves[0])
                        
        for x in range(n):
            graph[x].add(x)
        
        def dfs(node: int) -> List[int]:
            nodesToVisit = [node]
            visited = set()
            while nodesToVisit:
                curNode = nodesToVisit.pop()
                visited.add(curNode)
                for neighbor in graph[curNode]:
                    nodesToVisit.append(neighbor)
                graph[curNode] = set()
                
            return list(visited)
        
        connected_components = []
        # Iterated through all connected components
        visitedNodes = set()
        for node in graph:
            for neighbor in graph[node]:
                if neighbor not in visitedNodes:
                    connectedNodes = dfs(neighbor)
                    visitedNodes.update(connectedNodes)
                    connectedNodes.sort()
                    connected_components.append(connectedNodes)
                    
        # for connected in connected_components:
        #     print(connected)          
        
        # Sort the characters in each connected component in ascending order
        charArr = list(s)
        result = ['' for i in range(len(s))]
        for connected in connected_components:
            charList = []
            for c in connected:
                charList.append(charArr[c])
            charList.sort()
            # print(charList, connected)
            
            charLookup = zip(connected, charList)
            for c in charLookup:
                # print(c)
                result[c[0]] = c[1]
                                  
        # Build the smallest string
        return ''.join(result)
