import sys


def is_numeric(s):
    try:
        value = int(s)
        return True
    except ValueError:
        return False


def solve(n, m, arr):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]
    visit = [[0] * 3 for _ in range(3)]
    answer_cmd = None

    def backtracking(i, j, cmd, total, target, depth):
        nonlocal answer_cmd
        if depth == 0:
            if total == target:
                answer_cmd = str(cmd)
            return

        for k in range(4):
            x, y = i + dx[k], j + dy[k]
            if not (0 <= x < 3 and 0 <= y < 3) or visit[x][y] == 1:
                continue
            visit[x][y] = 1
            if is_numeric(arr[x][y]):
                if arr[i][j] == '+':
                    backtracking(x, y, cmd + f"\n{x} {y}", total + int(arr[x][y]), target, depth - 1)
                else:
                    backtracking(x, y, cmd + f"\n{x} {y}", total - int(arr[x][y]), target, depth - 1)
            else:
                backtracking(x, y, cmd + f"\n{x} {y}", total, target, depth)
            visit[x][y] = 0

    # 시작점 5개
    sx, sy = [0, 0, 1, 2, 2], [0, 2, 1, 0, 2]
    for s in range(5):
        xx, yy = sx[s], sy[s]
        visit[xx][yy] = 1
        backtracking(xx, yy, f"{xx} {yy}", int(arr[xx][yy]), n, m - 1)
        visit[xx][yy] = 0
    return answer_cmd


def main():
    n, m = map(int, input().split())
    arr = [list(map(str, input().strip())) for _ in range(3)]
    ans = solve(n, m, arr)
    if ans is not None:
        print(1)
        print(ans, end="")
    else:
        print(0)


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
