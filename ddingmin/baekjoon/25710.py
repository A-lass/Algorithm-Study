import sys

input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))


def get_dict_numbers(arr):
    result = {}
    for num in arr:
        if num not in result:
            result[num] = 0
        result[num] += 1
    return result


def get_scores(num1, num2):
    result = 0
    for num in str(num1 * num2):
        result += int(num)
    return result


def solve(n, arr):
    answer = 0

    num_counter = get_dict_numbers(arr)
    for num1 in num_counter.keys():
        for num2 in num_counter.keys():
            # 두 수가 같은 경우 2번 이상 나오지 않는 경우의 예외 처리한다.
            if num1 == num2 and num_counter[num1] == 1:
                continue
            answer = max(answer, get_scores(num1, num2))
    return answer


print(solve(n, arr))
