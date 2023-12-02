import java.io.*;
import java.util.*;

public class Main {

    static int[][] board;

    static boolean[][] fire;
    static Map<Integer, List<int[]>> map = new HashMap<>();
    static int N;
    static int M;
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};

    static int cost = -1;
    static int height = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        int V = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());

        board = new int[N + 1][M + 1];
        fire = new boolean[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            fire[x][y] = true;

            if (map.containsKey(time)) {
                List<int[]> list = map.get(time);
                list.add(new int[]{x, y});
            } else {
                List<int[]> list = new ArrayList<>();
                list.add(new int[]{x, y});
                map.put(time, list);
            }
        }

        bfs(startX, startY);


        bw.write(height + " " + cost + "\n");

        bw.flush();
    }

    public static void bfs(int x, int y) {
        boolean[][] visited = new boolean[N + 1][M + 1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        height = board[x][y];
        cost = 0;
        int time = 0;

        while (!queue.isEmpty()) {
            Queue<int[]> temp = new LinkedList<>(queue);
            queue.clear();
            volcano();
            if (map.containsKey(time)) {
                List<int[]> list = map.get(time);
                for (int[] loc : list) {
                    board[loc[0]][loc[1]] = -1;
                }
            }

            /*for(int i=1;i<=N;i++){
                for(int j=1;j<=M;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }*/
            while (!temp.isEmpty()) {
                int[] cur = temp.poll();

                if (board[cur[0]][cur[1]] == -1) {
                    continue;
                }

                if (height < board[cur[0]][cur[1]]) {
                    height = board[cur[0]][cur[1]];
                    cost = time;
                }



                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + row[i];
                    int ny = cur[1] + col[i];

                    if (1 <= nx && nx <= N && 1 <= ny && ny <= M) {
                        if (!visited[nx][ny] && board[nx][ny] != -1 && !fire[nx][ny]) {
                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }
                }
            }

            time++;
        }
    }

    public static void volcano() {
        boolean[][] visited = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i][j] == -1 && !visited[i][j]) {
                    visited[i][j] = true;
                    
                    for (int k = 0; k < 4; k++) {
                        int nx = i + row[k];
                        int ny = j + col[k];
                        if (1 <= nx && nx <= N && 1 <= ny && ny <= M) {
                            if (board[nx][ny] == -1) {
                                continue;
                            }
                            visited[nx][ny] = true;
                            board[nx][ny] = -1;
                        }
                    }
                }
            }
            //System.out.println();
        }
    }


}
