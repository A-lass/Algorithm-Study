import sys

m, n = map(int, sys.stdin.readline().split())
graph = [[1 if x == '0' else 0 for x in sys.stdin.readline().split()] for _ in range(m)]
for i in range(1, m):
    for j in range(1, n):
        if graph[i][j] == 0:
            continue
        graph[i][j] = min(graph[i - 1][j - 1], graph[i - 1][j], graph[i][j - 1]) + 1
print(max(map(max, graph)))
