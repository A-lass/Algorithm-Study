import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int[] row = {1, -1, 0, 0};
    static int[] col = {0, 0, 1, -1};
    static int startX;
    static int startY;
    static int endX;
    static int endY;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        startX = Integer.parseInt(st.nextToken());

        startY = Integer.parseInt(st.nextToken());

        endX = Integer.parseInt(st.nextToken());

        endY = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        boolean[][] visited = new boolean[N][M];

        startX--;
        startY--;
        endX--;
        endY--;

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());

            String str = st.nextToken();

            for(int j=0;j<M;j++){
                board[i][j] = str.charAt(j);
            }
        }

        int ans = 1;
        while (!change(board, new boolean[N][M])) {
            /*System.out.println("ans = " + ans);
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }*/
            ++ans;

            /*if(ans == 10){
                break;
            }*/
        }

        bw.write(ans+"\n");
        bw.flush();
    }

    public static boolean change(char[][] board, boolean[][] visited){

        // 상 하 좌 우 (방문하지 않았고, 벽이나 1을 만나기 전까지)
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        List<int[]> deleteList = new ArrayList<>();

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == endX && cur[1] == endY) {
                return true;
            }

            for(int i=0;i<4;i++){
                int nx = cur[0] + row[i];
                int ny = cur[1] + col[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nx][ny] && (board[nx][ny] == '0' || board[nx][ny] == '#')){
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    } else if(board[nx][ny] == '1'){
                        visited[nx][ny] = true;
                        deleteList.add(new int[]{nx, ny});
                    }
                }
            }
        }

        for (int[] point : deleteList) {
            board[point[0]][point[1]] = '0';
        }

        return false;

    }

}
