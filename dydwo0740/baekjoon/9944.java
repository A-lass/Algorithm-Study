import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static char[][] board;
    static int dot = 0;
    static int min = Integer.MAX_VALUE;
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        String input = "";
        int test = 1;
        while ((input = br.readLine()) != null) {

            st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new char[N][M];
            min = Integer.MAX_VALUE;
            dot = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String str = st.nextToken();
                for (int j = 0; j < M; j++) {
                    board[i][j] = str.charAt(j);
                    if (board[i][j] == '.') {
                        dot++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '.') {
                        boolean[][] visited = new boolean[N][M];
                        visited[i][j] = true;
                        dfs(i, j, 1, 0, visited);
                        visited[i][j] = false;
                    }
                }
            }

            if (min == Integer.MAX_VALUE) {
                System.out.println("Case " + test + ": " + -1);
            } else{
                System.out.println("Case " + test + ": " + min);
            }

            test++;
        }


        bw.flush();
    }

    public static void dfs(int x, int y, int cnt, int depth, boolean[][] visited) {
        if (cnt == dot) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + row[i];
            int ny = y + col[i];
            int count = 0;
            // 계속해서 움직이게 하는
            while (isTrue(nx, ny) && !visited[nx][ny] && board[nx][ny] == '.') {
                visited[nx][ny] = true;
                count++;
                nx += row[i];
                ny += col[i];

            }

            nx -= row[i];
            ny -= col[i];

            if (nx == x && ny == y) {
                continue;
            }
            //System.out.println(nx + " " + ny + " " + cnt + " " + count);

            dfs(nx, ny, cnt + count, depth + 1, visited);

            for (int d = 1; d <= count; d++) {
                nx = x + row[i] * d;
                ny = y + col[i] * d;
                visited[nx][ny] = false;
            }

        }

    }

    public static boolean isTrue(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) {
            return true;
        }

        return false;
    }

}
