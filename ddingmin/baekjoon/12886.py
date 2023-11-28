import sys
from collections import deque


def calc(x, y):
    if x < y:
        return x + x, y - x
    else:
        return x - y, y + y


def get_abc(i, j, k):
    result = []
    a, b = calc(i, j)
    result.append([a, b, k])
    b, c = calc(j, k)
    result.append([i, b, c])
    a, c = calc(i, k)
    result.append([a, j, c])
    return result


def solve(a, b, c):
    visit = {}
    visit[a, b, c] = True
    q = deque()
    q.append([a, b, c])

    while q:
        i, j, k = q.popleft()
        if i == j == k:
            return 1
        for a, b, c in get_abc(i, j, k):
            if (a, b, c) in visit: continue
            q.append([a, b, c])
            visit[a, b, c] = True
    return 0


def main():
    a, b, c = map(int, input().split())
    print(solve(a, b, c))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
