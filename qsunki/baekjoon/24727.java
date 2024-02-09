import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[][] space = new int[n][n];
        split = br.readLine().split(" ");
        int c = Integer.parseInt(split[0]);
        int e = Integer.parseInt(split[1]);
        if (n * n < c + e) {
            System.out.println(-1);
            return;
        }
        int cycle = 0;
        int fillCnt = 0;
        int cCnt = 0;
        int xStart = 0;
        int yStart = 0;
        outer:
        while (cCnt < c) {
            for (int j = 0; j <= fillCnt; ++j) {
                space[xStart + j][yStart - j] = 1;
                ++cCnt;
                if (cCnt == c) {
                    break outer;
                }
            }
            ++cycle;
            if (cycle < n) {
                fillCnt = cycle;
                ++yStart;
            } else {
                fillCnt = 2 * n - cycle - 2;
                ++xStart;
            }
        }
        cycle = 0;
        fillCnt = 0;
        int eCnt = 0;
        xStart = n - 1;
        yStart = n - 1;
        outer:
        while (eCnt < e) {
            for (int j = 0; j <= fillCnt; ++j) {
                int cx = xStart - j;
                int cy = yStart + j;
                space[cx][cy] = 2;
                int ny = cy - 1;
                if (ny >= 0) {
                    if (space[cx][ny] == 1) {
                        System.out.println(-1);
                        return;
                    }
                }
                int nx = cx - 1;
                if (nx >= 0) {
                    if (space[nx][cy] == 1) {
                        System.out.println(-1);
                        return;
                    }
                }
                ++eCnt;
                if (eCnt == e) {
                    break outer;
                }
            }
            ++cycle;
            if (cycle < n) {
                fillCnt = cycle;
                --yStart;
            } else {
                fillCnt = 2 * n - cycle - 2;
                --xStart;
            }
        }
        //
        sb.append(1).append("\n");
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; j++) {
                sb.append(space[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
