import sys


def find(parents, cur):
    if parents[cur] == -1:
        return -1
    if parents[cur] == cur:
        return cur
    parents[cur] = find(parents, parents[cur])
    return parents[cur]


def union(parents, nodes, a, b):
    ap = find(parents, a)
    bp = find(parents, b)
    parents[bp] = ap
    nodes[ap] += nodes[bp]
    nodes[bp] = 0


def fight(parents, nodes, a, b):
    ap = find(parents, a)
    bp = find(parents, b)
    if nodes[ap] < nodes[bp]:
        ap, bp = bp, ap
    elif nodes[ap] == nodes[bp]:
        parents[ap], parents[bp] = -1, -1
    parents[bp] = ap
    nodes[ap] -= nodes[bp]
    nodes[bp] = 0


def solve(n, m, nodes, parents, cmds):
    for cmd, p, q in cmds:
        p, q = p - 1, q - 1
        if cmd == 1:
            union(parents, nodes, p, q)
        else:
            fight(parents, nodes, p, q)
    visit = [0] * n
    answer = []
    for node in range(n):
        p = find(parents, node)
        if p == -1 or visit[p]: continue
        visit[p] = 1
        answer.append(nodes[p])

    ans = str(len(answer)) + "\n"
    ans += " ".join(map(str, sorted(answer)))
    return ans


def main():
    n, m = map(int, input().split())
    nodes = []
    parents = []
    for i in range(n):
        nodes.append(int(input()))
        parents.append(i)
    cmds = [list(map(int, input().split())) for _ in range(m)]
    print(solve(n, m, nodes, parents, cmds))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
