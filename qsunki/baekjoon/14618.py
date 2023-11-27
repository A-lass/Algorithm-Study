import sys
from heapq import *


def dijkstra(start):
    q = []
    distance[start] = 0
    heappush(q, (0, start))
    while q:
        dist, u = heappop(q)
        if distance[u] < dist:
            continue
        for v, w in graph[u]:
            if distance[v] <= dist + w:
                continue
            distance[v] = dist + w
            heappush(q, (distance[v], v))


INF = float('inf')
n, m = map(int, sys.stdin.readline().split())
graph = [[] for _ in range(n + 1)]
house = [' '] * (n + 1)
distance = [INF] * (n + 1)
start = int(sys.stdin.readline())
k = int(sys.stdin.readline())
for u in sys.stdin.readline().split():
    house[int(u)] = 'A'
for u in sys.stdin.readline().split():
    house[int(u)] = 'B'
for _ in range(m):
    u, v, w = map(int, sys.stdin.readline().split())
    graph[u].append([v, w])
    graph[v].append([u, w])

dijkstra(start)
min_distance = INF
house_sort = ''
for i in range(1, n + 1):
    if distance[i] < min_distance and house[i] in 'AB':
        min_distance = distance[i]
        house_sort = house[i]
    if distance[i] == min_distance and house[i] == 'A':
        house_sort = 'A'

if min_distance == INF:
    print(-1)
else:
    print(house_sort)
    print(min_distance)
