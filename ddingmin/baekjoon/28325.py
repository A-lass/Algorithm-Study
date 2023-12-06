import sys


def solve(n, arr):
    ans = 0

    # 첫 번째 큰 집을 먹은 경우
    dp = [[0] * n for _ in range(2)]
    dp[0][0] = 1
    for i in range(1, n - 1):
        # 큰집 먹기
        dp[0][i] = dp[1][i - 1] + 1
        # 작은집 먹기
        dp[1][i] = max(dp[0][i - 1], dp[1][i - 1]) + arr[i]
    # 마지막 큰 집은 못먹음
    ans = max(dp[0][n - 2], dp[1][n - 2]) + arr[n - 1]

    # 첫 번째 작은 집을 먹은 경우
    dp = [[0] * n for _ in range(3)]
    dp[1][0] = arr[0]
    for i in range(1, n):
        # 큰집 먹기
        dp[0][i] = dp[1][i - 1] + 1
        # 작은집 먹기
        dp[1][i] = max(dp[0][i - 1], dp[1][i - 1]) + arr[i]

    ans = max(ans, dp[0][n - 1], dp[1][n - 1])

    return ans


def main():
    n = int(input())
    arr = list(map(int, input().split()))
    print(solve(n, arr))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
