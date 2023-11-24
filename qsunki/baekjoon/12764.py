import sys
from heapq import *

n = int(sys.stdin.readline())
times = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
times.sort()
seat_cnt = [0] * 100000
computer_cnt = 0
computer_queue = []
end_queue = []
for start, end in times:
    while computer_queue and start > computer_queue[0][0]:
        heappush(end_queue, tuple(reversed(heappop(computer_queue))))
    if end_queue:
        seat_num, _ = heappop(end_queue)
        heappush(computer_queue, (end, seat_num))
        seat_cnt[seat_num] += 1
    else:
        heappush(computer_queue, (end, computer_cnt))
        seat_cnt[computer_cnt] += 1
        computer_cnt += 1

print(computer_cnt)
print(*seat_cnt[:computer_cnt])
