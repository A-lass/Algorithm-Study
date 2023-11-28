import sys


def solve(n, m, city, arr):
    dp = [[float('inf')] * (n + 1) for _ in range(m + 1)]
    dp[0][0] = 0

    for i in range(1, m + 1):  # 일
        for j in range(n + 1):  # 도시
            dp[i][j] = min(dp[i - 1][j], dp[i - 1][j - 1] + city[j - 1] * arr[i - 1])

    return dp[-1][-1]


def main():
    n, m = map(int, input().split())
    city = list(int(input()) for _ in range(n))
    arr = list(int(input()) for _ in range(m))
    print(solve(n, m, city, arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
