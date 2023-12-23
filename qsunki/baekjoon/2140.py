import sys

dx = [0, 0, 1, 1, 1, -1, -1, -1]
dy = [1, -1, 1, 0, -1, 1, 0, -1]
n = int(sys.stdin.readline())
grid = [list(sys.stdin.readline().strip()) for _ in range(n)]
ans = 0 if n < 5 else (n - 4) ** 2

for x in range(1, n - 1):
    for y in range(1, n - 1):
        if 1 < x < n - 2 and 1 < y < n - 2:
            continue
        zero_flag = False
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if grid[nx][ny] == '0':
                zero_flag = True
                break
        if not zero_flag:
            for i in range(8):
                nx = x + dx[i]
                ny = y + dy[i]
                if grid[nx][ny] == '#' or grid[nx][ny] == '*':
                    continue
                grid[nx][ny] = str(int(grid[nx][ny]) - 1)
            grid[x][y] = '*'
            ans += 1

print(ans)
