import sys

input = sys.stdin.readline

n, q = map(int, input().split())
arr = list(map(int, input().split()))


def find_x(arr, value):
    left, right = 0, len(arr)
    while left + 1 < right:
        mid = (left + right) // 2
        if arr[mid] < value:
            right = mid
        else:
            left = mid
    return right


for _ in range(q):
    x, y = map(int, input().split())
    answer = max(0, arr[y - 1] - x + 1) + max(0, find_x(arr, x) - y)
    print(answer)
