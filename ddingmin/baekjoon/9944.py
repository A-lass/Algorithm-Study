import sys


def solve(n, m, arr, need_filled):
    dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]

    def bt(i, j, filled_count, depth):
        nonlocal need_filled
        temp = float('inf')

        # 이미 최소 경로를 넘은 경우
        if depth > ans:
            return temp

        # 모든 공간이 채워진 경우
        if filled_count == need_filled:
            return depth

        for k in range(4):
            x, y = i + dx[k], j + dy[k]
            if not (0 <= x < n and 0 <= y < m): continue
            if not visit[x][y] and arr[x][y] == '.':
                f_cnt = 0

                fill_arr = []
                while 1:
                    fill_arr.append([x, y])
                    visit[x][y] = 1
                    f_cnt += 1
                    nx, ny = x + dx[k], y + dy[k]
                    if not (0 <= nx < n and 0 <= ny < m): break
                    if not visit[nx][ny] and arr[nx][ny] == '.':
                        x, y = nx, ny
                    else:
                        break

                temp = min(temp, bt(x, y, filled_count + f_cnt, depth + 1))

                clear_arr = []
                # 되돌리기
                for idx in range(1, f_cnt + 1):
                    clear_arr.append([i + dx[k] * idx, j + dy[k] * idx])
                    visit[i + dx[k] * idx][j + dy[k] * idx] = 0

        return temp

    ans = float('inf')
    for i in range(n):
        for j in range(m):
            if arr[i][j] == '.':
                visit = [[0] * m for _ in range(n)]
                visit[i][j] = 1
                ans = min(ans, bt(i, j, 1, 0))
    if ans == float('inf'): 
        ans = -1
    return ans


def main():
    case = 1
    while True:
        try:
            n, m = map(int, input().split())
            arr = [list(input().strip()) for _ in range(n)]
            need_filled = 0
            for i in range(n):
                for j in range(m):
                    if arr[i][j] == '.':
                        need_filled += 1
            print(f"Case {case}: {solve(n, m, arr, need_filled)}")
            case += 1
        except EOFError:
            break


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    # input = sys.stdin.readline
    main()
