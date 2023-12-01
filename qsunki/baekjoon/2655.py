import sys

n = int(sys.stdin.readline())
bricks = [[0, 0, 0]] + [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
dp = [x[1] for x in bricks]
dp_bricks = [[i] for i in range(n + 1)]
# i번 벽돌이 가장 위에 있을 때 높이
for _ in range(n):
    is_updated = False
    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if i == j:
                continue
            if bricks[i][0] < bricks[j][0] and bricks[i][2] < bricks[j][2]:
                tmp = bricks[i][1] + dp[j]
                if dp[i] < tmp:
                    dp[i] = tmp
                    dp_bricks[i] = [i] + dp_bricks[j]
                    is_updated = True
    if not is_updated:
        break
idx = 0
for i in range(1, n + 1):
    if dp[i] > dp[idx]:
        idx = i
print(len(dp_bricks[idx]), *dp_bricks[idx], sep='\n')
