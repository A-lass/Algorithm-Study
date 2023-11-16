import org.w3c.dom.Node;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int M;
    static char[][] board;

    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int ans = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        board = new char[N + 4][M + 4];

        for(int i=0;i<N+2;i++){
            Arrays.fill(board[i], 'D');
        }

        int[][] cur = new int[2][2];

        int index = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < M; j++) {
                board[i + 1][j + 1] = str.charAt(j);
                if (board[i + 1][j + 1] == 'o') {
                    board[i + 1][j + 1] = '.';
                    cur[index][0] = i + 1;
                    cur[index][1] = j + 1;
                    index++;
                }
            }
        }

        int x1 = cur[0][0];
        int y1 = cur[0][1];

        int x2 = cur[1][0];
        int y2 = cur[1][1];

        dfs(x1, y1, x2, y2, 0);

        bw.write(((ans == Integer.MAX_VALUE) ? -1 : ans) +"\n");


        bw.flush();
    }

    public static void dfs(int x1, int y1, int x2, int y2, int depth) {
        if(depth > 10){
            return;
        }
        if ((board[x1][y1] == 'D' && board[x2][y2] != 'D') || (board[x1][y1] != 'D' && board[x2][y2] == 'D')) {

            ans = Math.min(ans, depth);
            return;
        } else if (board[x1][y1] == 'D' && board[x2][y2] == 'D') {
            return;
        }

        for(int i=0;i<4;i++){
            int nx1 = x1 + row[i];
            int ny1 = y1 + col[i];
            int nx2 = x2 + row[i];
            int ny2 = y2 + col[i];

            if (board[nx1][ny1] == '#') {
                nx1 = x1;
                ny1 = y1;
            }
            if (board[nx2][ny2] == '#') {
                nx2 = x2;
                ny2 = y2;
            }


            dfs(nx1, ny1, nx2, ny2, depth + 1);
        }
    }

}
