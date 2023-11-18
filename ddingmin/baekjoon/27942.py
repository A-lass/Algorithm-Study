import sys
from collections import deque

dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]


def grow(arr, k, sx, sy):
    if k == 0:
        sx[0] -= 1
        for k in range(sy[0], sy[1] + 1):
            arr[sx[0]][k] = 0
        return 'U'
    elif k == 1:
        sx[1] += 1
        for k in range(sy[0], sy[1] + 1):
            arr[sx[1]][k] = 0
        return 'D'
    elif k == 2:
        sy[0] -= 1
        for k in range(sx[0], sx[1] + 1):
            arr[k][sy[0]] = 0
        return 'L'
    else:
        sy[1] += 1
        for k in range(sx[0], sx[1] + 1):
            arr[k][sy[1]] = 0
        return 'R'


def get_start_ij(k, sx, sy):
    if k == 0:
        return sx[0], sy[0], sx[0] + 1, sy[1] + 1
    elif k == 1:
        return sx[1], sy[0], sx[1] + 1, sy[1] + 1
    elif k == 2:
        return sx[0], sy[0], sx[1] + 1, sy[0] + 1
    else:
        return sx[0], sy[1], sx[1] + 1, sy[1] + 1


def solve(n, arr):
    sx, sy = [n // 2 - 1, n // 2], [n // 2 - 1, n // 2]
    cmd = ""
    energy = 0

    while 1:
        eat = [0, 0, 0, 0]
        for k in range(4):
            si, sj, ei, ej = get_start_ij(k, sx, sy)
            for i in range(si, ei):
                for j in range(sj, ej):
                    x, y = i + dx[k], j + dy[k]
                    if not (0 <= x < n and 0 <= y < n):
                        continue
                    eat[k] += arr[x][y]

        target = [-1, None]
        for k in range(4):
            if eat[k] > 0 and target[0] < eat[k]:
                target = [eat[k], k]

        if target[1] is None:
            print(energy)
            return cmd

        energy += target[0]
        cmd += grow(arr, target[1], sx, sy)


def main():
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, arr))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
