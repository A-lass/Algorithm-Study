from heapq import *
import sys


def prim(start):
    global ans
    q = [(0, start)]
    while q:
        w, u = heappop(q)
        if selected[u]:
            continue
        ans += w
        selected[u] = True
        for v, w in graph[u]:
            if selected[v]:
                continue
            heappush(q, (w, v))


while True:
    n = int(sys.stdin.readline())
    if n == 0:
        break
    graph = [[] for _ in range(n)]
    selected = [False] * n
    for _ in range(n - 1):
        line = sys.stdin.readline().split()
        cnt = int(line[1])
        u = ord(line[0]) - ord('A')
        for i in range(2, 2 + 2 * cnt, 2):
            v = ord(line[i]) - ord('A')
            w = int(line[i + 1])
            graph[u].append((v, w))
            graph[v].append((u, w))
    ans = 0
    prim(0)
    print(ans)
