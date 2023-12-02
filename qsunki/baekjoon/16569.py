import sys
from collections import deque


def volcano_bfs(q):
    for x, y, t in q:
        volcano_visit_time[x][y] = t
        js_visited[x][y] = True
    while q:
        cx, cy, ct = q.popleft()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            nt = ct + 1
            if nx < 0 or nx >= m or ny < 0 or ny >= n:
                continue
            if volcano_visit_time[nx][ny] <= nt:
                continue
            volcano_visit_time[nx][ny] = nt
            q.append((nx, ny, nt))


def bfs(x, y):
    ans = (graph[x][y], 0)
    q = deque([(x, y, 0)])
    js_visited[x][y] = True
    while q:
        cx, cy, ct = q.popleft()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            nt = ct + 1
            if nx < 0 or nx >= m or ny < 0 or ny >= n:
                continue
            if js_visited[nx][ny]:
                continue
            if volcano_visit_time[nx][ny] <= nt:
                continue
            if graph[nx][ny] > ans[0]:
                ans = (graph[nx][ny], nt)
            js_visited[nx][ny] = True
            q.append((nx, ny, nt))
    return ans


INF = float('inf')
m, n, v = map(int, sys.stdin.readline().split())
x, y = map(int, sys.stdin.readline().split())
x, y = x - 1, y - 1
graph = [list(map(int, sys.stdin.readline().split())) for _ in range(m)]
volcano_q = deque([(lambda x, y, t: (int(x) - 1, int(y) - 1, int(t)))(*sys.stdin.readline().split()) for _ in range(v)])
js_visited = [[False] * n for _ in range(m)]
volcano_visit_time = [[INF] * n for _ in range(m)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

volcano_bfs(volcano_q)
ans = bfs(x, y)
print(*ans)
