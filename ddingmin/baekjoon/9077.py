import sys

MAX_RANGE = 10001


def main():
    t = int(input())
    arr = [[0] * MAX_RANGE for _ in range(MAX_RANGE)]

    for _ in range(t):
        n = int(input())
        ans = 0
        for _ in range(n):
            x, y = map(int, input().split())
            for i in range(x, min(x + 11, MAX_RANGE)):
                for j in range(y, min(y + 11, MAX_RANGE)):
                    arr[i][j] += 1
                    ans = max(ans, arr[i][j])

        for i in range(MAX_RANGE):
            for j in range(MAX_RANGE):
                arr[i][j] = 0

        print(ans)


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
9077
