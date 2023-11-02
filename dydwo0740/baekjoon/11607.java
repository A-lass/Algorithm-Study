import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            for(int j=0;j<M;j++){
                board[i][j] = str.charAt(j) - '0';
            }
        }

        bw.write(bfs(board) == -1 ? "IMPOSSIBLE" : bfs(board) + "\n");


        bw.flush();
    }

    public static int bfs(int[][] board){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0] == N - 1 && cur[1] == M - 1){
                return cur[2];
            }

            for(int i=0;i<4;i++){
                int nx = cur[0] + row[i] * board[cur[0]][cur[1]];
                int ny = cur[1] + col[i] * board[cur[0]][cur[1]];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (!visited[nx][ny]) {
                        queue.add(new int[]{nx, ny, cur[2] + 1});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return -1;
    }
}
