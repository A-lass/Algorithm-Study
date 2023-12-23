import sys
import heapq

dx, dy = [-1, -1, -1, 0, 0, 1, 1, 1], [-1, 0, 1, -1, 1, -1, 0, 1]


def solve(n, arr):
    if n < 3:
        return 0
    elif n == 3 and int(arr[0][0]) == 1:
        return 1

    ans = 0

    for i in range(1, n - 2):
        cnt = 0
        if arr[1][i - 1] == '*': cnt += 1
        if arr[1][i] == '*': cnt += 1

        if int(arr[0][i - 1]) > cnt:
            ans += 1
            arr[1][i] = '*'
            arr[0][i + 1] = int(arr[0][i + 1]) - 1

    for i in range(1, n - 2):
        cnt = 0
        if arr[i - 1][n - 2] == '*': cnt += 1
        if arr[i][n - 2] == '*': cnt += 1

        if int(arr[i - 1][n - 1]) > cnt:
            ans += 1
            arr[i][n - 2] = '*'
            arr[i + 1][n - 1] = int(arr[i + 1][n - 1]) - 1

    for i in range(n - 2, 1, -1):
        cnt = 0
        if arr[n - 2][i + 1] == '*': cnt += 1
        if arr[n - 2][i] == '*': cnt += 1

        if int(arr[n - 1][i + 1]) > cnt:
            ans += 1
            arr[n - 2][i] = '*'
            arr[n - 1][i - 1] = int(arr[n - 1][i - 1]) - 1

    for i in range(n - 2, 1, -1):
        cnt = 0
        if arr[i + 1][1] == '*': cnt += 1
        if arr[i][1] == '*': cnt += 1

        if int(arr[i + 1][0]) > cnt:
            ans += 1
            arr[i][1] = '*'
            arr[i - 1][0] = int(arr[i - 1][0]) - 1

    if n > 4:
        ans += (n - 4) ** 2

    return ans


def main():
    n = int(input())
    arr = [list(input().strip()) for _ in range(n)]
    print(solve(n, arr))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
