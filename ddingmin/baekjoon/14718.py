import sys


def solve(n, need_count, arr):
    ans = float('inf')

    for i in range(n):
        for j in range(n):
            for k in range(n):
                can_win_count = 0
                for idx in range(n):
                    if arr[i][0] >= arr[idx][0] and arr[j][1] >= arr[idx][1] and arr[k][2] >= arr[idx][2]:
                        can_win_count += 1
                if can_win_count >= need_count:
                    ans = min(ans, arr[i][0] + arr[j][1] + arr[k][2])

    return ans


def main():
    n, k = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(n)]
    print(solve(n, k, sorted(arr)))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
