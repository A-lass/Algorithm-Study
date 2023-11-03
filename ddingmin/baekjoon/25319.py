import sys

sys.setrecursionlimit(10 ** 5)
input = sys.stdin.readline

# input
n, m, r = map(int, input().split())
alphabets = {}
arr = [list(input().strip()) for _ in range(n)]

# 이동하기 위해 알파벳을 키로 갖는 좌표들을 저장한다.
for i in range(n):
    for j in range(m):
        if arr[i][j] not in alphabets:
            alphabets[arr[i][j]] = [[i, j]]
        else:
            alphabets[arr[i][j]].append([i, j])

target_id = input().strip()

# 목표 id에 대한 갯수를 저장한다.
target_id_count = {}

for i in target_id:
    if i not in target_id_count:
        target_id_count[i] = 1
    else:
        target_id_count[i] += 1


# func
def calculate_can_levelUp():
    # 레벨업 할 수 있는 최대치를 계산한다.
    result = float('inf')

    for k in target_id_count:
        if k not in alphabets:
            return 0
        else:
            result = min(len(alphabets[k]) // target_id_count[k], result)
    return result


def get_cmd(i, j, x, y):
    result = ""
    if i > x:
        result += "U" * (i - x)
    else:
        result += "D" * (x - i)

    if j > y:
        result += "L" * (j - y)
    else:
        result += "R" * (y - j)
    return result + "P"


def backtracking(i, j, need_idx, words):
    global ans
    # 갱신되는 경우
    if need_idx and need_idx % len(target_id) == 0:
        ans = [need_idx // len(target_id), words + get_cmd(i, j, n - 1, m - 1)[:-1]]  # 도착 지점까지 이동할 경우 P 명령어는 제거

        if ans[0] == can_levelUp:  # 최대 레벨업에 도달하면 출력 후 종료
            print(ans[0], len(ans[1]))
            print(ans[1])
            exit()

    # 목표한 다음 위치의 좌표로 이동
    for x, y in alphabets[target_id[need_idx % len(target_id)]]:
        if ate[x][y]:
            continue
        ate[x][y] = 1
        backtracking(x, y, need_idx + 1, words + get_cmd(i, j, x, y))  # 이동 지점까지 명령어를 더 붙여준다.
        ate[x][y] = 0


# solve
can_levelUp = calculate_can_levelUp()
ans = [0, ""]

if can_levelUp:
    ate = [[0] * m for _ in range(n)]
    backtracking(0, 0, 0, "")
else: # 레벨업 할 수 없는 경우
    ans = [0, get_cmd(0, 0, n - 1, m - 1)[:-1]] # 시작 지점 부터 오른쪽 가장 하단까지 이동

print(ans[0], len(ans[1]))
print(ans[1])
