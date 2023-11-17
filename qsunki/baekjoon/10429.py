import sys


def dfs(visited, route, num, cnt):
    cx = route[-1][0]
    cy = route[-1][1]
    operator = graph[cx][cy]
    for i in range(4):
        nx = cx + dx[i]
        ny = cy + dy[i]
        if nx < 0 or nx >= 3 or ny < 0 or ny >= 3:
            continue
        if visited[nx][ny]:
            continue
        if operator.isnumeric():
            route.append((nx, ny))
            visited[nx][ny] = True
            ans = dfs(visited, route, num, cnt)
            if ans == 1:
                return 1
            route.pop()
            visited[nx][ny] = False
            continue
        n_num = num
        if operator == '+':
            n_num += int(graph[nx][ny])
        if operator == '-':
            n_num -= int(graph[nx][ny])
        n_cnt = cnt + 1
        route.append((nx, ny))
        if n_cnt == m and n_num == n:
            return 1
        if n_cnt >= m:
            route.pop()
            continue
        visited[nx][ny] = True
        ans = dfs(visited, route, n_num, n_cnt)
        if ans == 1:
            return 1
        visited[nx][ny] = False
        route.pop()
    return 0


def main():
    for i in range(3):
        for j in range(3):
            if graph[i][j].isnumeric():
                visited = [[False] * 3 for _ in range(3)]
                visited[i][j] = True
                route = [(i, j)]
                ans = dfs(visited, route, int(graph[i][j]), 1)
                if ans == 1:
                    print(1)
                    print('\n'.join([f'{x} {y}' for x, y, in route]))
                    return
    print(0)


n, m = map(int, sys.stdin.readline().split())
graph = [sys.stdin.readline().strip() for _ in range(3)]
dx = [0, 0, 1, -1]
dy = [1, -1, 0, 0]
main()
