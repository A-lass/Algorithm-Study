import sys


def back(x, y, sum_):
    if y == m:
        x += 1
        y = 0
    if x == n:
        global ans
        ans = max(ans, sum_)
        return
    if not visited[x][y]:
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[x][ny] or visited[nx][y]:
                continue
            visited[x][y] = True
            visited[x][ny] = True
            visited[nx][y] = True
            back(x, y + 1, sum_ + graph[x][y] * 2 + graph[x][ny] + graph[nx][y])
            visited[x][y] = False
            visited[x][ny] = False
            visited[nx][y] = False
    back(x, y + 1, sum_)


n, m = map(int, sys.stdin.readline().split())
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
visited = [[False] * m for _ in range(n)]
dx = [1, 1, -1, -1]
dy = [1, -1, 1, -1]
ans = 0
back(0, 0, 0)
print(ans)
