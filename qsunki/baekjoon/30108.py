# 처음 시도한 방법
import sys
from heapq import *


def search(graph, values, n):
    ans = []
    pq = []
    heappush(pq, (-values[1], 1))
    sum_ = 0
    while n > 0:
        value, parent = heappop(pq)
        sum_ += value
        for child in graph[parent]:
            heappush(pq, (-values[child], child))
        ans.append(-sum_)
        n -= 1
    return ans


def main():
    n = int(sys.stdin.readline())
    graph = [[] for _ in range(n + 1)]
    p = [0, 0] + list(map(int, sys.stdin.readline().split()))
    for i in range(2, n + 1):
        graph[p[i]].append(i)
    values = [0] + list(map(int, sys.stdin.readline().split()))
    ans = search(graph, values, n)
    print('\n'.join(map(str, ans)))


main()

# 두 번째 방법
import sys
from itertools import accumulate

sys.stdin.readline()
sys.stdin.readline()
acc = accumulate(sorted(map(int, sys.stdin.readline().split()), reverse=True))
print('\n'.join(map(str, acc)))
