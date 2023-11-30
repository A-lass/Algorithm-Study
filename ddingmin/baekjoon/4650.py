import sys
import heapq


def to_node(node_dict, char):
    if char not in node_dict:
        node_dict[char] = len(node_dict)
    return node_dict[char]


def solve(n, adj):
    start = 0
    visit = [0] * n
    visited_cnt = 1
    visit[start] = 1
    ans = 0

    hq = []
    for nxt, cost in adj[start]:
        heapq.heappush(hq, [cost, nxt])

    while visited_cnt < n and hq:
        cost, cur = heapq.heappop(hq)
        if visit[cur]: continue
        visit[cur] = 1
        visited_cnt += 1
        ans += cost
        for nxt, nxt_cost in adj[cur]:
            heapq.heappush(hq, [nxt_cost, nxt])

    return ans


def main():
    n = int(input())
    lines = []

    while 1:
        if n == 0:
            break
        line = list(input().split())
        if len(line) == 1:
            node_dict = {}
            adj = [[] for _ in range(n)]
            for l in lines:
                if len(l) > 2:
                    a = to_node(node_dict, l[0])
                    for i in range(1, len(l) // 2):
                        b = to_node(node_dict, l[i * 2])
                        c = int(l[i * 2 + 1])
                        adj[a].append([b, c])
                        adj[b].append([a, c])
            print(solve(n, adj))
            n = int(line[0])
            lines.clear()
        else:
            lines.append(line)


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
