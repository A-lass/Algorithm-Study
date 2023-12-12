import sys
import heapq


def get_node(nodes, cur):
    if cur not in nodes:
        nodes[cur] = len(nodes) + 1
    return nodes[cur]


def calc_distance(si, sj, ei, ej):
    return abs(si - ei) + abs(sj - ej)


def solve(adj, start, end):
    # 다익스트라 돌려서 구하기
    costs = [float('inf')] * 10
    costs[start] = 0

    hq = []
    heapq.heappush(hq, [0, start])

    while hq:
        cur_cost, cur_node = heapq.heappop(hq)
        # print(f"costs[cur_node]: {costs[cur_node]}, cur_cost: {cur_cost}, cur_node: {cur_node}")
        if costs[cur_node] != cur_cost: continue
        for next_node, next_cost in adj[cur_node]:
            if cur_cost + next_cost < costs[next_node]:
                costs[next_node] = cur_cost + next_cost
                heapq.heappush(hq, [costs[next_node], next_node])

    return costs[end]


def main():
    sx, sy = map(int, input().split())
    ex, ey = map(int, input().split())
    teleports = [list(map(int, input().split())) for _ in range(3)]
    nodes = {}
    adj = [[] * 10 for _ in range(10)]
    start, end = get_node(nodes, str([sx, sy])), get_node(nodes, str([ex, ey]))
    for i in range(3):
        tsx, tsy, tex, tey = teleports[i]

        # tp 이동시간, 직접 이동 시간 중 더 짧은 시간
        t_node_start, t_node_end = get_node(nodes, str([tsx, tsy])), get_node(nodes, str([tex, tey]))
        adj[t_node_start].append([t_node_end, min(10, calc_distance(tsx, tsy, tex, tey))])
        adj[t_node_end].append([t_node_start, min(10, calc_distance(tsx, tsy, tex, tey))])

        # 시작점 -> tps, tpe
        adj[start].append([t_node_start, calc_distance(sx, sy, tsx, tsy)])
        adj[start].append([t_node_end, calc_distance(sx, sy, tex, tey)])

        # tps -> 도착점
        adj[t_node_end].append([end, calc_distance(tex, tey, ex, ey)])
        adj[t_node_start].append([end, calc_distance(tsx, tsy, ex, ey)])

        for j in range(3):
            if i == j: continue
            ttsx, ttsy, ttex, ttey = teleports[j]
            tt_node_start, tt_node_end = get_node(nodes, str([ttsx, ttsy])), get_node(nodes, str([ttex, ttey]))

            adj[t_node_end].append([tt_node_start, calc_distance(tex, tey, ttsx, ttsy)])
            adj[t_node_end].append([tt_node_end, calc_distance(tex, tey, ttex, ttey)])
            adj[t_node_start].append([tt_node_start, calc_distance(tsx, tsy, ttsx, ttsy)])
            adj[t_node_start].append([tt_node_end, calc_distance(tsx, tsy, ttex, ttey)])

    # 바로 가는 거리랑 비교
    distance = min(calc_distance(sx, sy, ex, ey), solve(adj, start, end))
    print(distance)


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
