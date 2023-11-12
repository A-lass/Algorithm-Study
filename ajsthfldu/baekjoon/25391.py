import sys


def main():
    n, m, k = map(int, sys.stdin.readline().split())
    score = [tuple(map(int, sys.stdin.readline().split())) for _ in range(n)]
    a_sort = sorted(score, reverse=True)
    b_sort = sorted(score, key=lambda x: x[1], reverse=True)
    selected = set(b_sort[:k])
    i = 0
    j = 0
    while j < m:
        if a_sort[i] in selected:
            i += 1
            continue
        selected.add(a_sort[i])
        j += 1
    print(sum([a for a, b in selected]))


main()
