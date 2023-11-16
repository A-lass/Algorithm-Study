import sys
from collections import deque


def move(arr, n, m, i, j, x, y):
    if not (0 <= x < n and 0 <= y < m):
        return x, y
    if arr[x][y] == '#':
        return i, j
    return x, y


def solve(n, m, arr, coins):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    q = deque()
    q.append(coins)
    visit = {}
    visit[str(coins)] = 1

    depth = 1

    while q:
        if depth > 10:
            return -1
        for _ in range(len(q)):
            coin = q.popleft()
            i1, j1 = coin[0]
            i2, j2 = coin[1]
            for k in range(4):
                x1, y1 = move(arr, n, m, i1, j1, i1 + dx[k], j1 + dy[k])
                x2, y2 = move(arr, n, m, i2, j2, i2 + dx[k], j2 + dy[k])
                nxt = [[x1, y1], [x2, y2]]
                if str(nxt) in visit: continue
                if not (0 <= x1 < n and 0 <= y1 < m) and not (0 <= x2 < n and 0 <= y2 < m):
                    continue

                if (0 <= x1 < n and 0 <= y1 < m) and (0 <= x2 < n and 0 <= y2 < m):
                    visit[str(nxt)] = 1
                    q.append(nxt)
                else:
                    return depth
        depth += 1
    return -1


def main():
    n, m = map(int, input().split())
    arr = [list(map(str, input().strip())) for _ in range(n)]
    coins = []
    for i in range(n):
        for j in range(m):
            if arr[i][j] == 'o':
                coins.append([i, j])

    print(solve(n, m, arr, coins))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
