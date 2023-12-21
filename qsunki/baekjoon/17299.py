import sys
from collections import defaultdict

n = int(sys.stdin.readline())
A = list(map(int, sys.stdin.readline().split()))
F = defaultdict(int)
stack = []
result = [-1] * n
for x in A:
    F[x] += 1
for i in range(n - 1, -1, -1):
    while stack and F[A[i]] >= F[stack[-1]]:
        stack.pop()

    if stack:
        result[i] = stack[-1]

    stack.append(A[i])

print(*result)
