import sys

input = sys.stdin.readline


# input
n = int(input())
arr = list(input().strip())
change = {'R': 'G', 'G': 'B', 'B': 'R'}

# func
def change_light(idx, arr):
    arr[idx], arr[idx + 1], arr[idx + 2] = change[arr[idx]], change[arr[idx + 1]], change[arr[idx + 2]]
    return arr


def solve(new_arr):
    count = 0

    # (1 ~ n - 2)까지의 전구를 0번째 전구와 동일한 색으로 맞춰주기
    for i in range(1, n - 2):
        while new_arr[0] != new_arr[i]:
            count += 1
            new_arr = change_light(i, new_arr)
    # 0번째, -1, -2번째 전구가 모두 동일하다면 모든 전구가 동일하다.
    if new_arr[0] == new_arr[-1] == new_arr[-2]:
        return count
    return float('inf')


# solve
answer = float('inf')

# 시작 경우의 수는 3가지
for i in range(3):
    answer = min(answer, solve(arr.copy()) + i)

    # 0, 1, 2 전구 돌리기
    arr = change_light(0, arr)

if answer == float('inf'):
    answer = -1
print(answer)
