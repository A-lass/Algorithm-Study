import sys


def solve(n, cmds):
    answer = []
  
    # 초기 데이터 세팅
    start, end, cost = cmds[0]
  
    for s, e, c in cmds[1:]:
        if s <= end: # 구간 연결
            end = max(end, e)
            cost = min(cost, c)
        else: # 경로 추가
            answer.append([start, end, cost])
            start, end, cost = s, e, c
    # 마지막 경로 추가
    answer.append([start, end, cost])
    print(len(answer))
    for pp in answer:
        print(*pp)


def main():
    n = int(input())
    arr = [list(map(int, input().split())) for _ in range(n)]
    solve(n, sorted(arr))


if __name__ == '__main__':
    input = sys.stdin.readline
    main()
