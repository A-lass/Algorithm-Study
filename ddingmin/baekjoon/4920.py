import sys


def solve(n, arr):
    ans = -1 * float('inf')

    # 3개짜리
    for i in range(n - 1):
        for j in range(n - 2):
            ans = max(ans, arr[i][j] + arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 1][j + 2])

            ans = max(ans, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 2])
            ans = max(ans, arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2] + arr[i][j])

            ans = max(ans, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1])
            ans = max(ans, arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j + 2] + arr[i][j + 1])

    for i in range(n - 2):
        for j in range(n - 1):
            ans = max(ans, arr[i][j + 1] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 2][j])

            ans = max(ans, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 1][j + 1])
            ans = max(ans, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i][j + 1])

            ans = max(ans, arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1] + arr[i + 1][j])
            ans = max(ans, arr[i][j + 1] + arr[i + 1][j + 1] + arr[i + 2][j + 1] + arr[i + 2][j])

    # 4개짜리
    for i in range(n - 1):
        for j in range(n - 1):
            ans = max(ans, arr[i][j] + arr[i + 1][j] + arr[i][j + 1] + arr[i + 1][j + 1])
    # 기다란거
    for i in range(n - 3):
        for j in range(n):
            ans = max(ans, arr[i][j] + arr[i + 1][j] + arr[i + 2][j] + arr[i + 3][j])
    for i in range(n):
        for j in range(n - 3):
            ans = max(ans, arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i][j + 3])

    return ans


def main():
    idx = 1
    while 1:
        n = int(input())
        if n == 0:
            break
        arr = [list(map(int, input().split())) for _ in range(n)]
        print(f"{idx}. {solve(n, arr)}")
        idx += 1


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
