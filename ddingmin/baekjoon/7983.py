import sys


def solve(n, arr):
    ans = float('inf')
    for doing, deadline in arr:
        ans = min(ans, deadline) - doing

    return ans


def main():
    n = int(input())
    arr = sorted([list(map(int, input().split())) for _ in range(n)], key=lambda x: [-x[1]])
    print(solve(n, arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
