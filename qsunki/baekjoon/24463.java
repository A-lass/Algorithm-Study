import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String[] split;
    static String line;

    static int n;
    static int m;
    static char[][] miro;
    static int startX;
    static int startY;
    static int endX;
    static int endY;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        split = br.readLine().split(" ");
        n = Integer.parseInt(split[0]);
        m = Integer.parseInt(split[1]);
        miro = new char[n][];
        visited = new boolean[n][m];
        boolean entranceFlag = false;
        for (int i = 0; i < n; ++i) {
            miro[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; ++j) {
                if (!entranceFlag && isEntrance(i, j)) {
                    startX = i;
                    startY = j;
                    entranceFlag = true;
                } else if (entranceFlag && isEntrance(i, j)) {
                    endX = i;
                    endY = j;
                }
                if (miro[i][j] == '.') {
                    miro[i][j] = '@';
                }
            }
        }
        dfs(startX, startY);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                sb.append(miro[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean isEntrance(int x, int y) {
        return (x == 0 || x == n - 1 || y == 0 || y == m - 1) && miro[x][y] == '.';
    }

    static boolean dfs(int x, int y) {
        visited[x][y] = true;
        if (x == endX && y == endY) {
            miro[x][y] = '.';
            return true;
        }
        boolean escapable = false;
        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny] || miro[nx][ny] != '@') {
                continue;
            }
            escapable |= dfs(nx, ny);
        }
        if (escapable) {
            miro[x][y] = '.';
        }
        return escapable;
    }

}
