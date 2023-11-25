import sys

L, T = map(int, sys.stdin.readline().split())
n = int(sys.stdin.readline())
ants_loc = []
for _ in range(n):
    loc, direction = sys.stdin.readline().split()
    loc = int(loc)
    if direction == 'D':
        t = T + loc
    else:
        t = T + 2 * L - loc
    remain = t % (2 * L)
    if remain <= L:
        loc = remain
    else:
        loc = 2 * L - remain
    ants_loc.append(loc)

print(*sorted(ants_loc), sep=' ')
