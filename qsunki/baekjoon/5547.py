import sys
from collections import deque


def cnt_inner_wall(x, y):
    queue = deque([(x, y)])
    visited[y][x] = True
    inner_wall_cnt = 0
    is_open = False
    while queue:
        cx, cy = queue.popleft()
        row = cy % 2
        for i in range(6):
            nx = cx + dx[row][i]
            ny = cy + dy[i]
            if nx < 0 or nx >= w or ny < 0 or ny >= h:
                is_open = True
                continue
            if visited[ny][nx]:
                continue
            if view[ny][nx] == 1:
                inner_wall_cnt += 1
                continue
            queue.append((nx, ny))
            visited[ny][nx] = True
    return 0 if is_open else inner_wall_cnt


w, h = map(int, sys.stdin.readline().split())
view = [[int(x) for x in sys.stdin.readline().split()] for _ in range(h)]
# odd_row, even_row
dx = [[0, -1, 0, 1, 1, 1], [-1, -1, -1, 0, 1, 0]]
dy = [-1, 0, 1, 1, 0, -1]
wall_cnt = 0
inner_wall_cnt = 0
visited = [[False] * w for _ in range(h)]

for y in range(h):
    for x in range(w):
        if view[y][x] == 0:
            if visited[y][x]:
                continue
            inner_wall_cnt += cnt_inner_wall(x, y)
            continue
        row = y % 2
        for i in range(6):
            ny = y + dy[i]
            nx = x + dx[row][i]
            if nx < 0 or nx >= w or ny < 0 or ny >= h:
                wall_cnt += 1
                continue
            if view[ny][nx] == 0:
                wall_cnt += 1
print(wall_cnt - inner_wall_cnt)
