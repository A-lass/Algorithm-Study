import sys
from collections import deque


def main():
    input = sys.stdin.readline

    dir = {'W': [-1, 0], 'S': [1, 0], 'A': [0, -1], 'D': [0, 1]}

    h, w = map(int, input().split())
    arr = [list(input().strip()) for _ in range(h)]

    # 다오 제거
    for i in range(h):
        for j in range(w):
            if arr[i][j] == 'D':
                arr[i][j] = '.'
                sx, sy = i, j

    n = int(input())

    move = [list(map(str, input().split())) for _ in range(n)]

    def solve(sx, sy):
        q = deque()
        q.append([sx, sy, ""])
        visit = [[0] * w for _ in range(h)]
        visit[sx][sy] = 1

        while q:
            i, j, cmd = q.popleft()
            if arr[i][j] == 'Z':
                return ['YES', cmd]
            if not (visit[i][j] - 1 < len(move)):
                continue
            k1, k2 = move[visit[i][j] - 1]

            x, y = i + dir[k1][0], j + dir[k1][1]
            if (0 <= x < h and 0 <= y < w) and arr[x][y] != '@':
                visit[x][y] = visit[i][j] + 1
                q.append([x, y, cmd + k1])

            x, y = i + dir[k2][0], j + dir[k2][1]
            if (0 <= x < h and 0 <= y < w) and arr[x][y] != '@':
                visit[x][y] = visit[i][j] + 1
                q.append([x, y, cmd + k2])
        return ['NO']

    ans = solve(sx, sy)
    for pp in ans:
        print(pp)


if __name__ == '__main__':
    main()
