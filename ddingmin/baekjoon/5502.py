import sys


def solve(n, word, reverse_word):
    lcs = [[0] * (n + 1) for _ in range(n + 1)]

    for i in range(1, n + 1):
        for j in range(1, n + 1):
            if word[i] == reverse_word[j]:
                lcs[i][j] = lcs[i - 1][j - 1] + 1
            else:
                lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1])

    return n - lcs[-1][-1]


def main():
    n = int(input())
    word = input().strip()
    reverse_word = word[::-1]
    print(solve(n, '0' + word, '0' + reverse_word))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
