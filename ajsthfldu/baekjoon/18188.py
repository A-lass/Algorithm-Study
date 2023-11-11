import sys
from collections import deque


def bfs(graph, x, y, n, movable, ans):
    queue = deque([(x, y, 0, [])])
    while queue:
        cx, cy, step, path = queue.popleft()
        for d in movable[step]:
            dx, dy = dir[d]
            nx = cx + dx
            ny = cy + dy
            if nx < 0 or nx >= h or ny < 0 or ny >= w:
                continue
            if graph[nx][ny] == '@':
                continue
            if graph[nx][ny] == 'Z':
                ans.extend(path)
                ans.append(d)
                return True
            if step + 1 == n:
                continue
            npath = path.copy()
            npath.append(d)
            queue.append((nx, ny, step + 1, npath))

    return False


def main():
    graph = [list(sys.stdin.readline().strip()) for _ in range(h)]
    n = int(sys.stdin.readline())
    movable = [list(sys.stdin.readline().strip().split()) for _ in range(n)]
    ans = []
    x, y = 0, 0
    for i in range(h):
        for j in range(w):
            if graph[i][j] == 'D':
                x, y = i, j
    arrive = bfs(graph, x, y, n, movable, ans)
    if arrive:
        print('YES')
        print(''.join(ans))
    else:
        print('NO')


if __name__ == '__main__':
    dir = {'W': (-1, 0), 'A': (0, -1), 'S': (1, 0), 'D': (0, 1)}
    h, w = map(int, sys.stdin.readline().split())
    main()
