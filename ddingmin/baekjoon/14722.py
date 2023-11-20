import sys


def solve(n, arr):
    dp = [[0] * n for _ in range(n)]

    for i in range(n):
        for j in range(n):
            mDp = max(dp[i - 1][j], dp[i][j - 1])
            if arr[i][j] == mDp % 3:
                dp[i][j] = mDp + 1
            else:
                dp[i][j] = mDp

    return dp[-1][-1]


def main():
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
