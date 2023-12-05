import sys


def solve(n, m, three, five):
    pSumThree = [0]
    pSumFive = [0]

    for w, num in three:
        pSumThree.append(pSumThree[-1] + num)
    for w, num in five:
        pSumFive.append(pSumFive[-1] + num)

    ans = 0

    for cnt in range(len(pSumThree)):
        if cnt * 3 > m: break
        availableFiveCount = min((m - cnt * 3) // 5, len(pSumFive) - 1)
        ans = max(ans, pSumThree[cnt] + pSumFive[availableFiveCount])

    return ans


def main():
    n, m = map(int, input().split())
    three, five = [], []
    for _ in range(n):
        a, b = map(int, input().split())
        if a == 3:
            three.append([a, b])
        else:
            five.append([a, b])

    print(solve(n, m, sorted(three, reverse=True), sorted(five, reverse=True)))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
