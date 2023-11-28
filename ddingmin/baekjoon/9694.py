import sys
import heapq


def solve(n, m, adj, costs):
    prev = [0] * (m + 1)
    costs[m] = 0
    hq = [[0, m]]
    while hq:
        cost, cur = heapq.heappop(hq)
        if costs[cur] != cost:
            continue
        for nxt, nxt_cost in adj[cur]:
            temp = cost + nxt_cost
            if temp < costs[nxt]:
                costs[nxt] = temp
                heapq.heappush(hq, [temp, nxt])
                prev[nxt] = cur

    if costs[m - 1] == float('inf'):
        return -1

    find = []
    find.append(m - 1)

    f = m - 1
    while f != m:
        f = prev[f]
        find.append(f)
    find[-1] = 0
    return " ".join(map(str, find[::-1]))


def main():
    t = int(input())
    for idx in range(1, t + 1):
        n, m = map(int, input().split())
        adj = [[] for _ in range(m + 1)]
        costs = [float('inf')] * (m + 1)
        for _ in range(n):
            a, b, c = map(int, input().split())
            if a == 0:
                a = m
            if b == 0:
                b = m
            adj[a].append([b, c])
            adj[b].append([a, c])
        print(f"Case #{idx}: {solve(n, m, adj, costs)}")


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
