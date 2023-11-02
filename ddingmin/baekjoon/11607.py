import sys
from collections import deque

input = sys.stdin.readline

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]

# input
n, m = map(int, input().split())
arr = [list(map(int, list(input().strip()))) for _ in range(n)]

visit = [[0] * m for _ in range(n)]

# init
q = deque()
q.append([0, 0])
visit[0][0] = 1

# bfs
while q:
    i, j = q.popleft()
    for k in range(4):
        x, y = i + dx[k] * arr[i][j], j + dy[k] * arr[i][j]
        if not(0 <= x < n and 0 <= y < m):
            continue
        if visit[x][y] == 0:
            q.append([x, y])
            visit[x][y] = visit[i][j] + 1
        elif visit[x][y] > visit[i][j] + 1:
            q.append([x, y])
            visit[x][y] = visit[i][j] + 1
# answer check
if visit[-1][-1] == 0:
    print("IMPOSSIBLE")
else:
    print(visit[-1][-1] - 1)
