import sys
from heapq import *


def dijkstra(start):
    q = []
    distance[start] = 0
    who_met[start] = [start]
    heappush(q, (0, start))
    while q:
        dist, u = heappop(q)
        if distance[u] < dist:
            continue
        for v, w in graph[u]:
            if distance[v] <= dist + w:
                continue
            distance[v] = dist + w
            who_met[v] = who_met[u] + [v]
            heappush(q, (distance[v], v))


INF = float('inf')
T = int(sys.stdin.readline())
for t in range(1, T + 1):
    n, m = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(m)]
    distance = [INF] * m
    who_met = [[] for _ in range(m)]
    for _ in range(n):
        u, v, w = map(int, sys.stdin.readline().split())
        graph[u].append([v, w])
        graph[v].append([u, w])
    dijkstra(0)
    if distance[-1] == INF:
        print(f'Case #{t}: -1')
    else:
        print(f'Case #{t}:', *who_met[-1])
