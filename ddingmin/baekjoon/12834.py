import sys
import heapq


def solve(n, v, e, KIST, CRFOOD, house, adj):
    return dijk(KIST, adj, v, house) + dijk(CRFOOD, adj, v, house)


def dijk(start, adj, v, house):
    visit = [float('inf')] * (v + 1)
    visit[start] = 0
    hq = [[0, start]]
    while hq:
        cur_cost, cur_node = heapq.heappop(hq)
        if cur_cost != visit[cur_node]: continue
        for next_node, next_cost in adj[cur_node]:
            value = cur_cost + next_cost
            if value < visit[next_node]:
                visit[next_node] = value
                heapq.heappush(hq, [value, next_node])
    distance = 0
    for i in house:
        distance += visit[i] if visit[i] != float('inf') else -1

    return distance


def main():
    n, v, e = map(int, input().split())
    KIST, CRFOOD = map(int, input().split())
    house = list(map(int, input().split()))

    adj = [[] for _ in range(v + 1)]
    for _ in range(e):
        a, b, c = map(int, input().split())
        adj[a].append([b, c])
        adj[b].append([a, c])

    print(solve(n, v, e, KIST, CRFOOD, house, adj))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
