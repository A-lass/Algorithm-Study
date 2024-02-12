import java.io.*;
import java.util.*;

public class Main {
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<int[]> partA = new ArrayList<>();
        List<int[]> partB = new ArrayList<>();

        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        boolean[][] visited = new boolean[N][M];
        boolean flag = true;

        for(int i=0;i<N;i++){
            for (int j = 0; j < M; j++) {
                if(board[i][j] == 'X' && !visited[i][j]){
                    if(flag){
                        bfs(board, visited, i, j, partA);
                        flag = false;
                    } else{
                        bfs(board, visited, i, j, partB);
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < partA.size(); i++) {
            int[] compA = partA.get(i);
            for (int j = 0; j < partB.size(); j++) {
                int[] compB = partB.get(j);

                min = Math.min(min, Math.abs(compA[0] - compB[0]) + Math.abs(compA[1] - compB[1]));
            }
        }

        bw.write(min - 1 + "\n");
        bw.flush();
    }

    public static void bfs(char[][] board, boolean[][] visited, int x, int y, List<int[]> part) {
        Queue<int[]> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new int[]{x, y});

        part.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + row[i];
                int ny = cur[1] + col[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (!visited[nx][ny] && board[nx][ny] == 'X') {
                        part.add(new int[]{nx, ny});
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
