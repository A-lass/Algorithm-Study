import java.io.*;
import java.util.*;

public class Main {

    static int[] rowOdd = {0, 1, 1, 1, 0, -1};
    static int[] colOdd = {-1, -1, 0, 1, 1, 0};
    static int[] rowEven = {-1, 0, 1, 0, -1, -1};
    static int[] colEven = {-1, -1, 0, 1, 1, 0};
    static int N;
    static int M;
    static List<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());

        N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        int minus = 0;

        boolean[][] visited = new boolean[N + 1][M + 1];

        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                if(board[i][j] == 1){
                   ans += getCoverEdge(board, i, j);
                } else{
                    list = new ArrayList<>();
                    //System.out.println("i + \" \" + j = " + i + " " + j);
                    if (bfs(i, j, board) && !visited[i][j]) {
                        //bw.write(i + " " + j + " 입니다.\n");
                        for (int k = 0; k < list.size(); k++) {
                            int[] index = list.get(k);
                            visited[index[0]][index[1]] = true;
                            minus += (6 - getCoverEdge(board, index[0], index[1]));
                        }
                    }
                }
            }
        }


        bw.write((ans - minus) + "\n");

        bw.flush();

    }

    public static int[] changeIndex(int x, int y) {
        return new int[]{y, x};
    }

    public static int getCoverEdge(int[][] board, int x, int y) {
        int[] index = changeIndex(x, y);

        x = index[0];
        y = index[1];

        int cnt = 0;

        if(y % 2==0){
            for (int i = 0; i < 6; i++) {
                int nx = x + rowEven[i];
                int ny = y + colEven[i];

                index = changeIndex(nx, ny);

                if (1 <= index[0] && index[0] <= N && 1 <= index[1] && index[1] <= M) {
                    if (board[index[0]][index[1]] == 0) {
                        cnt++;
                    }
                } else{
                    cnt++;
                }
            }

            return cnt;
        }

        for (int i = 0; i < 6; i++) {
            int nx = x + rowOdd[i];
            int ny = y + colOdd[i];

            index = changeIndex(nx, ny);

            if (1 <= index[0] && index[0] <= N && 1 <= index[1] && index[1] <= M) {
                if (board[index[0]][index[1]] == 0) {
                    cnt++;
                }
            } else{
                cnt++;
            }
        }

        return cnt;
    }

    public static boolean bfs(int x, int y, int[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][M + 1];

        list.add(new int[]{x, y});
        visited[x][y] = true;
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == 1 || cur[0] == N || cur[1] == 1 || cur[1] == M) {
                return false;
            }

            int[] index = changeIndex(cur[0], cur[1]);

            for(int i=0;i<6;i++){
                if(index[1] % 2 == 0){
                    int nx = index[0] + rowEven[i];
                    int ny = index[1] + colEven[i];

                    int[] next = changeIndex(nx, ny);

                    if (!visited[next[0]][next[1]] && board[next[0]][next[1]] == 0) {
                        list.add(new int[]{next[0], next[1]});
                        queue.add(new int[]{next[0], next[1]});
                        visited[next[0]][next[1]] = true;
                    }
                } else{
                    int nx = index[0] + rowOdd[i];
                    int ny = index[1] + colOdd[i];

                    int[] next = changeIndex(nx, ny);

                    if (!visited[next[0]][next[1]] && board[next[0]][next[1]] == 0) {
                        list.add(new int[]{next[0], next[1]});
                        queue.add(new int[]{next[0], next[1]});
                        visited[next[0]][next[1]] = true;
                    }
                }
            }
        }

        return true;
    }
}
