import sys


def solve(n, cmds):
    dic = {}

    for s, e in cmds:
        if s not in dic:
            dic[s] = 0
        if e not in dic:
            dic[e] = 0
        dic[s] += 1
        dic[e] -= 1

    ans = 0
    ans_range = [0, 0]
    prev = 0
    need_range = False

    for k in sorted(dic.keys()):
        prev += dic[k]
        if prev > ans:
            ans = prev
            ans_range[0] = k
            need_range = True
        elif need_range and dic[k] < 0:
            need_range = False
            ans_range[1] = k

    print(ans)
    print(*ans_range)
    return ans


def main():
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    solve(n, arr)


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
