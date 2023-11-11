import sys


def main():
    n = int(sys.stdin.readline())
    graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
    h, w = 2, 2
    total = 0
    ans = []
    # x, y
    init_point = {
        'U': [n // 2 - 2, n // 2 - 1],
        'D': [n // 2 + 1, n // 2 - 1],
        'L': [n // 2 - 1, n // 2 - 2],
        'R': [n // 2 - 1, n // 2 + 1]
    }
    while True:
        max_score = 0
        max_sym = ''
        num_extensible = 4
        for sym, (x, y) in init_point.items():
            tmp_sum = 0
            if x < 0 or x >= n or y < 0 or y >= n:
                num_extensible -= 1
                continue
            if sym == 'U' or sym == 'D':
                tmp_sum = sum(graph[x][y:y + w])
            else:
                for i in range(h):
                    tmp_sum += graph[x + i][y]
            if tmp_sum <= 0:
                num_extensible -= 1
                continue
            if tmp_sum > max_score:
                max_sym = sym
                max_score = tmp_sum
        if num_extensible == 0:
            break
        if max_sym == 'U':
            h += 1
            init_point['L'][0] -= 1
            init_point['R'][0] -= 1
            init_point['U'][0] -= 1
        elif max_sym == 'D':
            h += 1
            init_point['D'][0] += 1
        elif max_sym == 'L':
            w += 1
            init_point['U'][1] -= 1
            init_point['D'][1] -= 1
            init_point['L'][1] -= 1
        else:
            w += 1
            init_point['R'][1] += 1
        total += max_score
        ans.append(max_sym)

    print(total)
    print(''.join(ans))


if __name__ == '__main__':
    main()
