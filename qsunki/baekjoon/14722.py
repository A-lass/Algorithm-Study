import sys

n = int(sys.stdin.readline())
city = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
mem = [0] * (n + 1)
for i in range(n):
    for j in range(n):
        mem[j] = max(mem[j - 1], mem[j])
        if mem[j] % 3 == city[i][j]:
            mem[j] += 1
print(mem[n - 1])
