import sys

MOD = 10000


def solve(n, m, pastas):
    dp = [[0] * 3 for _ in range(n + 1)]

    # 1일차 세팅
    if pastas[1] is None:
        dp[1] = [1, 1, 1]
    else:
        dp[1][pastas[1]] = 1
    # 2일차 세팅
    if pastas[2] is not None:
        dp[2][pastas[2]] = sum(dp[1])
    else:
        for t in range(3):
            dp[2][t] = sum(dp[1])

    for day in range(3, n + 1):
        for t in range(3):
            # print(f"dp[day - 1]: {dp[day - 1]} day[day - 1][t] + sum(dp[day - 2]) - dp[day - 2][t]) % MOD")
            if dp[day - 1][t] == 0:
                dp[day][t] = sum(dp[day - 1]) % MOD
            else:
                dp[day][t] = (
                                     sum(dp[day - 1]) - dp[day - 1][t] +
                                     sum(dp[day - 2]) - dp[day - 2][t]
                             ) % MOD

        if pastas[day] is not None:
            dp[day][(pastas[day] + 1) % 3] = 0
            dp[day][(pastas[day] + 2) % 3] = 0

    ans = sum(dp[n]) % MOD
    return ans


def main():
    n, m = map(int, input().split())
    pastas = [None] * (n + 1)

    for _ in range(m):
        day, p = map(int, input().split())
        pastas[day] = p - 1

    print(solve(n, m, pastas))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
