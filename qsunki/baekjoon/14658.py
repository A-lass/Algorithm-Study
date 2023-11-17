import sys

n, m, l, k = map(int, sys.stdin.readline().split())
stars = [list(map(int, sys.stdin.readline().split())) for _ in range(k)]
max_cnt = 0

for star1 in stars:
    for star2 in stars:
        cnt = 0
        for star in stars:
            if star1[0] <= star[0] <= star1[0] + l and star2[1] <= star[1] <= star2[1] + l:
                cnt += 1
        max_cnt = max(max_cnt, cnt)

print(k - max_cnt)
