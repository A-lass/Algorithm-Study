import sys
from collections import deque


def findStart():
    start_a = (0, 0)
    start_b = (0, 0)
    is_first = True
    for i in range(n):
        for j in range(m):
            if board[i][j] == 'o':
                if is_first:
                    start_a = (i, j)
                    is_first = False
                else:
                    start_b = (i, j)
                    return start_a, start_b


def bfs(start_a, start_b):
    queue = deque([(start_a, start_b, 0)])
    visited[start_a[0]][start_a[1]][start_b[0]][start_b[1]] = True
    visited[start_b[0]][start_b[1]][start_a[0]][start_a[1]] = True
    while queue:
        current_a, current_b, current_step, = queue.popleft()
        if current_step >= 10:
            return -1
        next_step = current_step + 1
        for i in range(4):
            next_a = current_a[0] + dx[i], current_a[1] + dy[i]
            next_b = current_b[0] + dx[i], current_b[1] + dy[i]
            if 0 <= next_a[0] < n and 0 <= next_a[1] < m:
                if board[next_a[0]][next_a[1]] == '#':
                    next_a = current_a
            if 0 <= next_b[0] < n and 0 <= next_b[1] < m:
                if board[next_b[0]][next_b[1]] == '#':
                    next_b = current_b
            if (0 <= next_a[0] < n and 0 <= next_a[1] < m
                    and 0 <= next_b[0] < n and 0 <= next_b[1] < m):
                if visited[next_a[0]][next_a[1]][next_b[0]][next_b[1]]:
                    continue
                visited[next_a[0]][next_a[1]][next_b[0]][next_b[1]] = True
                visited[next_b[0]][next_b[1]][next_a[0]][next_a[1]] = True
                queue.append((next_a, next_b, next_step))
            elif (0 <= next_a[0] < n and 0 <= next_a[1] < m
                  or 0 <= next_b[0] < n and 0 <= next_b[1] < m):
                return next_step
    return -1


n, m = map(int, sys.stdin.readline().split())
board = [sys.stdin.readline().strip() for _ in range(n)]

visited = [[[[False] * m for _ in range(n)] for _ in range(m)] for _ in range(n)]

dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]

start_a, start_b = findStart()

ans = bfs(start_a, start_b)
print(ans)
