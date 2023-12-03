import sys

n = int(sys.stdin.readline())
assignments = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
assignments.sort(key=lambda x: x[1], reverse=True)
do_day = assignments[0][1]
for d, t in assignments:
    if t <= do_day:
        do_day = t - d
    else:
        do_day = do_day - d
print(do_day)
