import sys
from collections import deque


def bfs(graph, x1, y1, visited):
    queue = deque([(x1, y1, 0)])
    visited[x1][y1] = True
    while queue:
        cx, cy, step = queue.popleft()
        for i in range(4):
            nx = cx + dx[i]
            ny = cy + dy[i]
            if nx < 0 or nx >= n or ny < 0 or ny >= m:
                continue
            if visited[nx][ny]:
                continue
            if graph[nx][ny] == '1':
                visited[nx][ny] = True
                queue.append((nx, ny, step + 1))
                graph[nx][ny] = '0'
            elif graph[nx][ny] == '#':
                return step + 1
            else:
                visited[nx][ny] = True
                queue.appendleft((nx, ny, step))


if __name__ == '__main__':
    n, m = map(int, sys.stdin.readline().split())
    x1, y1, x2, y2 = map(lambda x: int(x) - 1, sys.stdin.readline().split())
    graph = [list(sys.stdin.readline().strip()) for _ in range(n)]
    visited = [[False] * m for _ in range(n)]
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    ans = bfs(graph, x1, y1, visited)
    print(ans)
