import sys
from collections import deque

input = sys.stdin.readline

dx, dy = [1, -1, 0, 0], [0, 0, 1, -1]

# input
n, m = map(int, input().split())
r, c, rr, cc = map(int, input().split())
r, c, rr, cc = r - 1, c - 1, rr - 1, cc - 1

arr = [list(input().strip()) for _ in range(n)]

visited = [[0] * m for _ in range(n)]


def jump():
    q = deque()
    visit = [[0] * m for _ in range(n)]
    visit[r][c] = 1
    q.append([r, c])

    delete = []

    while q:
        i, j = q.popleft()
        for k in range(4):
            x, y = i + dx[k], j + dy[k]
            if not (0 <= x < n and 0 <= y < m): continue
            if visit[x][y]: continue

            if visited[x][y] == 1:
                visit[x][y] = 1
                q.append([x, y])
            elif visited[x][y] == 0 and arr[x][y] == '0':
                visited[x][y] = 1
                visit[x][y] = 1
                q.append([x, y])
            elif visited[x][y] == 0 and arr[x][y] != '0':
                delete.append([x, y])

    for x, y in delete:
        visited[x][y] = 1
        arr[x][y] = '0'


ans = 0
arr[r][c] = '0'
visited[r][c] = 1

while arr[rr][cc] == '#':
    ans += 1
    jump()

print(ans)
