import sys
from collections import deque


def bfs(graph, ix, iy):
    queue = deque([(ix, iy, 0)])
    visited[ix][iy] = True
    while queue:
        cx, cy, step = queue.popleft()
        jump = graph[cx][cy]
        for i in range(4):
            nx = cx + dx[i] * jump
            ny = cy + dy[i] * jump
            if not (0 <= nx < n and 0 <= ny < m):
                continue
            if visited[nx][ny]:
                continue
            if nx == n - 1 and ny == m - 1:
                return step + 1
            queue.append((nx, ny, step + 1))
            visited[nx][ny] = True
    return -1


if __name__ == '__main__':
    n, m = map(int, sys.stdin.readline().split())
    graph = [list(map(int, sys.stdin.readline().strip())) for _ in range(n)]
    visited = [[False] * m for _ in range(n)]
    dx = [0, 0, 1, -1]
    dy = [1, -1, 0, 0]
    x = bfs(graph, 0, 0)
    print(x if x != -1 else 'IMPOSSIBLE')
