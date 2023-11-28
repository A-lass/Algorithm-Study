import sys


def solve(n, m, l, k, arr):
    answer = 0
    for i in range(k):
        for j in range(k):
            cnt = 0

            # 두 별을 걸치는 가장 왼쪽 상단 좌표
            sx, sy = min(arr[i][0], arr[j][0]), min(arr[i][1], arr[j][1])

            for x, y in arr:
                # 구한 좌표의 최대 범위 별 개수 구하기
                if sx <= x <= sx + l and sy <= y <= sy + l:
                    cnt += 1
            answer = max(answer, cnt)

    return k - answer


def main():
    n, m, l, k = map(int, input().split())
    arr = []
    for _ in range(k):
        arr.append(list(map(int, input().split())))
    print(solve(n, m, l, k, arr))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
