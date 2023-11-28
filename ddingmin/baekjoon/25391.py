import sys


def solve(n, m, k, arr):
    answer = 0

    arr.sort(key=lambda x: [-x[1], -x[0]])
    new_arr = []

    for i in range(k):
        a, b = arr[i]
        answer += a

    for i in range(k, n):
        a, b = arr[i]
        new_arr.append([a, b])

    new_arr.sort(key=lambda x: [-x[0], -x[1]])

    for i in range(m):
        a, b = new_arr[i]
        answer += a

    return answer


def main():
    input = sys.stdin.readline
    n, m, k = map(int, input().split())
    arr = []

    for idx in range(n):
        a, b = map(int, input().split())
        arr.append([a, b])

    print(solve(n, m, k, arr))


if __name__ == '__main__':
    main()
