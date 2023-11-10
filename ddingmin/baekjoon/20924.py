import sys
from collections import deque


def main():
    input = sys.stdin.readline
    n, root = map(int, input().split())

    # init
    answer = [None, 0]
    adj = [[] for _ in range(n + 1)]
    visit = [0] * (n + 1)
    q = deque()

    for _ in range(n - 1):
        a, b, dist = map(int, input().split())
        adj[a].append([dist, b])
        adj[b].append([dist, a])

    q.append([0, root])
    visit[root] = 1

    while q:
        cur_dist, cur = q.popleft()

        # root -> leaf 최대 거리 갱신
        answer[1] = max(answer[1], cur_dist)

        # 기가노드를 판단하기 위해 count
        count = 0
        for next_dist, next in adj[cur]:
            if visit[next]:
                continue
            q.append([cur_dist + next_dist, next])
            visit[next] = 1
            count += 1
        if count > 1 and answer[0] == None:  # 기가 노드를 식별 X 상태, 현재 가지로부터 2갈래로 퍼져나가는 경우
            # root -> 기가노드 거리 측정
            answer[0] = cur_dist

    # 기가 노드가 없는 경우 예외 처리
    if answer[0] == None:
        answer[0] = answer[1]
    print(answer[0], answer[1] - answer[0])


if __name__ == '__main__':
    main()
