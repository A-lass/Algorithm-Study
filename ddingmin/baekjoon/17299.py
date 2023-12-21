import sys

MAX_VALUE = 1_000_000


def solve(n, arr):
    # 갯수 배열로 변환
    cnt = {}

    for i in arr:
        if i not in cnt:
            cnt[i] = 0
        cnt[i] += 1

    cnt_arr = []
    for i in range(n):
        cnt_arr.append([cnt[arr[i]], i])

    ans = [0] * n

    stack = []

    # 뒤에서부터 스택으로 해결
    for new, idx in cnt_arr[::-1]:
        # 새로 들어오는 값이 더 큰 경우
        if stack and stack[-1][0] < new:
            # 값이 더 큰 값이 없을 때 까지 pop
            while stack and stack[-1][0] < new:
                stack.pop()

        # 스택이 없는 경우는 그냥 집어넣고 ans = -1
        if not stack:
            ans[idx] = -1
            stack.append([new, idx])
        # 스택의 가장 상단값보다 새로 들어오는 값이 작은 경우 ans는 최상단의 실제 값
        elif stack and stack[-1][0] > new:
            prev, prev_idx = stack[-1]
            ans[idx] = arr[prev_idx]
            stack.append([new, idx])
        # 스택의 가장 상단값과 새로 들어오는 값이 같은 경우 ans는 최상단의 정답 값
        elif stack and stack[-1][0] == new:
            prev, prev_idx = stack[-1]
            ans[idx] = ans[prev_idx]
            stack.append([new, idx])

    print(*ans)
    return 0


def main():
    n = int(input())
    arr = list(map(int, input().split()))
    solve(n, arr)


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
