import sys
import heapq


def solve(n, adj, node):
    answer = [0]
    start = 1

    hq = []
    heapq.heappush(hq, [-1 * node[start - 1], start])

    while hq:
        dist, cur = heapq.heappop(hq)
        answer.append((-1 * dist) + answer[-1])
        for nxt in adj[cur]:
            heapq.heappush(hq, [-1 * node[nxt - 1], nxt])

    return answer[1:]


def main():
    input = sys.stdin.readline
    n = int(input())
    adj = [[] for _ in range(n + 1)]
    p = list(map(int, input().split()))
    for cur in range(len(p)):
        adj[p[cur]].append(cur + 2)
    node = list(map(int, input().split()))

    result = solve(n, adj, node)
    for pp in result:
        print(pp)


if __name__ == '__main__':
    main()
