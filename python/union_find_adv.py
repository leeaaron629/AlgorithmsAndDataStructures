from collections import defaultdict
from typing import Dict, DefaultDict

class UnionFind:
    def __init__(self) -> None:
        self.parent: Dict[int, int] = {}
        self.rank: DefaultDict[int, int] = defaultdict(int)
        self.size: DefaultDict[int, int] = defaultdict(int)
        
    def make_set(self, x: int) -> None:
        if x not in self.parent:
            self.parent[x] = x
            self.size[x] = 1
            
    def find(self, x: int) -> int:
        # Initialize if not exists
        if x not in self.parent:
            self.make_set(x)
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]
    
    def union(self, x: int, y: int) -> None:
        x_parent = self.find(x)
        y_parent = self.find(y)
        
        if x_parent == y_parent:
            return
        
        if self.rank[x_parent] < self.rank[y_parent]:
            self.parent[x_parent] = y_parent
            self.size[y_parent] += self.size[x_parent]
        elif self.rank[y_parent] < self.rank[x_parent]:
            self.parent[y_parent] = x_parent
            self.size[x_parent] += self.size[y_parent]
        else:
            self.parent[x_parent] = y_parent
            self.rank[y_parent] += 1
            self.size[y_parent] += self.size[x_parent]
            
    def get_size(self, x: int) -> int:
        return self.size[self.find(x)]
    
    def is_connected(self, x: int, y: int) -> bool:
        return self.find(x) == self.find(y)
    
    def print_state(self):
        print(f"parent={self.parent}")
        print(f"ranks={self.rank}")
        print(f"sizes={self.size}")
    
uf = UnionFind()
uf.union(0,1)
uf.union(2,3)
uf.union(4,5)
uf.union(6,7)

uf.union(1,3)
uf.union(5,7)

uf.union(3,7)
print("Before path compression")
uf.print_state()
res = uf.find(2)
res = uf.find(0)
res = uf.find(4)
print("After path compression")
uf.print_state()