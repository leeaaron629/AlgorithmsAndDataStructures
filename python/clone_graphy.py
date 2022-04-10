class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

def cloneGraph(self, node: 'Node') -> 'Node':
    
    if node is None: return None
    
    queue = []
    nodeLookup = {}
    visited = set()
    
    queue.append(node)
    while queue:
        
        curNode = queue.pop(0)
        if curNode.val in visited: continue
        
        newNode = nodeLookup.get(curNode.val)
        if newNode is None:
            newNode = Node(curNode.val)
            nodeLookup[curNode.val] = newNode
        
        visited.add(curNode.val)
        print(curNode.val)
        
        for n in curNode.neighbors:
            queue.append(n)
            
            newNeighbor = nodeLookup.get(n.val)
            if newNeighbor is None:
                newNeighbor = Node(n.val)
                nodeLookup[n.val] = newNeighbor
                
            newNode.neighbors.append(newNeighbor)
            
    
    return nodeLookup[node.val]

values = [1,2,3,4,5]

nodes = list(map(lambda x: Node(x), values))
print(nodes)

nodes[0].neighbors = [nodes[1],nodes[2],nodes[3]]
nodes[3].neighbors = [nodes[4]]

cloneGraph(nodes[0])

