import sys


def solve(n, arr):
    # 넓이, 무게, 번호, 높이
    lis = [0] * n
    ans = [0] * n
    top = [0] * n

    for i in range(n):
        lis[i] = 1
        ans[i] = [arr[i][2]]
        top[i] = arr[i][3]
        for j in range(i):
            if arr[j][1] > arr[i][1]:
                if top[i] < top[j] + arr[i][3]:
                    top[i] = top[j] + arr[i][3]
                    lis[i] = lis[j] + 1
                    ans[i] = ans[j] + [arr[i][2]]

    answer = [0, 0, []]
    for i in range(n):
        if answer[0] < top[i]:
            answer = [top[i], lis[i], ans[i]]

    print(answer[1])
    for pp in answer[2][::-1]:
        print(pp)
    return 0


def main():
    n = int(input())
    arr = []
    for idx in range(1, n + 1):
        a, b, c = map(int, input().split())
        arr.append([a, c, idx, b])
    solve(n, sorted(arr, key=lambda x: [-x[0], x[1]]))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
