import sys


def solve(n, m, arr):
    dp = [[float('inf')] * m for _ in range(n)]

    # 초기값 세팅
    for j in range(m):
        dp[0][j] = arr[0][j]

    # 점화식: min(이전값들 중 최소 + 현재 무기) [같은 무기는 제외]
    for i in range(1, n):
        for j in range(m):
            for k in range(m):
                if j == k: continue
                dp[i][j] = min(dp[i][j], dp[i - 1][k] + arr[i][j])

    return min(dp[-1])


def main():
    n, m = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, m, arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
