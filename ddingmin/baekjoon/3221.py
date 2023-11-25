import sys


def move(direction, cur, l, t):
    if direction == 'L':
        temp = t - cur
        if temp < 0:
            return cur - t
        # 홀수
        if temp // l % 2 == 1:
            return l - temp % l
        else:
            return temp % l
    else:
        temp = t - (l - cur)
        if temp < 0:
            return cur + t
        if temp // l % 2 == 0:
            return l - temp % l
        else:
            return temp % l



def solve(l, t, cmd):
    answer = []
    for cur, c in cmd:
        answer.append(move(c, int(cur), l, t))
    return sorted(answer)


def main():
    l, t = map(int, input().split())
    n = int(input())
    cmd = list(input().split() for _ in range(n))
    print(*solve(l, t, cmd))


if __name__ == '__main__':
    sys.setrecursionlimit(10 ** 5)
    input = sys.stdin.readline
    main()
