import sys
from collections import defaultdict


def find_max_mosquito():
    mosquito_count = defaultdict(int)

    for entry in mosquito_entries:
        Te, Tx = entry
        mosquito_count[Te] += 1
        mosquito_count[Tx] -= 1

    max_mosquitoes = 0
    max_mosquitoes_time = [0, 0]
    current_mosquitoes = 0
    start_flag = False

    for time, count in sorted(mosquito_count.items()):
        current_mosquitoes += count
        if current_mosquitoes > max_mosquitoes:
            max_mosquitoes = current_mosquitoes
            max_mosquitoes_time[0] = time
            start_flag = True
        elif start_flag and current_mosquitoes < max_mosquitoes:
            max_mosquitoes_time[1] = time
            start_flag = False

    return max_mosquitoes, max_mosquitoes_time


n = int(sys.stdin.readline())
mosquito_entries = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]

max_mosquitoes, max_mosquitoes_time = find_max_mosquito()
print(max_mosquitoes)
print(*max_mosquitoes_time)
