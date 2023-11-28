import sys

n, m = map(int, sys.stdin.readline().split())
d = [0] + [int(sys.stdin.readline()) for _ in range(n)]
c = [0] + [int(sys.stdin.readline()) for _ in range(m)]
INF = float('inf')
dp = [[INF] * (m + 1) for _ in range(n + 1)]
dp[0] = [0] * (m + 1)
for i in range(1, n + 1):
    for j in range(i, m - n + i + 1):
        dp[i][j] = min(dp[i][j - 1], dp[i - 1][j - 1] + d[i] * c[j])

print(dp[n][m])
