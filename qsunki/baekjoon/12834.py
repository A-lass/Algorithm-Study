import sys
from heapq import *


def dijkstra(x):
    distance = [INF] * (V + 1)
    q = [(0, x)]
    distance[x] = 0
    while q:
        dist, u = heappop(q)
        if distance[u] < dist:
            continue
        for v, w in graph[u]:
            cost = dist + w
            if cost >= distance[v]:
                continue
            distance[v] = cost
            heappush(q, (cost, v))

    return distance


INF = float('inf')
N, V, E = map(int, sys.stdin.readline().split())
A, B = map(int, sys.stdin.readline().split())
members = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(V + 1)]
for _ in range(E):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append([v, w])
    graph[v].append([u, w])

d1 = dijkstra(A)
d2 = dijkstra(B)
ans = 0

for member in members:
    if d1[member] == INF:
        d1[member] = -1
    if d2[member] == INF:
        d2[member] = -1
    ans += d1[member] + d2[member]

print(ans)
