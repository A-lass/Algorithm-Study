import sys


def back(x, y, d, move_cnt, visit_cnt):
    global ans
    if visit_cnt == blank_cnt:
        ans = min(ans, move_cnt)
        return
    nx = x + dx[d]
    ny = y + dy[d]
    cnt = 0
    while 0 <= nx < n and 0 <= ny < m and graph[nx][ny] == 0:
        cnt += 1
        nx = nx + dx[d]
        ny = ny + dy[d]
    if visit_cnt + cnt == blank_cnt:
        ans = min(ans, move_cnt)
        return
    for i in range(4):
        if i % 2 == d % 2:
            continue
        nx = x + dx[d] * cnt + dx[i]
        ny = y + dy[d] * cnt + dy[i]
        if nx < 0 or nx >= n or ny < 0 or ny >= m or graph[nx][ny] == 1:
            continue
        for j in range(cnt + 1):
            graph[x + dx[d] * j][y + dy[d] * j] = 1
        back(nx, ny, i, move_cnt + 1, visit_cnt + cnt + 1)
        for j in range(cnt + 1):
            graph[x + dx[d] * j][y + dy[d] * j] = 0


sys.setrecursionlimit(10 ** 8)
t = 0
while True:
    t += 1
    read = sys.stdin.readline()
    if not read:
        break
    n, m = map(int, read.split())
    graph = [[1 if x == '*' else 0 for x in sys.stdin.readline().strip()] for _ in range(n)]
    blank_cnt = n * m - sum(map(sum, graph))
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    INF = float('inf')
    ans = INF
    if blank_cnt == 1:
        ans = 0
    else:
        for i in range(n):
            for j in range(m):
                for d in range(4):
                    nx = i + dx[d]
                    ny = j + dy[d]
                    if graph[i][j] == 1 or nx < 0 or nx >= n or ny < 0 or ny >= m or graph[nx][ny] == 1:
                        continue
                    back(i, j, d, 1, 1)

    ans = ans if ans != INF else -1
    print(f'Case {t}: {ans}')
