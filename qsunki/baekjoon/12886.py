from collections import deque
import sys


def bfs(a, b, c):
    q = deque([(a, b, c)])
    visited[a][c] = True
    while q:
        x, y, z = q.popleft()
        if x == y == z:
            return 1
        for a, b in (x, y), (y, z), (x, z):
            if a == b:
                continue
            b = b - a
            a = a * 2
            c = sum_ - a - b
            a, b, c = sorted([a, b, c])
            if visited[a][c]:
                continue
            q.append((a, b, c))
            visited[a][c] = True
    return 0


a, b, c = sorted(map(int, sys.stdin.readline().split()))
sum_ = a + b + c
visited = [[False] * (sum_ + 1) for _ in range(sum_ + 1)]
ans = 0
if sum_ % 3 == 0:
    ans = bfs(a, b, c)
print(ans)
