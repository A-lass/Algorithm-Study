import java.io.*;
import java.util.*;

public class Main {
    static boolean flag = false;
    static int ans = 0;
    static int N;
    static int M;

    static int[][] col = {{-1, 0}, {-1, 0}, {0, 1}, {0, 1}};
    static int[][] row = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, new boolean[N][M], 0, 0);

        bw.write(ans + "\n");
        bw.flush();
    }

    public static void dfs(int[][] board, boolean[][] visited, int x, int sum) {
        ans = Math.max(ans, sum);



        for (int i = x; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    for (int s = 0; s < 4; s++) {
                        int nx1 = i + row[s][0];
                        int ny1 = j + col[s][0];
                        int nx2 = i + row[s][1];
                        int ny2 = j + col[s][1];

                        if (0 <= nx1 && nx1 < N && 0 <= ny1 && ny1 < M) {
                            if (0 <= nx2 && nx2 < N && 0 <= ny2 && ny2 < M) {
                                if (!visited[nx1][ny1] && !visited[nx2][ny2]) {
                                    visited[i][j] = true;
                                    visited[nx1][ny1] = true;
                                    visited[nx2][ny2] = true;
                                    //System.out.println("s + \" \" + i + \" \"  + j = " + s + " " + i + " " + j + " " + (sum + board[i][j] * 2 + board[nx1][ny1] + board[nx2][ny2]));
                                    dfs(board, visited, i, sum + board[i][j] * 2 + board[nx1][ny1] + board[nx2][ny2]);
                                    visited[i][j] = false;
                                    visited[nx1][ny1] = false;
                                    visited[nx2][ny2] = false;
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}
