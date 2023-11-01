import sys


def solve():
    if n == 1:
        return 0
    if n == 2:
        if color[0] == color[1]:
            return 0
        return -1
    if n == 3:
        if color[0] == color[1] and color[0] == color[2]:
            return 0
        else:
            return -1
    cnt = [0, 0, 0]
    color1 = color.copy()
    cnt[0] = change_light(color1, 0)
    color2 = color.copy()
    ch(color2, 0, 1)
    cnt[1] = change_light(color2, 1)
    color3 = color.copy()
    ch(color3, 0, 2)
    cnt[2] = change_light(color3, 2)
    cnt = [x for x in cnt if x != -1]
    return min(cnt)


def change_light(color, init):
    ans = init
    before = color[0]
    for i in range(1, n - 2):
        if color[i] == before:
            continue
        diff = 1 if (color[i] + 1) % 3 == before else 2
        ans += diff
        ch(color, i, diff)
    if color[-1] != color[-2] or color[-1] != color[-3]:
        return -1
    return ans


def ch(color, i, diff):
    color[i] = (color[i] + diff) % 3
    color[i + 1] = (color[i + 1] + diff) % 3
    color[i + 2] = (color[i + 2] + diff) % 3


if __name__ == '__main__':
    n = int(sys.stdin.readline())
    color = sys.stdin.readline().strip()
    dic = {'R': 0, 'G': 1, 'B': 2}
    color = list(map(dic.get, color))
    ans = solve()
    print(ans)
