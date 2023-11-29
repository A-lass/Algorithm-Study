import sys


def solve(n, m, arr):
    dp = [[0] * m for _ in range(n)]
    ans = 0

    for i in range(n):
        for j in range(m):
            if arr[i][j] == 0:
                dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1
                ans = max(ans, dp[i][j])

    return ans


def main():
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, m, arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
