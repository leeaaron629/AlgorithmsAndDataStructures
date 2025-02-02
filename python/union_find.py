class UnionFind:
    def __init__(self):
        self.parent = {}

    def find(self, x):
        if x not in self.parent:
            self.parent[x] = x
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    # union x and y, by setting root_x's parent to root_y
    # self.parent[root_x] = root_y
    # now y is the parent of root_x and x
    def union(self, x, y):
        self.parent[self.find(x)] = self.find(y) 