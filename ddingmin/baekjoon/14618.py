import sys
import heapq


def solve(adj, visit, a_type, b_type, start):
    # dijk
    visit[start] = 0
    hq = [[0, start]]
    while hq:
        dist, cur = heapq.heappop(hq)
        if visit[cur] != dist:
            continue
        for next_dist, nxt in adj[cur]:
            total_dist = dist + next_dist
            if total_dist < visit[nxt]:
                visit[nxt] = total_dist
                heapq.heappush(hq, [total_dist, nxt])

    # A형 집 부터 갱신
    answer = [None, float('inf')]
    for node in a_type:
        if visit[node] < answer[1]:
            answer = ['A', visit[node]]
    for node in b_type:
        if visit[node] < answer[1]:
            answer = ['B', visit[node]]
    if answer[0] is None:
        return [-1]
    return answer


def main():
    input = sys.stdin.readline
    n, m = map(int, input().split())  # node, edge
    adj = [[] for _ in range(n + 1)]
    visit = [float('inf')] * (n + 1)

    home_node = int(input())
    k = int(input())  # 종류 별 동물의 수
    a_type = list(map(int, input().split()))
    b_type = list(map(int, input().split()))

    for _ in range(m):
        a, b, dist = map(int, input().split())
        adj[a].append([dist, b])
        adj[b].append([dist, a])

    ans = solve(adj, visit, a_type, b_type, home_node)
    for pp in ans:
        print(pp)


if __name__ == '__main__':
    main()
