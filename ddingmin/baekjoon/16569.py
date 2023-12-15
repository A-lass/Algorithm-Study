import sys
import heapq
from collections import deque

dx, dy = [-1, 1, 0, 0], [0, 0, -1, 1]


def flow_fire(fire_q, fire, n, m):
    # 화산이 흐른다.
    for idx in range(len(fire_q)):
        i, j = fire_q[idx]
        for k in range(4):
            x, y = i + dx[k], j + dy[k]
            if not (0 <= x < n and 0 <= y < m): continue
            if not fire[x][y]:
                fire[x][y] = 1
                fire_q.append([x, y])


def hole_start(hole, now, q, fire):
    # 시간이 다 된 화산 분출구를 q에 넣는다.
    while hole and hole[0][0] < now:
        t, x, y = heapq.heappop(hole)
        q.append([x, y])
        fire[x][y] = 1



def solve(n, m, v, sx, sy, highs, fire, hole, visit):
    ans = (highs[sx][sy], 0)
    visit[sx][sy] = 1
    now = 0
    fire_q = deque()
    people_q = deque()
    people_q.append([sx, sy])
    hole_start(hole, now, fire_q, fire)
    while people_q:
        now += 1
        hole_start(hole, now, fire_q, fire)
        flow_fire(fire_q, fire, n, m)

        # print(f"{now}시간의 화산 상황")
        # for pp in fire:
        #     print(*pp)
        # print()
        # 
        # print(f"{now}시간의 이동 상황")
        # for pp in visit:
        #     print(*pp)
        # print()
        #
        # print(f"정답: {ans}")
        # print(f"이동 큐: {people_q}")

        for _ in range(len(people_q)):
            cx, cy = people_q.popleft()
            for k in range(4):
                nx, ny = cx + dx[k], cy + dy[k]
                if not (0 <= nx < n and 0 <= ny < m): continue
                if fire[nx][ny] or visit[nx][ny]: continue
                visit[nx][ny] = now
                people_q.append([nx, ny])
                # 정답 갱신
                if ans[0] < highs[nx][ny]:
                    ans = (highs[nx][ny], now)

    return ans


def main():
    n, m, v = map(int, input().split())
    sx, sy = map(int, input().split())
    highs = [list(map(int, input().split())) for _ in range(n)]
    visit = [[0] * m for _ in range(n)]
    fire = [[0] * m for _ in range(n)]  # 이동할 수 없는 지역
    hole = []
    for _ in range(v):
        x, y, t = map(int, input().split())
        y, x = y - 1, x - 1
        visit[x][y] = 1
        heapq.heappush(hole, [t, x, y])
    print(*solve(n, m, v, sx - 1, sy - 1, highs, fire, hole, visit))


if __name__ == '__main__':
    # sys.setrecursionlimit(10 ** 6)
    input = sys.stdin.readline
    main()
