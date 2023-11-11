import sys


def find_item(dungeon, c):
    for i in range(n):
        for j in range(m):
            if dungeon[i][j] == c:
                dungeon[i][j] = '0'
                return i, j
    return -1, -1


def solve():
    c = 0
    x = 0
    y = 0
    tx = 0
    ty = 0
    while True:
        for ch in s:
            nx, ny = find_item(dungeon, ch)
            if nx == -1 and ny == -1:
                tmp.clear()
                move_route(n - 1, m - 1, x, y)
                ans.extend(tmp)
                return c
            move_route(nx, ny, tx, ty)
            tx, ty = nx, ny
            tmp.append('P')
        ans.extend(tmp)
        x, y = tx, ty
        tmp.clear()
        c += 1


def move_route(nx, ny, x, y):
    dx = nx - x
    dy = ny - y
    if dx > 0:
        tmp.extend('D' * dx)
    elif dx < 0:
        tmp.extend('U' * -dx)
    if dy > 0:
        tmp.extend('R' * dy)
    elif dy < 0:
        tmp.extend('L' * -dy)


if __name__ == '__main__':
    n, m, len_s = map(int, sys.stdin.readline().split())
    dungeon = [list(sys.stdin.readline().strip()) for _ in range(n)]
    s = sys.stdin.readline().strip()
    ans = []
    tmp = []
    c = solve()
    print(c, len(ans))
    print(''.join(ans))
