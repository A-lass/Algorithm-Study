import sys
import heapq


def solve(n, arr):
    ans = []
    hq = []
    empty = []

    for start, end in arr:
        # 이용자 정리
        while hq and hq[0][0] <= start:
            t, seat = heapq.heappop(hq)
            heapq.heappush(empty, seat)

        # 남아있는 자리가 없는 경우
        if len(empty) == 0:
            ans.append(1)
            heapq.heappush(hq, [end, len(ans) - 1])
        else:
            seat = heapq.heappop(empty)
            heapq.heappush(hq, [end, seat])
            ans[seat] += 1

    return ans


def main():
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    ans = solve(n, sorted(arr))
    print(len(ans))
    print(*ans)


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
