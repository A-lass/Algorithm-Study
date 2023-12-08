import sys

dir = [[-1, -1], [-1, 1], [1, -1], [1, 1]]


def solve(n, m, arr):
    visit = [[0] * m for _ in range(n)]
    visited = {}

    def bt(power):
        next_power = power
        for i in range(n):
            for j in range(m):
                if visit[i][j]: continue
                for dx, dy in dir:
                    x1, y1 = i + dx, j
                    x2, y2 = i, j + dy
                    if not (0 <= x1 < n and 0 <= y1 < m and 0 <= x2 < n and 0 <= y2 < m):
                        continue
                    if visit[x1][y1] or visit[x2][y2]: continue
                    visit[x1][y1], visit[x2][y2], visit[i][j] = 1, 1, 2
                    if str(visit) in visited:
                        visit[x1][y1], visit[x2][y2], visit[i][j] = 0, 0, 0
                        continue
                    visited[str(visit)] = 1
                    cur_power = arr[x1][y1] + arr[x2][y2] + arr[i][j] * 2
                    next_power = max(next_power, bt(power + cur_power))
                    visit[x1][y1], visit[x2][y2], visit[i][j] = 0, 0, 0
        return next_power

    return bt(0)


def main():
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, m, arr))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
