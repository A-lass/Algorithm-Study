import sys


def main():
    n, q = map(int, sys.stdin.readline().split())
    ans = []
    a = [0] + list(map(int, sys.stdin.readline().split()))
    for i in range(q):
        x, y = map(int, sys.stdin.readline().split())
        if x > a[y]:
            ans.append('0')
            continue
        cnt = 1
        cnt += a[y] - x
        start = y
        end = n
        while start <= end:
            mid = (start + end) // 2
            if a[mid] < x:
                end = mid - 1
            else:
                start = mid + 1
        cnt += end - y
        ans.append(str(cnt))
    print('\n'.join(ans))


if __name__ == '__main__':
    main()
