import sys

n, k = map(int, sys.stdin.readline().split())
days = [-1] * (n + 1)
for _ in range(k):
    a, b = map(int, sys.stdin.readline().split())
    days[a] = b - 1
dp = [[0] * 3 for _ in range(n + 1)]
dp[1] = [1] * 3
if days[1] != -1:
    dp[1] = [0] * 3
    dp[1][days[1]] = 1
x = sum(dp[1])
dp[2] = [x] * 3
if days[2] != -1:
    dp[2] = [0] * 3
    dp[2][days[2]] = x
for i in range(3, n + 1):
    dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 2][1] + dp[i - 2][2]
    dp[i][1] = dp[i - 1][0] + dp[i - 1][2] + dp[i - 2][0] + dp[i - 2][2]
    dp[i][2] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 2][0] + dp[i - 2][1]
    for j in range(3):
        if dp[i - 1][j] == 0:
            dp[i][j] = sum(dp[i - 1])
    if days[i] != -1:
        for j in range(3):
            if j != days[i]:
                dp[i][j] = 0
print(sum(dp[n]) % 10000)
