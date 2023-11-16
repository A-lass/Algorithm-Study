import sys

sys.setrecursionlimit(10 ** 6)


def dfs(graph, v, length):
    if len(graph[v]) == 1:
        global max_branch_length
        max_branch_length = max(max_branch_length, length)
        return
    for nv, e in graph[v]:
        if visited[nv]:
            continue
        visited[nv] = True
        dfs(graph, nv, length + e)


def graph_update():
    for i in range(n - 1):
        a, b, d = map(int, sys.stdin.readline().split())
        graph[a].append([b, d])
        graph[b].append([a, d])


if __name__ == '__main__':
    n, r = map(int, sys.stdin.readline().split())
    graph = [[] for _ in range(n + 1)]
    visited = [False] * (n + 1)

    graph_update()

    pillar_length = 0
    max_branch_length = 0

    if len(graph[r]) == 1:
        while not visited[r] and len(graph[r]) <= 2:
            visited[r] = True
            for v, e in graph[r]:
                if visited[v]:
                    continue
                pillar_length += e
                r = v

    if not visited[r]:
        visited[r] = True
        dfs(graph, r, 0)

    print(pillar_length, max_branch_length)
