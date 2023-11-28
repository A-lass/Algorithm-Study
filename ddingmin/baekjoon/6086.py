import sys
from collections import deque

MAX_NODE = 401


def solve(capacity, flow, adj, start, end):
    ans = 0
    visit = [[0] * MAX_NODE for _ in range(MAX_NODE)]

    while 1:
        prev = [0] * MAX_NODE
        q = deque([start])
        while q and prev[end] == 0:
            cur = q.popleft()
            for nxt in adj[cur]:
                if not visit[cur][nxt] and not prev[nxt] and capacity[cur][nxt] - flow[cur][nxt] > 0:
                    prev[nxt] = cur
                    q.append(nxt)
                    if nxt == end:
                        break

        if prev[end] == 0:
            break

        min_flow = float('inf')

        node = end
        while node != start:
            min_flow = min(min_flow, capacity[prev[node]][node] - flow[prev[node]][node])
            node = prev[node]

        node = end
        while node != start:
            flow[prev[node]][node] += 1
            flow[node][prev[node]] -= 1
            visit[prev[node]][node] = 1
            node = prev[node]

        ans += 1

    return ans


def main():
    n, p = map(int, input().split())
    capacity = [[0] * MAX_NODE for _ in range(MAX_NODE)]
    flow = [[0] * MAX_NODE for _ in range(MAX_NODE)]
    adj = [[] for _ in range(MAX_NODE)]
    for _ in range(p):
        a, b = map(int, input().split())
        capacity[a][b] = 1
        adj[a].append(b)
        adj[b].append(a)

    print(solve(capacity, flow, adj, 1, 2))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
