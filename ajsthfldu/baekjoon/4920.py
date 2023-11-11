import sys

if __name__ == '__main__':
    tet = []
    # 1자
    tet.append([(0, 0), (0, 1), (0, 2), (0, 3)])
    tet.append([(0, 0), (1, 0), (2, 0), (3, 0)])
    # 2번꺽인 모양
    tet.append([(0, 0), (0, 1), (1, 1), (1, 2)])
    tet.append([(0, 0), (1, 0), (1, -1), (2, -1)])
    # ㄱ자
    tet.append([(0, 0), (0, 1), (0, 2), (1, 2)]) # ㄱ
    tet.append([(0, 0), (1, 0), (2, 0), (2, -1)]) #ㅢ
    tet.append([(0, 0), (1, 0), (1, 1), (1, 2)]) # ㄴ
    tet.append([(0, 0), (0, 1), (1, 0), (2, 0)])
    # ㅗ자
    tet.append([(0, 0), (0, 1), (0, 2), (1, 1)]) # ㅜ
    tet.append([(0, 0), (1, 0), (2, 0), (1, -1)]) # # ㅓ
    tet.append([(0, 0), (1, 0), (1, -1), (1, 1)]) # ㅗ
    tet.append([(0, 0), (1, 0), (2, 0), (1, 1)]) # ㅏ
    # ㅁ자
    tet.append([(0, 0), (0, 1), (1, 0), (1, 1)])
    k = 1
    while True:
        n = int(sys.stdin.readline())
        if n == 0:
            break
        graph = [list(map(int, sys.stdin.readline().split())) for _ in range(n)]
        max_sum = sum(graph[0][:4])
        for i in range(n):
            for j in range(n):
                for block in tet:
                    tmp_sum = 0
                    for point in block:
                        x = i + point[0]
                        y = j + point[1]
                        if x < 0 or x >= n or y < 0 or y >= n:
                            tmp_sum = max_sum
                            break
                        tmp_sum += graph[x][y]
                    max_sum = max(max_sum, tmp_sum)
        print(f'{k}. {max_sum}')
        k += 1
