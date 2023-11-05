import sys


def main():
    n = int(sys.stdin.readline())
    a = list(map(int, sys.stdin.readline().split()))
    cnt = [0] * 1000
    b = []
    for x in a:
        if cnt[x] == 0 or cnt[x] == 1:
            b.append(x)
            cnt[x] += 1
    selected = [False] * 1000
    computed = [False] * (999 * 999 + 1)
    max_score = 0
    for i in range(len(b) - 1):
        if selected[b[i]]:
            continue
        for j in range(i + 1, len(b)):
            mul = b[i] * b[j]
            if computed[mul]:
                continue
            score = sum(map(int, str(mul)))
            max_score = max(max_score, score)
            computed[mul] = True
        selected[b[i]] = True
    print(max_score)


if __name__ == '__main__':
    main()
