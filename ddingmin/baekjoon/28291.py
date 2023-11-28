import sys
from collections import deque


def solve(n, m, arr, power, lamps):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    q = deque(lamps)
    while q:
        i, j = q.popleft()
        for k in range(4):
            x, y = i + dx[k], j + dy[k]
            if not (0 <= x < n and 0 <= y < m): continue
            if arr[x][y] == 1 and power[i][j] - 1 > power[x][y]:
                power[x][y] = power[i][j] - 1
                q.append([x, y])

    for i in range(n):
        for j in range(m):
            if arr[i][j] == 3:
                flag = False
                for k in range(4):
                    x, y = i + dx[k], j + dy[k]
                    if not (0 <= x < n and 0 <= y < m):
                        continue
                    if power[x][y]:
                        flag = True
                if not flag:
                    return "failed"

    return "success"


def main():
    n, m = map(int, input().split())
    arr = [[0] * m for _ in range(n)]
    power = [[0] * m for _ in range(n)]
    lamps = []
    for _ in range(int(input())):
        cmd, i, j = input().split()
        i, j = int(i), int(j)
        if cmd == 'redstone_block':
            arr[i][j] = 2
            lamps.append([i, j])
            power[i][j] = 15
        elif cmd == 'redstone_dust':
            arr[i][j] = 1
        elif cmd == 'redstone_lamp':
            arr[i][j] = 3
    print(solve(n, m, arr, power, lamps))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
