from ast import List
import collections

def findRedundantConnection(edges: List[List[int]]) -> List[int]:
    graph = collections.defaultdict(set)
    
    def bfs(source, target):
        # print('Starting bfs on ', u, v)
        # for k in graph:
        #     print(k, ' -> ', graph[k])
        nodesToVisit = [source]
        visited.add(source)
        while nodesToVisit:                
            curNode = nodesToVisit.pop()
            if curNode == target: return True
            neighbors = graph[curNode]
            # print(curNode,visited,neighbors)
            for n in neighbors:
                if n not in visited:
                    visited.add(n)
                    nodesToVisit.append(n)
        
        return False
        
    
    for u, v in edges:
        visited = set()
        if u in graph and v in graph and bfs(u, v):
            return u, v
        graph[u].add(v)
        graph[v].add(u)
        
#         for item in graph.items():
#             print(item)
                
    return []


