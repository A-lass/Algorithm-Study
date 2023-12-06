import sys


def fill_dp(n):
    for i in range(1, n):
        if C[i]:
            dp[i][0] = max(dp[i - 1])
            dp[i][1] = max(dp[i - 1])
            continue
        dp[i][0] = dp[i - 1][1] + 1
        dp[i][1] = max(dp[i - 1])


n = int(sys.stdin.readline())
C = list(map(int, sys.stdin.readline().split()))
dp = [[0, 0] for _ in range(n)]
fill_dp(n)
ans = max(dp[n - 1])
dp = [[0, 0] for _ in range(n)]
if C[0] == 0:
    dp[0][0] = 1
    fill_dp(n - 1)
    ans = max(ans, *dp[n - 2])
print(sum(C) + ans)
