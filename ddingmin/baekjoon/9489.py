import sys

MAX_MODE = 1001


def node_number_generater(nodes, node):
    if node not in nodes:
        nodes[node] = len(nodes) + 1
    return nodes[node]


def solve(n, k, arr):
    adj = [[] for _ in range(MAX_MODE)]
    radj = [[] for _ in range(MAX_MODE)]
    parents = []
    parent_idx = -1
    nodes = {}

    # 인접 리스트 구현
    root_node = node_number_generater(nodes, arr[0])
    parents.append(node_number_generater(nodes, arr[0]))
    for i in range(1, n):
        cur = node_number_generater(nodes, arr[i])
        if arr[i] - 1 > arr[i - 1]:
            parent_idx += 1

        adj[parents[parent_idx]].append(cur)
        radj[cur].append(parents[parent_idx])
        parents.append(cur)

    # 찾기
    k = node_number_generater(nodes, k)
    if len(radj[k]) != 1: return 0
    father_node = radj[k][0]
    if len(radj[father_node]) != 1: return 0
    grandfather_node = radj[father_node][0]

    ans = 0
    for f_node in adj[grandfather_node]:
        if f_node == father_node: continue
        ans += len(adj[f_node])

    return ans


def main():
    while 1:
        n, k = map(int, input().split())
        if (n, k) == (0, 0): break
        arr = list(map(int, input().split()))
        print(solve(n, k, arr))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
